package db;

import java.io.FileNotFoundException;
import java.util.Scanner;

import domain.DomainException;
import domain.Winkel;

public class WinkelDatabaseLezer extends WinkelDatabaseHandler {
	
	
	public WinkelDatabaseLezer(String filename, Winkel winkel)
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
			try {
				getWinkel().voegProductToe(type, id, title);
			} catch (DomainException e) {
				throw new DbException("cannot add product available in file");
			} finally {
				linescanner.close();
			}						
		}
		filescanner.close();
	}

}
