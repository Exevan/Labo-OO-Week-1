package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Item;

public class IO {

	public static List<Item> readInventory(String path) {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static void writeInventory(List<Item> inventory)
	{
		Map<Class<Item>, String> categories = new HashMap<Class<Item>, String>();
		for(Item item : inventory) {
			if(categories.containsKey(item.getClass()))
				categories.put((Class<Item>) item.getClass(), categories.get(item.getClass()) + item.getTitle() + " " + item.getId() + "\n");
			else
				categories.put((Class<Item>) item.getClass(),"\n" + item.getClass().getName() + "\n" + item.getTitle() + " " + item.getId() + "\n");
		}
		Collection<String> data = categories.values();
		write(data);
	}
	
	private static void write(Collection<String> inventory) {
		BufferedWriter writer = null;
		try {
			File file = new File("winkel.txt");
			System.out.println(file.getCanonicalPath());
			writer = new BufferedWriter(new FileWriter(file));			
			for (String item : inventory) {
				writer.write(item);
			}
		} catch(IOException e) {

		} finally {
			try {
				writer.close();				
			} catch(IOException e) {

			}
		}
	}

}
