package domain.state;

import domain.Product;

public class UitleenbaarState implements ProductState {
	
	@Override
	public void leenUit(Product product) {
		product.setStaat(new UitgeleendState());
	}

	@Override
	public void brengTerug(Product product, boolean beschadigd) {

	}

	@Override
	public boolean herstel(Product product) {
		return false;
	}

	@Override
	public void verwijder(Product product) {
		product.setStaat(new VerwijderdState());
	}
	
	@Override
	public String toString() {
		return "uitleenbaar";
	}

}
