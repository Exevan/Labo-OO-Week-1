package domain.price;

import domain.Product;

public class KwantumKortingStrategy extends Korting implements KortingStrategy {	
	
	public KwantumKortingStrategy(Product product) {
		super(product);
	}

	@Override
	public double berekenKorting(Product product, int days, double prijs) {
		return prijs - days;
	}

}
