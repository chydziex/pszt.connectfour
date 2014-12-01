package model;

class Pole implements Cloneable {
	
	
	/**
	 * zmienna okre�laj�c� po�o�enie danego pola na planszy
	 */
	Wspolrzedne wspolrzedna;
	
	/**
	 * zmienna okre�laj�ca do kogo nale�y �eton na danym polu
	 */
	Przynaleznosc zeton;
	
	/**
	 * konstuktor klasy Pole
	 * @param wiersz
	 * @param kolumna
	 */
	Pole(int wiersz, int kolumna)
	{
		zeton = Przynaleznosc.BRAK;
		wspolrzedna = new Wspolrzedne(wiersz, kolumna);
	}
	
	/** Konstruktor do klonowania. */
	Pole(Wspolrzedne wspol)
	{
		zeton = Przynaleznosc.BRAK;
		wspolrzedna = wspol;
	}
	
	/**
	 * geter zmiennej wspolrzedna
	 * @return wsp�rz�dna pola (wspolrzedna)
	 */
	public Wspolrzedne getWspolrzedna() {
		return wspolrzedna;
	}
	
	/**
	 * geter zmiennej zeton
	 * @return przynale�no�� �etonu (zeton)
	 */
	public Przynaleznosc getZeton() {
		return zeton;
	}

	/**
	 * seter zmiennej zeton
	 * @param zeton
	 */
	public void setZeton(Przynaleznosc zeton) {
		this.zeton = zeton;
	}
	
	//Pomocniczno do klonowania
	public String toString()
	{
		return new String(wspolrzedna.toString() + " " + zeton);
	}
	
	@Override
    public Object clone() throws CloneNotSupportedException {
        Wspolrzedne kopiaWspolrzednych = (Wspolrzedne) wspolrzedna.clone();
        Pole kopiaPola = new Pole(kopiaWspolrzednych);
        kopiaPola.zeton = zeton;
        return kopiaPola;
    }
}
