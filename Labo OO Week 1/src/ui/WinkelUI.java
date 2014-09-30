package ui;

import db.DbException;
import db.WinkelDatabaseLezer;
import db.WinkelDatabaseSchrijver;
import domain.DomainException;
import domain.Winkel;

import javax.swing.JOptionPane;

public class WinkelUI {
	
	public void start()
	{
		Winkel winkel = new Winkel();
		
		WinkelDatabaseLezer dblezer = null;
		String fileString = JOptionPane.showInputDialog("Waar vinden we uw db-file?");
		if(fileString != null)
		{
			try {
				dblezer = new WinkelDatabaseLezer(fileString, winkel);
			} catch (DbException e) {
				JOptionPane.showMessageDialog(null, "cannot read from file");
			}
			try {
				dblezer.lees();
			} catch (DbException e) {
				JOptionPane.showMessageDialog(null, "cannot read from file");
			}
		}
		

		String menu = "1. Add item\n" +
				      "2. Show item\n" +
				      "3. Show all items\n" +
				      "4. Show rental price\n" +				      
				      "\n" + 
				      "0. Quit";
		int choice = -1;
		while (choice != 0) {
			String choiceString = JOptionPane.showInputDialog(menu);
			// if you pressed cancel or close...
			if(choiceString == null)
			{
				choice = 0;
			}
			else
			{
				choice = Integer.parseInt(choiceString);
				if (choice == 1) {
					addItem(winkel);
				} else if (choice == 2) {
					showItem(winkel);
				} else if (choice == 3){
					showItems(winkel);
				} else if (choice == 4){
					showPrice(winkel);
				}
			}
		}
		WinkelDatabaseSchrijver dbschrijver = null;
		try {
			dbschrijver = new WinkelDatabaseSchrijver(fileString, winkel);
		} catch (DbException e) {
			JOptionPane.showMessageDialog(null, "cannot write to file");
		}
		try {
			dbschrijver.schrijf();
		} catch (DbException e) {
			JOptionPane.showMessageDialog(null, "cannot write to file");
		}
	}
	
	private void addItem(Winkel winkel)
	{
		String title = JOptionPane.showInputDialog("Enter the title:");
		String id = JOptionPane.showInputDialog("Enter the id:");
		Object choice = JOptionPane.showInputDialog(null,
				"What type:", "Possible types",
				JOptionPane.QUESTION_MESSAGE, null, Winkel.TYPES, null);
		String type = (String) choice;
		try {
			winkel.voegProductToe(type, id, title);
		} catch (DomainException e) {
			JOptionPane.showMessageDialog(null, "product werd niet goed toegevoegd, wellicht door foutieve input");
		}
	}
	
	private void showItem(Winkel winkel)
	{
		String id = JOptionPane.showInputDialog("Enter the id:");
		JOptionPane.showMessageDialog(null, winkel.getProductTitle(id));
	}
	
	private void showItems(Winkel winkel)
	{
		JOptionPane.showMessageDialog(null, winkel);
	}
	
	private void showPrice(Winkel winkel)
	{
		String id = JOptionPane.showInputDialog("Enter the id:");
		String daysString = JOptionPane.showInputDialog("Enter the number of days:");
		int days = Integer.parseInt(daysString);
		JOptionPane.showMessageDialog(null, "Huurprijs: "+winkel.getProductRentalPrice(id,days)+"euro.");
	}

}
