package domain;

public class Muziek extends Product {

	public Muziek(String id, String name, int basisPrijs) 
			throws DomainException {
		super(id, name, basisPrijs);
	}

	@Override
	public double berekenHuurprijs(int aantalDagen) {
		double huurprijs = aantalDagen * 1.5;
		return huurprijs;
	}
	
	public String toString()
	{
		return "cd - "+getNaam();
	}

	@Override
	public String getType() {
		return Winkel.MUZIEKT;
	}

}
