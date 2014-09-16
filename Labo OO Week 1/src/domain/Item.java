package domain;

public abstract class Item {
	
	protected String title;
	protected int id;	
	
	public Item(String title, int id) {
		this.setTitle(title);
		this.setId(id);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public abstract double getPrice(int days);

}
