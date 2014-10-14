package domain.state;

import domain.Product;

public class UitgeleendState implements ProductState {

	@Override
	public void leenUit(Product product) {

	}

	@Override
	public void brengTerug(Product product, boolean beschadigd) {
		if(beschadigd)
			product.setStaat(new BeschadigdState());
		else
			product.setStaat(new UitleenbaarState());
	}

	@Override
	public boolean herstel(Product product) {
		return false;
	}

	@Override
	public void verwijder(Product product) {

	}
	
	@Override
	public String toString() {
		return "uitgeleend";
	}

}
