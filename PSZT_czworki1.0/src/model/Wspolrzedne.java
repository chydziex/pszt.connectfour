package model;

public class Wspolrzedne {
	
	/**
	 * zmienna okreœlaj¹capionowe po³o¿enie wspó³rzêdnej
	 */
	private int wiersz;
	
	/**
	 * zmienna okreœlaj¹ca poziome po³o¿enie wspó³rzêdnej
	 */
	private int kolumna;
	
	/**
	 * Konstruktor klasy Wspolrzedne
	 * @param wiersz
	 * @param kolumna
	 */
	public Wspolrzedne(int wiersz, int kolumna)
	{
		this.wiersz=wiersz;
		this.kolumna=kolumna;
	}
	
	/**
	 * geter do zmiennej wiersz
	 * @return wiersz (int)
	 */
	public int getWiersz() {
		return wiersz;
	}
	
	/**
	 * seter do zmiennej wiersz
	 * @param wiersz
	 */
	public void setWiersz(int wiersz) {
		this.wiersz = wiersz;
	}
	
	/**
	 * geter do zmiennej kolumna
	 * @return kolumna (int)
	 */
	public int getKolumna() {
		return kolumna;
	}
	
	/**
	 * seter do zmiennej kolumna
	 * @param kolumna
	 */
	public void setKolumna(int kolumna) {
		this.kolumna = kolumna;
	}
}
