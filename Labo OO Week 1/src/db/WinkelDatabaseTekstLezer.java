package db;

import java.io.FileNotFoundException;
import java.util.Scanner;

import domain.DomainException;
import domain.Film;
import domain.Muziek;
import domain.Product;
import domain.Spel;
import domain.state.ProductState;

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
			Object staat = null;
			try {
				String stateName = linescanner.next();
				Class<?> stateClass = Class.forName(stateName);
				staat = stateClass.newInstance();
			} catch (Exception e) {
				linescanner.close();
				throw new DbException("Product could not be created");
			}
			Product product = null;
			try {
				switch (type) {
				case "F":
					product = new Film(id, title, basisprijs, (ProductState) staat);
					break;
				case "M":
					product = new Muziek(id, title, basisprijs, (ProductState) staat);
					break;
				case "S":
					product = new Spel(id, title, basisprijs, (ProductState) staat);
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
