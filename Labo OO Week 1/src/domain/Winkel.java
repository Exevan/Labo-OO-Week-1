package domain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class Winkel {
	
	private Map<Integer, Item> items;
	
	public Winkel()
	{
		items = new HashMap<Integer, Item>();
	}
	
	public String getTitle(int itemid) {
		return items.get(itemid).getTitle();
	}
	
	public double getPrice(int itemid, int days) {
		return items.get(itemid).getPrice(days);
	}		
	
	public boolean addItem(String title, int id, String type) {
		Item item;
		switch (title) {
		case "M":
			item = new Movie(title, id);
			break;
		case "G":
			item = new Game(title, id);
			break;
		default:
			return false;
		}
		items.put(id, item);
		return true;
	}
}