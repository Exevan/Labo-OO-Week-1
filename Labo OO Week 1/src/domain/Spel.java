package domain;

public class Spel extends Product {

	public Spel(String id, String name) throws DomainException {
		super(id, name);
	}

	@Override
	public double berekenHuurprijs(int aantalDagen) {
		double huurprijs = aantalDagen * 3;
		return huurprijs;
	}
	
	public String toString()
	{
		return "spel - "+getName();
	}

	@Override
	public String getType() {		
		return Winkel.SPELT;
	}

}
