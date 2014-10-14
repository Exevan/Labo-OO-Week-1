package domain.state;

import domain.DomainException;
import domain.Product;

public interface ProductState {
	
	public void leenUit(Product product) throws DomainException;
	
	public void brengTerug(Product product, boolean beschadigd) throws DomainException;
	
	public boolean herstel(Product product) throws DomainException;
	
	public void verwijder(Product product);

}
