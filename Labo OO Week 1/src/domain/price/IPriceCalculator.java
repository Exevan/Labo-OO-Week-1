package domain.price;

import domain.Winkel;

public interface IPriceCalculator {
	
	public double calculatePrice(String id, int days, Winkel winkel);

}
