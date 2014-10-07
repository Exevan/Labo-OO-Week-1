package domain.price;

import domain.Product;

public abstract class KortingHandler {
	
	private Product product;
	
	public KortingHandler(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}
}
