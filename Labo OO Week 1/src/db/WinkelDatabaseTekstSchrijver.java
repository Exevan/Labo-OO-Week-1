package db;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import domain.Product;
import domain.state.VerwijderdState;

public class WinkelDatabaseTekstSchrijver implements WinkelDatabaseSchrijver {

	private WinkelDatabaseHandler handler;
	
	public WinkelDatabaseTekstSchrijver(WinkelDatabaseHandler handler)
	{
		this.handler = handler;
	}

		
	public void schrijf()
		throws DbException{	
		PrintWriter schrijver;
		try{
			schrijver = new PrintWriter(handler.getBestand());
		}catch(FileNotFoundException e){
			throw new DbException("file not available");
		}
		ArrayList<Product> producten = handler.getWinkel().getProducten();
		for(Product product: producten)
		{
			if(product.getStaat().getClass().equals(VerwijderdState.class))
				continue;
			
			schrijver.print(product.getType());			
			schrijver.print("\t");
			//id
			schrijver.print(product.getId());
			schrijver.print("\t");
			//title
			schrijver.print(product.getNaam());
			schrijver.print("\t");
			//basisprijs
			schrijver.print(product.getBasisprijs());
			schrijver.print("\t");
			//staat
			schrijver.print(product.getStaat().getClass().getCanonicalName());
		}
		schrijver.close();		
	}
}
