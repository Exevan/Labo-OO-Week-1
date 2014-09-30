package db;

import java.io.FileNotFoundException;
import java.util.Scanner;

import domain.DomainException;
import domain.Film;
import domain.Muziek;
import domain.Product;
import domain.Spel;
import domain.Winkel;

public class WinkelDatabaseTekstLezer extends WinkelDatabaseHandler implements WinkelDatabaseLezer {
	
	
	public WinkelDatabaseTekstLezer(String filename, Winkel winkel)
		throws DbException
	{
		super(filename,winkel);
	}

	public void lees()
		throws DbException{		
		Scanner filescanner = null;
		try {
			filescanner = new Scanner(getBestand());
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
			Product product = null;
			try {
				switch (type) {
				case "F":
					product = new Film(id, title);
					break;
				case "M":
					product = new Muziek(id, title);
					break;
				case "S":
					product = new Spel(id, title);
					break;
				default:
					break;
				}
				getWinkel().voegProductToe(product);
			} catch (DomainException e) {
				throw new DbException("cannot add product available in file");
			} finally {
				linescanner.close();
			}						
		}
		filescanner.close();
	}

}
