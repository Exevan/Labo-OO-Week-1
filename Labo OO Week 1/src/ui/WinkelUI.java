package ui;

import java.util.Date;

import javax.swing.JOptionPane;

import util.IO;
import domain.CD;
import domain.Game;
import domain.Item;
import domain.Movie;
import domain.Winkel;

public class WinkelUI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//IO.readInventory();
		Winkel winkel = new Winkel();
		WinkelUI ui = new WinkelUI();

		ui.show(winkel);
		IO.writeInventory(winkel.getInventory());
	}

	public void show(Winkel winkel) {
		try {
			String menu = MENU;
			boolean go = true;
			while (go) {
				String choiceString = JOptionPane.showInputDialog(menu);
				int choice = Integer.parseInt(choiceString);
				if (choice == 1) {
					addItem(winkel);
				} else if (choice == 2) {
					showItem(winkel);
				} else if (choice == 3) {
					showPrice(winkel);
				} else if (choice == 4) {
					showInventory(winkel);
				} else if (choice == 5) {
					showStatus(winkel);
				} else if (choice == 6) {
					lendItem(winkel);
				} else if (choice == 7) {
					returnItem(winkel);
				} else if (choice == 0) {
					go = false;
				} else {
					show(winkel);
				}
			}
		} catch (NumberFormatException e) {
			show(winkel);
		}
	}

	private void returnItem(Winkel winkel) {
		int id = askId();
		Item item = winkel.getItem(id);
		if(item != null){
			if(item.isLeant()){
				JOptionPane.showMessageDialog(null, "Thank you for returning your Item!");
				item.setReturned();
			}
		}else{
			JOptionPane.showMessageDialog(null, "A Item with that id was not found.");
		}
	}

	private void lendItem(Winkel winkel) {
		int id = askId();
		Item item = winkel.getItem(id);
		if(item != null){
			if(item.isLeant()){
				JOptionPane.showMessageDialog(null, "This item is currently leant. You can not lend it.");
			}else{
				item.setRentalDay(new Date());
				JOptionPane.showMessageDialog(null, "You have now leant the item.");
			}
		}else{
			JOptionPane.showMessageDialog(null, "A Item with that id was not found.");
		}
	}

	private void showStatus(Winkel winkel) {
		int id = askId();
		Item item = winkel.getItem(id);
		if(item != null){
			if(item.isLeant()){
				JOptionPane.showMessageDialog(null, "This item is currently leant.");
			}else{
				JOptionPane.showMessageDialog(null, "This item is available.");
			}
		}else{
			JOptionPane.showMessageDialog(null, "A Item with that id was not found.");
		}
	}

	private String askTitle() {
		return JOptionPane.showInputDialog("Enter the title:");
	}

	private String askType() {
		return JOptionPane
				.showInputDialog("Enter the type (M for movie/G for game/C for CD):");
	}

	private int askId() {
		while (true) {
			try {
				return Integer.parseInt(JOptionPane
						.showInputDialog("Enter the id:"));
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a number");
			}
		}
	}

	private int askDays() {
		while (true) {
			try {
				return Integer.parseInt(JOptionPane
						.showInputDialog("Enter the number of days:"));
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a number");
			}
		}

	}

	public void addItem(Winkel winkel) {
		String title = askTitle();
		int id = askId();
		String type = askType();
		Item item = null;
		switch (type) {
		case "M":
			item = new Movie(title, id);
			break;
		case "G":
			item = new Game(title, id);
			break;
		case "C":
			item = new CD(title, id);
			break;
		}
		winkel.addItem(item);
	}

	public void showItem(Winkel winkel) {
		int id = askId();
		String title = winkel.getTitle(id);
		if (!title.equals(""))
			JOptionPane.showMessageDialog(null, title);
		else
			JOptionPane.showMessageDialog(null, "Item id not found");
	}

	public void showPrice(Winkel winkel) {
		int id = askId();
		int days = askDays();
		double price = winkel.getPrice(id, days);
		if (price != -1)
			JOptionPane.showMessageDialog(null, price);
		else
			JOptionPane.showMessageDialog(null, "Item id not found");
	}

	public void showInventory(Winkel winkel) {
		String items = "";
		for (String item : winkel.getTitles()) {
			items += (item + "\n");
		}
		JOptionPane.showMessageDialog(null, items);
	}

	public static final String MENU = "1. Add item\n2. Show item\n3. Show rental price\n4. Show inventory\n5. Show item status\n6. Lend item\n7. Return item\n\n0. Quit";
}
