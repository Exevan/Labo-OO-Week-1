package domain.price;

import domain.Product;

public interface IPriceCalculator {
	
	public double calculatePrice(Product product, int days);

}
