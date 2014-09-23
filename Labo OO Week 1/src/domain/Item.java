package domain;

import java.util.Date;

import exc.NotLeantException;
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
	
	/*
	 * @returns true when the Item is currently leant. Otherwise this method will return false.
	 */
	public boolean isLeant(){
		return this.getRentalDay() != null;
	}
	
	public int getDaysLent() throws NotLeantException{
		if(this.getRentalDay() == null){
			throw new NotLeantException();
		}
		return DateDiff.daysBetween(this.getRentalDay(), new Date());
	}
	
	public abstract double getPrice(int days);
	
	public String toString(){
		String status = "in inventory";
		if(this.isLeant()){
			status = "leant";
		}
		return this.getId() + this.getTitle() + status;
	}
	
}
