package domain;

public class Muziek extends Product {

	public Muziek(String id, String name) throws DomainException {
		super(id, name);
	}

	@Override
	public double berekenHuurprijs(int aantalDagen) {
		double huurprijs = aantalDagen * 1.5;
		return huurprijs;
	}
	
	public String toString()
	{
		return "cd - "+getName();
	}

	@Override
	public String getType() {
		return Winkel.MUZIEKT;
	}

}
