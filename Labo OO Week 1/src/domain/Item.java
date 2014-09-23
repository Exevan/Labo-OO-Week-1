package domain;

import java.util.Date;

import util.DateDiff;

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
	
	public boolean isLeant(){
		return this.getRentalDay() != null;
	}
	
	/*
	 * Make sure that you first check whether the Item actually is leant. Otherwise this will throw a NullpointerException.
	 */
	public int getDaysLent() throws NotLeantException{
		if(this.getRentalDay() == null){
			throw new NotLeantException();
		}
		return DateDiff.daysBetween(this.getRentalDay(), new Date());
	}
	
	public abstract double getPrice(int days);

}
