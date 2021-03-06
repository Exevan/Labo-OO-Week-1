package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.jws.Oneway;

import domain.price.GeenKortingStrategy;
import domain.price.KwantumKortingStrategy;
import domain.price.RegularCustomeKortingSTrategy;

public class Winkel implements Observable {

	private HashMap<String, Product> producten;
	private KlantenLijst klantenLijst;
	private List<Observer> observers;

	public Winkel()
	{
		producten = new LinkedHashMap<String, Product>();
	}

//---PRODUCTEN---------------------------------------------------------------------------------------------------------------------------------------
	
	public void voegProductToe(Product product)
			throws DomainException{
		if(product == null)
			throw new DomainException("product should not be null");
		if(! producten.containsKey(product.getId()))
			producten.put(product.getId(), product);				
	}

	public void leenProductUit(String id) throws DomainException {
		producten.get(id).leenUit();
	}

	public void brengProductTerug(String id, boolean beschadigd) throws DomainException {
		producten.get(id).brengTerug(beschadigd);
	}

	public boolean herstelProduct(String id) throws DomainException {
		return producten.get(id).herstel();
	}


	private Product getProduct(String id) {
		Collection<Product> productenSet = producten.values();
		Product product = null;
		for(Product p: productenSet)
		{
			if(p.getId().equals(id))
			{
				product = p;
			}
		}
		return product;		
	}

	public String getTitel(String id) {
		String title = "";
		Product product = getProduct(id);
		if(product != null)
		{
			title = product.getNaam();
		}
		else
		{
			title = "no product with this id";
		}
		return title;
	}

	public double getHuurPrijs(String id, int nrDays) {

		double price = 0;
		Product product = getProduct(id);
		if(product != null)
		{
			price = product.berekenHuurprijs(nrDays);
		}
		return price;
	}

	public double getHerstelPrijs(String id) {
		return producten.get(id).getBasisprijs() / 3;
	}

	public void setKorting(String id, KortingStrategyType type) {
		Product product = getProduct(id);
		switch (type) {
		case GEEN:
			product.setKorting(new GeenKortingStrategy(product));
			break;
		case REGULAR:
			product.setKorting(new RegularCustomeKortingSTrategy(product));
			break;
		case KWANTUM:
			product.setKorting(new KwantumKortingStrategy(product));
			break;
		default:
			break;
		}
	}

//---KLANTEN-----------------------------------------------------------------------------------------------------------------------------------------
	
	public void voegKlantToe(String naam, String email) {
		klantenLijst.addKlant(naam, email);
	}
	
	public void verwijderKlant(int id) {
		klantenLijst.removeKlant(id);
	}
	
	

//---OBSERVERS---------------------------------------------------------------------------------------------------------------------------------------
	
	
	@Override
	public void register(Observer observer) {
		observers.add(observer);		
	}

	@Override
	public void remove(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notify(Object type) {
		for(Observer observer : observers) {
			observer.update(this, type);
		}
	}

	public String toString() {
		String winkelitems = "";
		Collection<Product> productenSet = producten.values();
		for(Product product: productenSet)
			winkelitems += product +"\n";
		return winkelitems;
	}

	public ArrayList<Product> getProducten() {
		ArrayList<Product> productenlijst = new ArrayList<Product>();
		Collection<Product> productenSet = producten.values();
		for(Product product: productenSet)
			productenlijst.add(product);
		return productenlijst;
	}

}
