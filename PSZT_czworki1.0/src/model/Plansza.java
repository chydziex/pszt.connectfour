package model;

import model.Pole.Przynaleznosc;

public class Plansza {
	
	private Pole[][] plansza;
	private int licznik;
	private int iloscWierszy;
	private int iloscKolumn;
	private Wspolrzedne aktualnaWspolrzedna;
	
	
	/**
	 * Konstruktor klasy Plansza, zape�nia pola tablicy obiektami klasy Pole, przechowuje rozmiar tablicy i zeruje reszt� zmiennych
	 * @param ileWierszy
	 * @param ileKolumn
	 */
	Plansza(int ileWierszy, int ileKolumn)
	{
		licznik=0;
		iloscWierszy=ileWierszy;
		iloscKolumn=ileKolumn;
		aktualnaWspolrzedna=new Wspolrzedne(0,0);
		
		plansza= new Pole[iloscWierszy][iloscKolumn];
		for(int n=0;n<iloscWierszy;++n)
			for(int m=0;m<iloscKolumn;++m)
			{
				plansza[n][m]= new Pole(n,m);
			}
	}
	
	
	/**
	 * geter do obiektu aktualna wsp�rz�dna
	 * @return
	 */
	public Wspolrzedne getAktualnaWspolrzedna()
	{
		return aktualnaWspolrzedna;
	}
	

	/** 
	 * Funkcja sprawdza gdzie znajduje si� �eton wrzucony do danej kolumny przez danego gracza, zapisuje zmian� pola
	 * i przelicza czy tworzy ci�g 4 �eton�w w pionie, poziomie, i skosach (lewy i prawy)
	 * @param kolumna
	 * @param ktoryGracz
	 * @return informacja czy rzut ko�czy si� wygran�
	 */
	public boolean sprawdzCzyWygrana(int kolumna, int ktoryGracz)
	{
		Przynaleznosc gracz;
		wyliczAktualnaWspolrzedna(kolumna);
		gracz = zmienPole(ktoryGracz);
		if(policzPion(gracz)>=4||policzPoziom(gracz)>=4||policzLewoSkos(gracz)>=4||policzPrawoSkos(gracz)>=4)
			return true;
		else
			return false;
	}
	
	
	/**
	 * Funkcja por�wnuje ilo�� wrzuconych �eton�w z rozmiarem planszy
	 * @return informacja czy jest remis
	 */
	public boolean sprawdzCzyRemis()
	{
		++licznik;
		if(iloscKolumn*iloscWierszy==licznik)
			return true;
		else
			return false;
	}
	
	/**
	 * Funkcja srawdza czy dana kolumna nie jest ju� pe�na i czy rzut nie jest wykonany poza plansz� 
	 * @param kolumna
	 * @return czy ruch jest dozowolony
	 */
	public boolean czyRuchJestDozwolony(int kolumna)
	{
		if(kolumna<0||kolumna>=iloscKolumn)
			return false;
		
		if((plansza[0][kolumna].getZeton()==Przynaleznosc.BRAK))
			return true;
		else
			return false;
	}
	
	/**
	 * Funkcja wy�wietla tabel� przynale�no�ci w edytorze, do testowania
	 */
	public void show()
	{
		for(int n=0;n<iloscWierszy;++n)
		{
			for(int m=0;m<iloscKolumn;++m)
			{
				if(plansza[n][m].getZeton()==Przynaleznosc.BRAK)
					System.out.print("0");
				else if(plansza[n][m].getZeton()==Przynaleznosc.GRACZ1)
					System.out.print("1");
				else if(plansza[n][m].getZeton()==Przynaleznosc.GRACZ2)
					System.out.print("2");
				else
					System.out.print("7");
			}		
			System.out.println("");
		}
		return;
	}
	
	/**
	 * Funkcja wylicza ko�cowe po�o�enie �etonu w planszy po wrzuceniu do danej kolumny
	 * @param kolumna
	 */
	private void wyliczAktualnaWspolrzedna(int kolumna)
	{
		int wiersz=iloscWierszy-1;
		aktualnaWspolrzedna.setKolumna(kolumna);
		
		for(;wiersz>=0;--wiersz)
			if(plansza[wiersz][kolumna].getZeton()==Przynaleznosc.BRAK)
			{
				aktualnaWspolrzedna.setWiersz(wiersz);
				return;
			}
		
		return;
	}
	
	/**
	 * Funkcja zapisuje informacje kt�rego gracza �eton jest na polu o wsp�rz�dnych aktualnaWspolrzedna 
	 * @param ktoryGracz
	 * @return zwraca do kt�rego gracza nale�y pole
	 */
	private Przynaleznosc zmienPole(int ktoryGracz)
	{
		if(ktoryGracz==0)
		{
			plansza[aktualnaWspolrzedna.getWiersz()][aktualnaWspolrzedna.getKolumna()].setZeton(Przynaleznosc.GRACZ1);
			return Przynaleznosc.GRACZ1;
		}
		else if(ktoryGracz==1)
		{
			plansza[aktualnaWspolrzedna.getWiersz()][aktualnaWspolrzedna.getKolumna()].setZeton(Przynaleznosc.GRACZ2);
			return Przynaleznosc.GRACZ2;
		}
		else
			System.out.println("BLAD");

		return Przynaleznosc.BRAK;
	}
	
	/**
	 * Funkcja sprawdza jak d�ugi ci�g tworzy w pionie wrzucony �eton
	 * @param gracz
	 * @return jaki d�ugi tworzy ci�g
	 */
	private int policzPion(Przynaleznosc gracz)
	{
		
		int licznik=0;
		
		for(int wiersz = aktualnaWspolrzedna.getWiersz();wiersz<iloscWierszy;++wiersz)
			if(plansza[wiersz][aktualnaWspolrzedna.getKolumna()].getZeton()==gracz)
				++licznik;
			else
				return licznik;
		
		return licznik;
	}
	
	/**
	 * Funkcja sprawdza jak d�ugi ci�g tworzy w poziomie wrzucony �eton
	 * @param gracz
	 * @return jaki d�ugi tworzy ci�g
	 */
	private int policzPoziom(Przynaleznosc gracz)
	{
		int licznik=0;
		for(int kolumna = aktualnaWspolrzedna.getKolumna();kolumna>=0;--kolumna)
			if(plansza[aktualnaWspolrzedna.getWiersz()][kolumna].getZeton()==gracz)
				++licznik;
			else
				break;
		--licznik;
		for(int kolumna = aktualnaWspolrzedna.getKolumna();kolumna<iloscKolumn;++kolumna)
			if(plansza[aktualnaWspolrzedna.getWiersz()][kolumna].getZeton()==gracz)
				++licznik;
			else
				return licznik;
		
		return licznik;
		}
	
	/**
	 * Funkcja sprawdza jak d�ugi ci�g tworzy na lewoskos wrzucony �eton
	 * @param gracz
	 * @return jaki d�ugi tworzy ci�g
	 */
	private int policzLewoSkos(Przynaleznosc gracz)
	{
		int licznik=0;
		int kolumna = aktualnaWspolrzedna.getKolumna();
		int wiersz = aktualnaWspolrzedna.getWiersz();
		for(;kolumna>=0 && wiersz>=0;--kolumna,--wiersz)
			if(plansza[wiersz][kolumna].getZeton()==gracz)
				++licznik;
			else
				break;
		--licznik;
		kolumna = aktualnaWspolrzedna.getKolumna();
		wiersz = aktualnaWspolrzedna.getWiersz();
		for(;kolumna<iloscKolumn && wiersz<iloscWierszy;++kolumna,++wiersz)
			if(plansza[wiersz][kolumna].getZeton()==gracz)
				++licznik;
			else
				return licznik;
		
		return licznik;
		}
	
	/**
	 * Funkcja sprawdza jak d�ugi ci�g tworzy na prawoskos wrzucony �eton
	 * @param gracz
	 * @return jaki d�ugi tworzy ci�g
	 */
	private int policzPrawoSkos(Przynaleznosc gracz)
	{
		int licznik=0;
		int kolumna = aktualnaWspolrzedna.getKolumna();
		int wiersz = aktualnaWspolrzedna.getWiersz();
		for(;kolumna<iloscKolumn && wiersz>=0;++kolumna,--wiersz)
			if(plansza[wiersz][kolumna].getZeton()==gracz)
				++licznik;
			else
				break;
		--licznik;
		kolumna = aktualnaWspolrzedna.getKolumna();
		wiersz = aktualnaWspolrzedna.getWiersz();
		for(;kolumna>=0 && wiersz<iloscWierszy;--kolumna,++wiersz)
			if(plansza[wiersz][kolumna].getZeton()==gracz)
				++licznik;
			else
				return licznik;
		
		return licznik;
		}
	
}
