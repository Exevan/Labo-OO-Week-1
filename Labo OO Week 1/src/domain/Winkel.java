package domain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
	public List<Item> getInventory() {
		List<Item> inventory = new ArrayList<Item>();
		for(Item item : items.values())
			inventory.add(item);
		return inventory;
	}
	
	public List<String> getInventoryList() {
		List<String> items = new ArrayList<String>();
		for (Item item : this.items.values()) {
			String rentalDay = "";
			if (item.getRentalDay() != null)
				rentalDay = item.getRentalDay().toString();
			items.add(item.getId() + "\t" + item.getTitle() + "\t" + rentalDay);
		}
		return items;
	}

	public List<String> getTitles() {
		List<String> items = new ArrayList<String>();
		for (Item item : this.items.values()) {
			items.add(item.getTitle());
		}
		return items;
	}
	
	public boolean addItem(Item item) {
		items.put(item.getId(), item);
		return true;
	}
}