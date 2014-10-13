package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Winkel {

	public static final String FILMT = "F";
	public static final String MUZIEKT = "M";
	public static final String SPELT = "S";
	public static final String[] TYPES = {FILMT,MUZIEKT,SPELT};

	private HashMap<String, Product> producten; 

	public Winkel()
	{
		producten = new HashMap<String, Product>();
	}

	public void voegProductToe(Product product)
			throws DomainException{
		if(product == null)
			throw new DomainException("product should not be null");
		if(! producten.containsKey(product.getId()))
			producten.put(product.getId(), product);				
	}

	public void leenProductUit(String id) {
		producten.get(id).leenUit();
	}

	public void brengProductTerug(String id, boolean beschadigd) {
		producten.get(id).brengTerug(false);
	}
	
	public boolean herstelProduct(String id) {
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
