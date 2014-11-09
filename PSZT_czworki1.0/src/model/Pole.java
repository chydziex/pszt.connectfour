package model;

class Pole {
	
	/**
	 * enum definuj�cy czy lub do kogo nale�y zeton znajduj�cy si� na danym polu
	 * oparty o zmienn� ktoryGracz w klasie Gracz
	 *
	 */
	enum Przynaleznosc {BRAK, GRACZ1, GRACZ2}
	
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
		zeton= Przynaleznosc.BRAK;
		wspolrzedna = new Wspolrzedne(wiersz, kolumna);
	}
	
	/**
	 * geter zmiennej wspolrzedna
	 * @return wsp�rz�dna pola (wspolrzedna)
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
	
}
