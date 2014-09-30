package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Winkel {
	
	public static final String FILMT = "F";
	public static final String MUZIEKT = "M";
	public static final String SPELT = "S";
	public static final String[] TYPES = {FILMT,MUZIEKT,SPELT};
	
	private HashMap<Product,Integer> producten; 
	
	public Winkel()
	{
		producten = new HashMap<Product,Integer>();
	}
	
	public void voegProductToe(String type, String id, String title)
		throws DomainException
	{
		Product p = null;
		switch(type)
		{
		case FILMT:
			p = new Film(id, title);
			break;
		case MUZIEKT:
			p = new Muziek(id, title);
			break;
		case SPELT:
			p = new Spel(id, title);
			break;
		default:
			p = null;
			break;
		}
		voegProductToe(p);
	}
	
	public void voegProductToe(Product product)
			throws DomainException{
		if(product == null)
			throw new DomainException("product should not be null");
		Product zelfdeproduct = getProduct(product.getId());
		if(zelfdeproduct != null)
		{
			int oldvalue = producten.get(zelfdeproduct);
			producten.put(zelfdeproduct, oldvalue+1);
		}
		else
		{
			producten.put(product, 1);		
		}
		
	}
	
	private Product getProduct(String id)
	{
		Set<Product> productenSet = producten.keySet();
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
	
	public String getProductTitle(String id)
	{
		String title = "";
		Product product = getProduct(id);
		if(product != null)
		{
			title = product.getName();
		}
		else
		{
			title = "no product with this id";
		}
		return title;
	}
	
	public double getProductRentalPrice(String id, int nrDays)
	{
		double price = 0;
		Product product = getProduct(id);
		if(product != null)
		{
			price = product.berekenHuurprijs(nrDays);
		}
		return price;
	}
	
	public String toString()
	{
		String winkelitems = "";
		Set<Product> productenSet = producten.keySet();
		for(Product product: productenSet)
		{
			winkelitems += product + ": "+producten.get(product)+"\n";
		}
		return winkelitems;
	}
	
	public ArrayList<Product> getProducten()
	{
		ArrayList<Product> productenlijst = new ArrayList<Product>();
		Set<Product> productenSet = producten.keySet();
		for(Product product: productenSet)
		{
			for(int i = 0; i < producten.get(product); i++)
			{
				productenlijst.add(product);
			}
		}
		return productenlijst;
	}

}
