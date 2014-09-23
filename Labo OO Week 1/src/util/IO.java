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
import java.util.Scanner;

import domain.Item;

public class IO {

	public static List<Item> readInventory() {
		List<String> data = read();
		return null;
	}
	
	private static List<String> read() {
		List<String> data = new ArrayList<String>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("winkel.txt"));
			while(scanner.hasNext()) {
				@SuppressWarnings("unused")
				String line = scanner.next();
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
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
				categories.put((Class<Item>) item.getClass(),"\n" + item.getClass().getName() + "\n" + item.getTitle() + " " + item.getId() + "\n");
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
