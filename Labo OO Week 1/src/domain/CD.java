package domain;

public class CD extends Item{

	//Dit is een debiele verandering.
	public CD(String title, int id) {
		super(title, id);
	}

	@Override
	public double getPrice(int days) {
		return days * 1.5;
	}
	
}
