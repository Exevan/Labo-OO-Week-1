package domain;

import domain.price.GeenKortingStrategy;
import domain.price.KortingStrategy;
import domain.state.ProductState;
import domain.state.UitleenbaarState;

public abstract class Product {

	private ProductState staat;
	private KortingStrategy korting;

	private String id;
	private String naam;
	private int basisprijs;

	public Product(String id, String name, int basisprijs)
			throws DomainException 
	{
		setId(id);
		setNaam(name);
		setBasisprijs(basisprijs);
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

	public String getNaam() {
		return naam;
	}

	private void setNaam(String naam) 
			throws DomainException
	{
		if(naam == null)
			throw new DomainException("provide valid name");
		this.naam = naam;
	}

	public int getBasisprijs() {
		return basisprijs;
	}

	public void setBasisprijs(int basisprijs) 
		throws DomainException
	{
		if(basisprijs <= 0)
			throw new DomainException("provide valid base price");
		this.basisprijs = basisprijs;
	}

	public String getStaat() {
		return staat.toString();
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

	public boolean herstel() {
		return staat.herstel(this);
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
