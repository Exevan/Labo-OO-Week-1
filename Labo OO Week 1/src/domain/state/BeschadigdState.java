package domain.state;

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
	public double herstel(Product product) {
		product.setStaat(new UitleenbaarState(product));
		return 0.0;
	}

	@Override
	public void verwijder(Product product) {
		product.setStaat(new VerwijderdState(product));
	}

}
