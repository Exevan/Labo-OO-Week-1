package domain;

public abstract class Product {

	private String id;
	private String name;
	
	public Product(String id, String name)
		throws DomainException
	{
		setId(id);
		setName(name);

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
	
	public abstract String getType();
	public abstract double berekenHuurprijs(int aantalDagen);
	

}
