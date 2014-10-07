package domain;

import domain.price.GeenKortingStrategy;
import domain.price.KortingStrategy;
import domain.state.ProductState;
import domain.state.UitleenbaarState;

public abstract class Product {

	private ProductState staat;
	private KortingStrategy korting;

	private String id;
	private String name;

	public Product(String id, String name)
			throws DomainException {
		setId(id);
		setName(name);
		setStaat(new UitleenbaarState(this));
		setKorting(new GeenKortingStrategy(this));;
	}

	public String getId() {
		return id;
	}

	private void setId(String id) 
			throws DomainException
	{
		if(id == null)
			throw new DomainException("provide valid id");
		this.id = id;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) 
			throws DomainException
	{
		if(name == null)
			throw new DomainException("provide valid name");
		this.name = name;
	}

	public ProductState getStaat() {
		return staat;
	}

	public void setStaat(ProductState staat) {
		this.staat = staat;
	}

	public void leenUit() {
		staat.leenUit(this);
	}

	public void brengTerug(boolean beschadigd) {
		staat.brengTerug(this, beschadigd);
	}

	public void herstel(boolean beschadigd) {
		staat.herstel(this);
	}

	public void verwijder() {
		staat.verwijder(this);
	}

	public KortingStrategy getKorting() {
		return korting;
	}

	public void setKorting(KortingStrategy korting) {
		this.korting = korting;
	}

	public double berekenKorting(int aantalDagen) {
		return korting.berekenKorting(this, aantalDagen);
	}

	public abstract String getType();
	public abstract double berekenHuurprijs(int aantalDagen);


}
