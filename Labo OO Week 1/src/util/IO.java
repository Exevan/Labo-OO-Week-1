package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import domain.Item;

public class IO {

	public static List<Item> readInventory(String path) {
		return null;
	}

	public static void writeInventory(List<String> inventory) {
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
