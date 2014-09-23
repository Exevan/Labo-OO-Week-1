package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Item;

public class IO {

	public static List<Item> readInventory(String path) {
		return null;
	}
	
	public static void writeInventory(List<Item> inventory)
	{
		Map<Class<Item>, String> categories = new HashMap<Class<Item>, String>();
		for(Item item : inventory) {
			if(categories.containsKey(item.getClass()))
				categories.put((Class<Item>) item.getClass(), categories.get(item.getClass()) + item.getTitle() + " " + item.getId() + "\n");
			else
				categories.put((Class<Item>) item.getClass(), item.getTitle() + " " + item.getId() + "\n");
		}
		
		System.out.println("");
	}
	
//	private static void writeInventory(List<String> inventory) {
//		BufferedWriter writer = null;
//		try {
//			File file = new File("winkel.txt");
//			System.out.println(file.getCanonicalPath());
//			writer = new BufferedWriter(new FileWriter(file));			
//			for (String item : inventory) {
//				writer.write(item);
//			}
//		} catch(IOException e) {
//
//		} finally {
//			try {
//				writer.close();				
//			} catch(IOException e) {
//
//			}
//		}
//	}

}
