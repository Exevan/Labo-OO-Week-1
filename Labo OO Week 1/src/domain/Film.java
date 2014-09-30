package domain;

public class Film extends Product {

	public Film(String id, String name) 
			throws DomainException {
		super(id, name);
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
		return "film - "+getName();
	}

	@Override
	public String getType() {		
		return Winkel.FILMT;
	}

}
