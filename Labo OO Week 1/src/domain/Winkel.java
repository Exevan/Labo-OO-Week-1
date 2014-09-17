package domain;
import java.util.HashMap;
import java.util.Map;

public class Winkel {
	
	private Map<Integer, Item> items;
	
	public Winkel()
	{
		items = new HashMap<Integer, Item>();
	}
	
	public int nbItems() {
		return items.size();
	}

	public String getTitle(int itemid) {
		if (items.containsKey(itemid))	
			return items.get(itemid).getTitle();
		else
			return "";
		
	}
	
	public double getPrice(int itemid, int days) {
		if (items.containsKey(itemid))	
			return items.get(itemid).getPrice(days);
		else
			return -1;
	}		
	
	public boolean addItem(String title, int id, String type) {
		Item item;
		switch (type) {
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