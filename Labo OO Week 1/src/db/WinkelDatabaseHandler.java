package db;

import java.io.File;

import domain.Winkel;

public class WinkelDatabaseHandler {

	private File bestand;
	private Winkel winkel;

	public WinkelDatabaseHandler(String filename, Winkel winkel) throws DbException {
		setBestand(filename);
		setWinkel(winkel);
	}

	public File getBestand() {
		return bestand;
	}

	private void setBestand(String filename) throws DbException {
		try {
			this.bestand = new File(filename);
		} catch (NullPointerException e) {
			throw new DbException("file not valid");
		}
	}

	public Winkel getWinkel() {
		return winkel;
	}

	private void setWinkel(Winkel winkel) {
		this.winkel = winkel;
	}

}
