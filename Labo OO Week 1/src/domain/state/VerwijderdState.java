package domain.state;

import domain.Product;

public class VerwijderdState extends State implements ProductState {

	public VerwijderdState(Product product) {
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
		return false;
	}

	@Override
	public void verwijder(Product product) {

	}
	
	@Override
	public String toString() {
		return "verwijderd";
	}

}
