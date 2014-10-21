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
import domain.KortingStrategyType;
import domain.LeesStrategyType;
import domain.Muziek;
import domain.Product;
import domain.ProductType;
import domain.SchrijfStrategyType;
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
		String fileString = JOptionPane.showInputDialog("Waar vinden we uw db-file?");

		try {
			dbhandler = new WinkelDatabaseHandler(fileString, winkel);
		} catch (DbException e1) {
			//TODO: Display message.
		}

		if (fileString != null) {
			
			LeesStrategyType type = (LeesStrategyType) JOptionPane.showInputDialog(null, "Welke opslagmethode?", "Opslagmethode", JOptionPane.QUESTION_MESSAGE, null, LeesStrategyType.values(), null);

			if (type.equals(LeesStrategyType.TEKST)) {
				dbhandler.setWinkelDatabaseLezer(new WinkelDatabaseTekstLezer(dbhandler));
			} else if (type.equals(LeesStrategyType.XML)) {
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
		
		SchrijfStrategyType type = (SchrijfStrategyType) JOptionPane.showInputDialog(null, "Welke opslagmethode?", "Opslagmethode", JOptionPane.QUESTION_MESSAGE, null, SchrijfStrategyType.values(), null);

		if (type.equals(SchrijfStrategyType.TEKST)) {
			dbhandler.setWinkelDatabaseSchrijver(new WinkelDatabaseTekstSchrijver(dbhandler));
		} else if (type.equals(SchrijfStrategyType.XML)) {
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
		return JOptionPane.showInputDialog(null, "What type:", "Possible types", JOptionPane.QUESTION_MESSAGE, null, ProductType.values(), null);
	}

	private int askDays() {
		return Integer.parseInt(JOptionPane.showInputDialog("Enter the number of days:"));
	}

	private int askBasePrice() {
		return Integer.parseInt(JOptionPane.showInputDialog("Enter the product's base price:"));
	}

	private KortingStrategyType askKorting(String id, Winkel winkel) {
		return (KortingStrategyType) JOptionPane.showInputDialog(null, "Welke korting?", "Korting", JOptionPane.QUESTION_MESSAGE, null, KortingStrategyType.values(), null);
	}

	private void addItem(Winkel winkel) {
		String title = askTitle();
		String id = askId();
		Object choice = askType();
		int basisPrijs = askBasePrice();
		ProductType type = (ProductType) choice;
		Product product = null;
		try {
			switch (type) {
			case FILM:
				product = new Film(id, title, basisPrijs);
				break;
			case MUZIEK:
				product = new Muziek(id, title, basisPrijs);
				break;
			case SPEL:
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
		String id = askId();
		JOptionPane.showMessageDialog(null, winkel.getTitel(id));
	}

	private void showItems(Winkel winkel) {
		JOptionPane.showMessageDialog(null, winkel);
	}

	private void showPrice(Winkel winkel) {
		String id = askId();
		KortingStrategyType type = askKorting(id, winkel);
		int dagen = askDays();
		winkel.setKorting(id, type);
		JOptionPane.showMessageDialog(null, "Huurprijs: " + winkel.getHuurPrijs(id, dagen) + " euro.");
	}

	private void rentProduct(Winkel winkel) {
		String id = JOptionPane.showInputDialog("Enter the id:");
		KortingStrategyType type = askKorting(id, winkel);
		int dagen = askDays();
		double prijs = winkel.getHuurPrijs(id, dagen);
		winkel.setKorting(id, type);
		JOptionPane.showMessageDialog(null, "Product " + id + " wordt uitgeleend voor " + dagen + ".\nPrijs: " + prijs + " euro");
		try {
			winkel.leenProductUit(id);
		} catch (DomainException e) {
			// TODO Auto-generated catch block

			return;
		}
	}

	private void returnProduct(Winkel winkel) {
		String id = JOptionPane.showInputDialog("Enter id:");
		boolean beschadigd = (0 == JOptionPane.showOptionDialog(null, "Is the product damaged?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null));
		try {
			winkel.brengProductTerug(id, beschadigd);
		} catch (DomainException e) {
			// TODO Auto-generated catch block
			return;
		}
	}

	private void repairProduct(Winkel winkel) {
		String id = JOptionPane.showInputDialog("Enter id:");
		boolean hersteld;
		try {
			hersteld = winkel.herstelProduct(id);
		} catch (DomainException e) {
			// TODO Auto-generated catch block
			return;
		}
		double herstelPrijs = winkel.getHerstelPrijs(id);
		if(hersteld)
			JOptionPane.showMessageDialog(null, "Product is gerepareerd\n Herstelprijs: " + herstelPrijs);
		else
			JOptionPane.showMessageDialog(null, "Product is niet gerepareerd en wordt verwijderd\n Herstelprijs: " + herstelPrijs);
	}
}
