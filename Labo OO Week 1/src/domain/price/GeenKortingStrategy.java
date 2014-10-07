package domain.price;

import domain.Product;

public class GeenKortingStrategy extends Korting implements KortingStrategy {
	
	public GeenKortingStrategy(Product product) {
		super(product);
	}

	@Override
	public double berekenKorting(Product product, int days) {
		return 0;
	}

}
