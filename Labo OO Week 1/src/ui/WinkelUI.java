package ui;

import javax.swing.JOptionPane;

import domain.Winkel;

public class WinkelUI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Winkel winkel = new Winkel();
		WinkelUI ui = new WinkelUI();

		ui.show(winkel);
	}

	public void show(Winkel winkel) {
		String menu = "1. Add item\n2. Show item\n3. Show rental price\n\n0. Quit";
		int choice = -1;
		while (choice != 0) {
			String choiceString = JOptionPane.showInputDialog(menu);
			choice = Integer.parseInt(choiceString);
			if (choice == 1) {
				addItem(winkel);
			} else if (choice == 2) {
				showItem(winkel);
			} else if (choice == 3){
				showPrice(winkel);
			}
		}
	}

	private String askTitle() {
		return JOptionPane.showInputDialog("Enter the title:");
	}

	private int askId() {
		while (true) {
			try {
				return Integer.parseInt(JOptionPane.showInputDialog("Enter the id:"));
			} catch (NumberFormatException e) {}
		}
	}

	private String askType() {
		return JOptionPane.showInputDialog("Enter the type (M for movie/G for game):");
	}

	public void addItem(Winkel winkel) {
		String title = askTitle();
		int id = askId();
		String type = askType();
		winkel.addItem(title, id, type);
	}

	public void showItem(Winkel winkel){
		int id = askId();
		int idx = -1;
		boolean gevonden = false;
		if(winkel.itemIds.get(i).equals(id))
		{
			idx = i;
			gevonden = true;
		}

		if(gevonden)
		{
			JOptionPane.showMessageDialog(null, winkel.itemTitles.get(idx));
		}		
	}

	public void showPrice(Winkel winkel){
		String id = JOptionPane.showInputDialog("Enter the id:");
		int idx = -1;
		boolean gevonden = false;
		for(int i = 0; i < winkel.itemIds.size() && !gevonden; i++){
			if(winkel.itemIds.get(i).equals(id)){
				idx = i;
				gevonden = true;
			}
		}
		if(gevonden){
			String daysString = JOptionPane.showInputDialog("Enter the number of days:");
			int days = Integer.parseInt(daysString);
			JOptionPane.showMessageDialog(null, winkel.getPrice(idx,days));
		}
	}

}
