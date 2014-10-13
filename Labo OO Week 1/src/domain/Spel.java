package domain;

public class Spel extends Product {

	public Spel(String id, String name, int basisPrijs) 
			throws DomainException {
		super(id, name, basisPrijs);
	}

	@Override
	public double berekenHuurprijs(int aantalDagen) {
		double huurprijs = aantalDagen * 3;
		return huurprijs;
	}
	
	public String toString()
	{
		return "spel - "+getNaam();
	}

	@Override
	public String getType() {		
		return Winkel.SPELT;
	}

}
