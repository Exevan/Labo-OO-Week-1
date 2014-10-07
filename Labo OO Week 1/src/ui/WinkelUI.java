package ui;

import javax.swing.JOptionPane;

import db.DbException;
import db.WinkelDatabaseLezer;
import db.WinkelDatabaseSchrijver;
import db.WinkelDatabaseTekstLezer;
import db.WinkelDatabaseTekstSchrijver;
import db.WinkelDatabaseXMLLezer;
import db.WinkelDatabaseXMLSchrijver;
import domain.DomainException;
import domain.Film;
import domain.Muziek;
import domain.Product;
import domain.Spel;
import domain.Winkel;

public class WinkelUI {

	public void start() {
		Winkel winkel = new Winkel();

		WinkelDatabaseLezer dblezer = null;
		int storageType = 0;
		String fileString = JOptionPane.showInputDialog("Waar vinden we uw db-file?");
		if (fileString != null) {
			String input = JOptionPane.showInputDialog("Gebruikt u tekst of xml als opslagmethode?\n\n1. Tekst\n2. XML");
			storageType = Integer.parseInt(input);

			try {
				if (storageType == 1) {
					dblezer = new WinkelDatabaseTekstLezer(fileString, winkel);
				} else if (storageType == 2) {
					dblezer = new WinkelDatabaseXMLLezer(fileString, winkel);
				}
			} catch (DbException e) {
				JOptionPane.showMessageDialog(null, "cannot read from file");
			}

			try {
				dblezer.lees();
			} catch (DbException e) {
				JOptionPane.showMessageDialog(null, "cannot read from file");
			}
		}

		String menu = "1. Add item\n" + "2. Show item\n" + "3. Show all items\n" + "4. Show rental price\n" + "\n" + "0. Quit";
		int choice = -1;
		while (choice != 0) {
			String choiceString = JOptionPane.showInputDialog(menu);
			// if you pressed cancel or close...
			if (choiceString == null) {
				choice = 0;
			} else {
				try{
					choice = Integer.parseInt(choiceString);
					if (choice == 1) {
						addItem(winkel);
					} else if (choice == 2) {
						showItem(winkel);
					} else if (choice == 3) {
						showItems(winkel);
					} else if (choice == 4) {
						showPrice(winkel);
					}
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Please enter the number of your choice.");
				}
				
			}
		}
		WinkelDatabaseSchrijver dbschrijver = null;
		try {
			if (storageType == 1) {
				dbschrijver = new WinkelDatabaseTekstSchrijver(fileString, winkel);
			} else if (storageType == 2) {
				dbschrijver = new WinkelDatabaseXMLSchrijver(fileString, winkel);
			}
		} catch (DbException e) {
			JOptionPane.showMessageDialog(null, "cannot write to file");
		}
		try {
			dbschrijver.schrijf();
		} catch (DbException e) {
			JOptionPane.showMessageDialog(null, "cannot write to file");
		}
	}

	private void addItem(Winkel winkel) {
		String title = JOptionPane.showInputDialog("Enter the title:");
		String id = JOptionPane.showInputDialog("Enter the id:");
		Object choice = JOptionPane.showInputDialog(null, "What type:", "Possible types", JOptionPane.QUESTION_MESSAGE, null, Winkel.TYPES, null);
		String type = (String) choice;
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
			winkel.voegProductToe(product);
		} catch (DomainException e) {
			JOptionPane.showMessageDialog(null, "product werd niet goed toegevoegd, wellicht door foutieve input");
		}
	}

	private void showItem(Winkel winkel) {
		String id = JOptionPane.showInputDialog("Enter the id:");
		JOptionPane.showMessageDialog(null, winkel.getProductTitle(id));
	}

	private void showItems(Winkel winkel) {
		JOptionPane.showMessageDialog(null, winkel);
	}

	private void showPrice(Winkel winkel) {
		String id = JOptionPane.showInputDialog("Enter the id:");
		String daysString = JOptionPane.showInputDialog("Enter the number of days:");
		int days = Integer.parseInt(daysString);
		JOptionPane.showMessageDialog(null, "Huurprijs: " + winkel.getProductRentalPrice(id, days) + "euro.");
	}

}
