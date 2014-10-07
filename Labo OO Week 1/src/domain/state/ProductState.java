package domain.state;

import domain.Product;

public interface ProductState {
	
	public void leenUit(Product product);
	
	public void brengTerug(Product product, boolean beschadigd);
	
	public double herstel(Product product);
	
	public void verwijder(Product product);

}
