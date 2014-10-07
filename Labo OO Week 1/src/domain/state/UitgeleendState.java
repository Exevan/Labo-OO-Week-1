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
	public double herstel(Product product) {
		return 0.0;
	}

	@Override
	public void verwijder(Product product) {

	}

}
