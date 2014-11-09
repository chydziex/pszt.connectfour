package model;

import model.Pole.Przynaleznosc;

public class Plansza {
	
	private Pole[][] plansza;
	private int licznik;
	private int iloscWierszy;
	private int iloscKolumn;
	private Wspolrzedne aktualnaWspolrzedna;
	
	
	/**
	 * Konstruktor klasy Plansza, zape³nia pola tablicy obiektami klasy Pole, przechowuje rozmiar tablicy i zeruje resztê zmiennych
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
	 * geter do obiektu aktualna wspó³rzêdna
	 * @return
	 */
	public Wspolrzedne getAktualnaWspolrzedna()
	{
		return aktualnaWspolrzedna;
	}
	

	/** 
	 * Funkcja sprawdza gdzie znajduje siê ¿eton wrzucony do danej kolumny przez danego gracza, zapisuje zmianê pola
	 * i przelicza czy tworzy ci¹g 4 ¿etonów w pionie, poziomie, i skosach (lewy i prawy)
	 * @param kolumna
	 * @param ktoryGracz
	 * @return informacja czy rzut koñczy siê wygran¹
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
	 * Funkcja porównuje iloœæ wrzuconych ¿etonów z rozmiarem planszy
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
	 * Funkcja srawdza czy dana kolumna nie jest ju¿ pe³na i czy rzut nie jest wykonany poza plansz¹ 
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
	 * Funkcja wyœwietla tabelê przynale¿noœci w edytorze, do testowania
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
	 * Funkcja wylicza koñcowe po³o¿enie ¿etonu w planszy po wrzuceniu do danej kolumny
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
	 * Funkcja zapisuje informacje którego gracza ¿eton jest na polu o wspó³rzêdnych aktualnaWspolrzedna 
	 * @param ktoryGracz
	 * @return zwraca do którego gracza nale¿y pole
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
	 * Funkcja sprawdza jak d³ugi ci¹g tworzy w pionie wrzucony ¿eton
	 * @param gracz
	 * @return jaki d³ugi tworzy ci¹g
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
	 * Funkcja sprawdza jak d³ugi ci¹g tworzy w poziomie wrzucony ¿eton
	 * @param gracz
	 * @return jaki d³ugi tworzy ci¹g
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
	 * Funkcja sprawdza jak d³ugi ci¹g tworzy na lewoskos wrzucony ¿eton
	 * @param gracz
	 * @return jaki d³ugi tworzy ci¹g
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
	 * Funkcja sprawdza jak d³ugi ci¹g tworzy na prawoskos wrzucony ¿eton
	 * @param gracz
	 * @return jaki d³ugi tworzy ci¹g
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
