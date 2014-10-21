package domain;

import java.util.Deque;
import java.util.LinkedList;

public class Klant implements Observer {
	
	private int id;
	private String naam;
	private String email;
	private boolean geregistreerd;
	private Deque<String> berichten;
	
	public Klant(int id, String naam, String email) {
		setId(id);
		setNaam(naam);
		setEmail(email);
		berichten = new LinkedList<String>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void addBericht(String bericht) {
		if (berichten.size() == 3) {
			berichten.pollLast();
		}
		berichten.addFirst(bericht);
	}
	
	public void removeBerichten() {
		berichten.clear();
	}
	
	public String getBerichten() {
		String berichten = "";
		for(String bericht : this.berichten)
			berichten += (bericht + "\n");
		return berichten;
	}

	@Override
	public void update(Winkel winkel, Object type) {
		// TODO Auto-generated method stub
		
	}

}
