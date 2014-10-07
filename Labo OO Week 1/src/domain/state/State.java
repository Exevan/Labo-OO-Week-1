package domain.state;

import domain.Product;

public abstract class State {
	
	private Product product;
	
	public State(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}	
}
