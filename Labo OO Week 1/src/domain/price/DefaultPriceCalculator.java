package domain.price;

import domain.Winkel;

public class DefaultPriceCalculator implements IPriceCalculator {

	@Override
	public double calculatePrice(String id, int days, Winkel winkel) {
		return 0;
	}

}
