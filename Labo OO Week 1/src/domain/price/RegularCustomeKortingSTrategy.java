package domain.price;

import domain.Product;

public class RegularCustomeKortingSTrategy extends Korting implements KortingStrategy {

	public RegularCustomeKortingSTrategy(Product product) {
		super(product);
	}

	@Override
	public double berekenKorting(Product product, int days) {
		return 0.25 * product.berekenHuurprijs(days);
	}

}
