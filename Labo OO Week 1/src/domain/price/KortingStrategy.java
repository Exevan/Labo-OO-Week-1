package domain.price;

import domain.Product;

public interface KortingStrategy {
	
	public double berekenKorting(Product product, int days, double prijs);

}
