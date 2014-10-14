package ui;

import javax.swing.JOptionPane;

import db.DbException;
import db.WinkelDatabaseHandler;
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

	private static final String MENU = "1. Add item\n" 
			+ "2. Show item\n" 
			+ "3. Show all items\n" 
			+ "4. Show rental price\n"
			+ "5. Rent product\n"
			+ "6. Return product\n"
			+ "7. Repair product\n"
			+ "\n" 
			+ "0. Quit";

	public void start() {
		Winkel winkel = new Winkel();

		WinkelDatabaseHandler dbhandler = null;
		int storageType = 0;
		String fileString = JOptionPane.showInputDialog("Waar vinden we uw db-file?");

		try {
			dbhandler = new WinkelDatabaseHandler(fileString, winkel);
		} catch (DbException e1) {
			//TODO: Display message.
		}

		if (fileString != null) {
			String input = JOptionPane.showInputDialog("Gebruikt u tekst of xml als opslagmethode?\n\n1. Tekst\n2. XML");
			storageType = Integer.parseInt(input);

			if (storageType == 1) {
				dbhandler.setWinkelDatabaseLezer(new WinkelDatabaseTekstLezer(dbhandler));
			} else if (storageType == 2) {
				dbhandler.setWinkelDatabaseLezer(new WinkelDatabaseXMLLezer(dbhandler));
			}

			try {
				dbhandler.lees();
			} catch (Exception e) {
				//TODO: Display message
			}
		}


		String menu = MENU;
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
					} else if (choice == 5) {
						rentProduct(winkel);
					} else if (choice == 6) {
						returnProduct(winkel);
					} else if (choice == 7) {
						repairProduct(winkel);
					}
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Please enter the number of your choice.");
				}

			}
		}

		String input = JOptionPane.showInputDialog("Gebruikt u tekst of xml als opslagmethode?\n\n1. Tekst\n2. XML");
		storageType = Integer.parseInt(input);
		
		if (storageType == 1) {
			dbhandler.setWinkelDatabaseSchrijver(new WinkelDatabaseTekstSchrijver(dbhandler));
		} else if (storageType == 2) {
			dbhandler.setWinkelDatabaseSchrijver(new WinkelDatabaseXMLSchrijver(dbhandler));
		}

		try {
			dbhandler.schrijf();
		} catch (Exception e) {
			//TODO: Display message
		}
	}

	private String askTitle() {
		return JOptionPane.showInputDialog("Enter the title:");
	}

	private String askId() {
		return JOptionPane.showInputDialog("Enter the id:");
	}

	private Object askType() {
		return JOptionPane.showInputDialog(null, "What type:", "Possible types", JOptionPane.QUESTION_MESSAGE, null, Winkel.TYPES, null);
	}

	private int askBasePrice() {
		return Integer.parseInt(JOptionPane.showInputDialog("Enter the product's base price:"));
	}

	private void addItem(Winkel winkel) {
		String title = askTitle();
		String id = askId();
		Object choice = askType();
		int basisPrijs = askBasePrice();
		String type = (String) choice;
		Product product = null;
		try {
			switch (type) {
			case "F":
				product = new Film(id, title, basisPrijs);
				break;
			case "M":
				product = new Muziek(id, title, basisPrijs);
				break;
			case "S":
				product = new Spel(id, title, basisPrijs);
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
		JOptionPane.showMessageDialog(null, winkel.getTitel(id));
	}

	private void showItems(Winkel winkel) {
		JOptionPane.showMessageDialog(null, winkel);
	}

	private void showPrice(Winkel winkel) {
		String id = JOptionPane.showInputDialog("Enter the id:");
		int dagen = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of days:"));
		JOptionPane.showMessageDialog(null, "Huurprijs: " + winkel.getHuurPrijs(id, dagen) + "euro.");
	}

	private void rentProduct(Winkel winkel) {
		winkel.leenProductUit(JOptionPane.showInputDialog("Enter id:"));
	}

	private void returnProduct(Winkel winkel) {
		String id = JOptionPane.showInputDialog("Enter id:");
		boolean beschadigd = (0 == JOptionPane.showOptionDialog(null, "Is the product damaged?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null));
		winkel.brengProductTerug(id, beschadigd);
	}
	
	private void repairProduct(Winkel winkel) {
		String id = JOptionPane.showInputDialog("Enter id:");
		boolean hersteld = winkel.herstelProduct(id);
		double herstelPrijs = winkel.getHerstelPrijs(id);
		if(hersteld)
			JOptionPane.showMessageDialog(null, "Product is gerepareerd\n Herstelprijs: " + herstelPrijs);
		else
			JOptionPane.showMessageDialog(null, "Product is niet gerepareerd en wordt verwijderd\n Herstelprijs: " + herstelPrijs);
	}
}
