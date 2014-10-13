package domain.state;

import domain.Product;

public interface ProductState {
	
	public void leenUit(Product product);
	
	public void brengTerug(Product product, boolean beschadigd);
	
	public boolean herstel(Product product);
	
	public void verwijder(Product product);

}
