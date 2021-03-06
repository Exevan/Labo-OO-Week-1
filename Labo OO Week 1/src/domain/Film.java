package domain;

import domain.state.ProductState;

public class Film extends Product {

	public Film(String id, String name, int basisPrijs) 
			throws DomainException {
		super(id, name, basisPrijs);
	}	

	public Film(String id, String name, int basisprijs, ProductState staat) 
			throws DomainException {
		super(id, name, basisprijs, staat);
	}

	@Override
	public double berekenHuurprijs(int aantalDagen) {
		double huurprijs = 5;
		int daysLeft = aantalDagen - 3;
		if (daysLeft > 0) {
			huurprijs += (daysLeft * 2);
		}
		double korting = berekenKorting(aantalDagen, huurprijs);
		return huurprijs - korting;
	}

	public String toString()
	{
		return "film - "+getNaam();
	}

	@Override
	public String getType() {		
		return ProductType.FILM.toString();
	}

}
