package domain.state;

import java.util.Random;

import domain.Product;

public class BeschadigdState extends State implements ProductState {

	public BeschadigdState(Product product) {
		super(product);
	}

	@Override
	public void leenUit(Product product) {

	}

	@Override
	public void brengTerug(Product product, boolean beschadigd) {

	}

	@Override
	public boolean herstel(Product product) {
		if(new Random().nextDouble() < 0.5) {
			product.setStaat(new UitleenbaarState(product));
			return true;
		} else {
			product.setStaat(new VerwijderdState(product));
			return false;
		}
	}

	@Override
	public void verwijder(Product product) {
		product.setStaat(new VerwijderdState(product));
	}
	
	@Override
	public String toString() {
		return "beschadigd";
	}

}
