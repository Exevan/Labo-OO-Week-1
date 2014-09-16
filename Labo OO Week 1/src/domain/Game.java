package domain;

public class Game extends Item {
	
	public Game(String title, int id) {
		super(title, id);
	}

	@Override
	public double getPrice(int days) {
		return days * 3.0;
	}

}
