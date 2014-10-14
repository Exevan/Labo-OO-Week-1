package domain.state;

import domain.DomainException;
import domain.Product;

public class UitleenbaarState implements ProductState {
	
	@Override
	public void leenUit(Product product) throws DomainException {
		if(product.getStaat().equals(UitgeleendState.class))
			throw new DomainException("Product is al uitgeleend");
		if(product.getStaat().equals(BeschadigdState.class))
			throw new DomainException("Product is beschadigd");
		if(product.getStaat().equals(VerwijderdState.class))
			throw new DomainException("Product is niet meer beschikbaar");
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
