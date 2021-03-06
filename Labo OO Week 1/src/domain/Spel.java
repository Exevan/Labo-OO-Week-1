package domain;

import domain.state.ProductState;

public class Spel extends Product {

	public Spel(String id, String name, int basisPrijs) 
			throws DomainException {
		super(id, name, basisPrijs);
	}
	
	public Spel(String id, String name, int basisprijs, ProductState staat) 
			throws DomainException {
		super(id, name, basisprijs, staat);
	}

	@Override
	public double berekenHuurprijs(int aantalDagen) {
		double huurprijs = aantalDagen * 3;
		double korting = berekenKorting(aantalDagen, huurprijs);
		return huurprijs - korting;
	}
	
	public String toString()
	{
		return "spel - " + getNaam();
	}

	@Override
	public String getType() {		
		return ProductType.SPEL.toString();
	}

}
