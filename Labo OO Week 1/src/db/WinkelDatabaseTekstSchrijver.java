package db;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import domain.Product;
import domain.Winkel;

public class WinkelDatabaseTekstSchrijver extends WinkelDatabaseHandler{

	public WinkelDatabaseTekstSchrijver(String filename, Winkel winkel)
		throws DbException
	{
		super(filename,winkel);
	}

		
	public void schrijf()
		throws DbException{	
		PrintWriter schrijver;
		try{
			schrijver = new PrintWriter(getBestand());
		}catch(FileNotFoundException e){
			throw new DbException("file not available");
		}
		ArrayList<Product> producten = getWinkel().getProducten();
		for(Product product: producten)
		{
			schrijver.print(product.getType());			
			schrijver.print("\t");
			//id
			schrijver.print(product.getId());
			schrijver.print("\t");
			//title
			schrijver.println(product.getName());
		}
		schrijver.close();		
	}
}
