package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Item;

public class IO {

	public static List<Item> readInventory() {
		List<String> data = read();
		return null;
	}
	
	private static List<String> read() {
		List<String> data = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File("winkel.txt")));
			String type = "";
			String line = "";
			while(line != null) {
				line = reader.readLine();
				if(line.equals(""))
					type = reader.readLine();
				else
					data.add(type + " " + line);		
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e)	{	
		} finally {
			try {
				reader.close();
			} catch (Exception e) {
			}
		}
		return data;
	}
	
	
	@SuppressWarnings("unchecked")
	public static void writeInventory(List<Item> inventory)
	{
		Map<Class<Item>, String> categories = new HashMap<Class<Item>, String>();
		for(Item item : inventory) {
			if(categories.containsKey(item.getClass()))
				categories.put((Class<Item>) item.getClass(), categories.get(item.getClass()) + item.getTitle() + " " + item.getId() + "\n");
			else
				categories.put((Class<Item>) item.getClass(),"\n" + item.getClass().getSimpleName() + "\n" + item.getTitle() + " " + item.getId() + "\n");
		}
		Collection<String> data = categories.values();
		write(data);
	}
	
	private static void write(Collection<String> inventory) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(new File("winkel.txt")));			
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
