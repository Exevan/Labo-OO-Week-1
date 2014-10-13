package db;

import java.io.FileNotFoundException;
import java.util.Scanner;

import domain.DomainException;
import domain.Film;
import domain.Muziek;
import domain.Product;
import domain.Spel;

public class WinkelDatabaseTekstLezer implements WinkelDatabaseLezer {
	
	private WinkelDatabaseHandler handler;
	
	public WinkelDatabaseTekstLezer(WinkelDatabaseHandler handler){
		this.handler = handler;
	}

	public void lees()
		throws DbException{		
		Scanner filescanner = null;
		try {
			filescanner = new Scanner(handler.getBestand());
		} catch (FileNotFoundException e1) {
			throw new DbException("file not available");
		}
		while(filescanner.hasNextLine())
		{
			Scanner linescanner = new Scanner(filescanner.nextLine());
			linescanner.useDelimiter("\t");
			String type = linescanner.next();
			String id = linescanner.next();
			String title = linescanner.next();
			int basisprijs = Integer.parseInt(linescanner.next());
			Product product = null;
			try {
				switch (type) {
				case "F":
					product = new Film(id, title, basisprijs);
					break;
				case "M":
					product = new Muziek(id, title, basisprijs);
					break;
				case "S":
					product = new Spel(id, title, basisprijs);
					break;
				default:
					break;
				}
				handler.getWinkel().voegProductToe(product);
			} catch (DomainException e) {
				throw new DbException("cannot add product available in file");
			} finally {
				linescanner.close();
			}						
		}
		filescanner.close();
	}

}
