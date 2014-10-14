package domain.state;

import java.util.Random;

import domain.Product;

public class BeschadigdState implements ProductState {

	@Override
	public void leenUit(Product product) {

	}

	@Override
	public void brengTerug(Product product, boolean beschadigd) {

	}

	@Override
	public boolean herstel(Product product) {
		if(new Random().nextDouble() < 0.5) {
			product.setStaat(new UitleenbaarState());
			return true;
		} else {
			product.setStaat(new VerwijderdState());
			return false;
		}
	}

	@Override
	public void verwijder(Product product) {
		product.setStaat(new VerwijderdState());
	}
	
	@Override
	public String toString() {
		return "beschadigd";
	}

}
