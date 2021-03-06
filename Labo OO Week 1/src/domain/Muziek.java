package domain;

import domain.state.ProductState;

public class Muziek extends Product {

	public Muziek(String id, String name, int basisPrijs) 
			throws DomainException {
		super(id, name, basisPrijs);
	}
	
	public Muziek(String id, String name, int basisprijs, ProductState staat) 
			throws DomainException {
		super(id, name, basisprijs, staat);
	}

	@Override
	public double berekenHuurprijs(int aantalDagen) {
		double huurprijs = aantalDagen * 1.5;
		double korting = berekenKorting(aantalDagen, huurprijs);
		return huurprijs - korting;
	}
	
	public String toString()
	{
		return "Muziek - " + getNaam();
	}

	@Override
	public String getType() {
		return ProductType.MUZIEK.toString();
	}

}
