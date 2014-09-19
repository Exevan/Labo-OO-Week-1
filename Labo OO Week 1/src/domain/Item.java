package domain;

import java.util.Date;

public abstract class Item {
	
	protected String title;
	protected int id;
	protected Date rentalDay;
	
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

	public Date getRentalDay(){
		return this.rentalDay;
	}
	
	public void setRentalDay(Date rentalDay){
		this.rentalDay = rentalDay;
	}
	
	public void setReturned(){
		this.rentalDay = null;
	}
	
	public int getDaysLent(){
		return DateDifference.daysBetween(this.getRentalDay(), new Date());
	}
	
	public abstract double getPrice(int days);

}
