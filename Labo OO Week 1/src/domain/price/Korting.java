package domain.price;

import domain.Product;

public abstract class Korting {
	
	private Product product;
	
	public Korting(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}
}
