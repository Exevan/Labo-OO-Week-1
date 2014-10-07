package db;

import java.io.File;

import domain.Winkel;

public class WinkelDatabaseHandler {

	private File bestand;
	private Winkel winkel;

	private WinkelDatabaseLezer wbl = null;
	private WinkelDatabaseSchrijver wbs = null;

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

	public void setWinkelDatabaseLezer(WinkelDatabaseLezer lezer) {
		this.wbl = lezer;
	}

	public void setWinkelDatabaseSchrijver(WinkelDatabaseSchrijver schrijver) {
		this.wbs = schrijver;
	}

	public void schrijf() throws DbException {
		wbs.schrijf();
	}

	public void lees() throws DbException {
		wbl.lees();
	}

}
