package domain;

public class Movie extends Item {
	
	public Movie(String title, int id) {
		super(title, id);
	}

	@Override
	public double getPrice(int days) {
		double price = 5.0;
		int daysLeft = days - 3;
		if (daysLeft > 0) {
			price += (daysLeft * 2);
		}
		return price;
	}

}
