package domain.state;

import domain.Product;

public class UitgeleendState extends State implements ProductState {

	public UitgeleendState(Product product) {
		super(product);
	}

	@Override
	public void leenUit(Product product) {

	}

	@Override
	public void brengTerug(Product product, boolean beschadigd) {
		if(beschadigd)
			product.setStaat(new BeschadigdState(product));
		else
			product.setStaat(new UitleenbaarState(product));
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
