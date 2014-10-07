package domain.state;

import domain.Product;

public class UitleenbaarState extends State implements ProductState {

	public UitleenbaarState(Product product) {
		super(product);
	}

	@Override
	public void leenUit(Product product) {
		product.setStaat(new UitgeleendState(product));
	}

	@Override
	public void brengTerug(Product product, boolean beschadigd) {

	}

	@Override
	public double herstel(Product product) {
		return 0.0;
	}

	@Override
	public void verwijder(Product product) {
		product.setStaat(new VerwijderdState(product));
	}

}
