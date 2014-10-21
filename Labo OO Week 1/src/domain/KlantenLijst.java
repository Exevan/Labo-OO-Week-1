package domain;

import java.util.HashMap;

public class KlantenLijst {
	
	private HashMap<Integer, Klant> klanten;
	private int nextId;
	
	public KlantenLijst(int nextId, Klant... klanten) {
		for(Klant klant : klanten) 
			this.klanten.put(klant.getId(), klant);
		this.nextId = nextId;
	}
	
	public void addKlant(String naam, String email) {
		Klant klant = new Klant(nextId, naam, email);
		this.klanten.put(klant.getId(), klant);
		nextId += 1;
	}
	
	public void removeKlant(int id) {
		this.klanten.remove(id);
	}
}
