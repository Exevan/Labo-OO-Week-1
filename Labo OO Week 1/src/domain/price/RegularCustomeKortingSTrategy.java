package domain.price;

import domain.Product;

public class RegularCustomeKortingSTrategy extends Korting implements KortingStrategy {

	public RegularCustomeKortingSTrategy(Product product) {
		super(product);
	}

	@Override
	public double berekenKorting(Product product, int days, double prijs) {
		return 0.25 * prijs;
	}

}
