package model;

class Pole {
	
	/**
	 * enum definuj¹cy czy lub do kogo nale¿y zeton znajduj¹cy siê na danym polu
	 * oparty o zmienn¹ ktoryGracz w klasie Gracz
	 *
	 */
	enum Przynaleznosc {BRAK, GRACZ1, GRACZ2}
	
	/**
	 * zmienna okreœlaj¹c¹ po³o¿enie danego pola na planszy
	 */
	Wspolrzedne wspolrzedna;
	
	/**
	 * zmienna okreœlaj¹ca do kogo nale¿y ¿eton na danym polu
	 */
	Przynaleznosc zeton;
	
	/**
	 * konstuktor klasy Pole
	 * @param wiersz
	 * @param kolumna
	 */
	Pole(int wiersz, int kolumna)
	{
		zeton= Przynaleznosc.BRAK;
		wspolrzedna = new Wspolrzedne(wiersz, kolumna);
	}
	
	/**
	 * geter zmiennej wspolrzedna
	 * @return wspó³rzêdna pola (wspolrzedna)
	 */
	public Wspolrzedne getWspolrzedna() {
		return wspolrzedna;
	}
	
	
	/**
	 * seter zmiennej wspolrzedna
	 * @param wspolrzedna
	 */
	public void setWspolrzedna(Wspolrzedne wspolrzedna) {
		this.wspolrzedna = wspolrzedna;
	}

	/**
	 * geter zmiennej zeton
	 * @return przynale¿noœæ ¿etonu (zeton)
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
	
}
