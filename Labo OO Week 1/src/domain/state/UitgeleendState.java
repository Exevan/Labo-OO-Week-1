package domain.state;

import domain.DomainException;
import domain.Product;

public class UitgeleendState implements ProductState {

	@Override
	public void leenUit(Product product) throws DomainException {
		if(product.getStaat().equals(UitgeleendState.class))
			throw new DomainException("Product is al uitgeleend");
		if(product.getStaat().equals(BeschadigdState.class))
			throw new DomainException("Product is beschadigd");
		if(product.getStaat().equals(VerwijderdState.class))
			throw new DomainException("Product is niet meer beschikbaar");
		
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
