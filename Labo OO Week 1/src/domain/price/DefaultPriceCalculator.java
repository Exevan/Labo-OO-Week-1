package domain.price;

import domain.Product;

public class DefaultPriceCalculator implements IPriceCalculator {

	@Override
	public double calculatePrice(Product product, int days) {
		return 0;
	}

}