package domain;

public class Film extends Product {

	public Film(String id, String name, int basisPrijs) 
			throws DomainException {
		super(id, name, basisPrijs);
	}	
	
	@Override
	public double berekenHuurprijs(int aantalDagen) {
		double huurprijs = 5;
		int daysLeft = aantalDagen - 3;
		if (daysLeft > 0) {
			huurprijs += (daysLeft * 2);
		}
		return huurprijs;
	}
	
	public String toString()
	{
		return "film - "+getNaam();
	}

	@Override
	public String getType() {		
		return Winkel.FILMT;
	}

}
