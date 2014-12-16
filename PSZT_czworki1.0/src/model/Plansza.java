package model;

import sztucznaInteligencja.Ciagi;

public class Plansza implements Cloneable {
	
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
	public Plansza(int ileWierszy, int ileKolumn)
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
	
	public Ciagi sasiednieCiagi(Przynaleznosc gracz, int kolumna)
	{
		Ciagi paczka= new Ciagi(gracz);
		wyliczAktualnaWspolrzedna( kolumna);
		
		paczka.setPion(policzPion(gracz) - 1);
		paczka.setPoziomLewo(policzPoziomLewo(gracz));
		paczka.setPoziomPrawo(policzPoziomPrawo(gracz));
		paczka.setLewoSkosGora(policzLewoSkosGora(gracz));
		paczka.setLewoSkosDol(policzLewoSkosDol(gracz));
		paczka.setPrawoSkosGora(policzPrawoSkosGora(gracz));
		paczka.setPrawoSkosDol(policzPrawoSkosDol(gracz));
		
		return paczka;
	}
	
	@Override
    public Object clone() throws CloneNotSupportedException {
		Plansza kopiaPlanszy = new Plansza(Model.iloscWierszy, Model.iloscKolumn);
        Wspolrzedne kopiaWspolrzednych = (Wspolrzedne) aktualnaWspolrzedna.clone();
        kopiaPlanszy.aktualnaWspolrzedna = kopiaWspolrzednych;
        kopiaPlanszy.licznik = licznik;
        for(int i = 0; i < iloscWierszy; i++)
        	for(int k = 0; k < iloscKolumn; k++)
        	{
        		Pole kopiaPola = (Pole) plansza[i][k].clone();
        		kopiaPlanszy.plansza[i][k] = kopiaPola;
        	}
        return kopiaPlanszy;
    }
	
	//Funkcja tylko do testowania klonowania.
	public void pisz()
	{
		System.out.println("Pola:");
		for(int i = 0; i < iloscWierszy; i++)
		{
        	for(int k = 0; k < iloscKolumn; k++)
        		System.out.print(plansza[i][k] + ",  ");
        	System.out.println();
		}
		System.out.println("licznik: " + licznik);
		System.out.println("iloscWierszy: " + iloscWierszy);
		System.out.println("iloscKolumn: " + iloscKolumn);
		System.out.println("aktualnaWspolrzedna: " + aktualnaWspolrzedna);
	}
	
	//Funkcja tylko do testowania klonowania
	public void zmien()
	{
		for(int i = 0; i < iloscWierszy; i++)
        	for(int k = 0; k < iloscKolumn; k++)
        	{
        		plansza[i][k].wspolrzedna.setWiersz(6);
        		plansza[i][k].wspolrzedna.setKolumna(5);
        	}
		licznik = 12;
		aktualnaWspolrzedna.setWiersz(4);
		aktualnaWspolrzedna.setKolumna(4);
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
		
		for(int wiersz = (aktualnaWspolrzedna.getWiersz() + 1) ;wiersz<iloscWierszy;++wiersz)
			if(plansza[wiersz][aktualnaWspolrzedna.getKolumna()].getZeton()==gracz)
				++licznik;
			else
				break;
		++licznik;
		return licznik;
	}
	
	/**
	 * Funkcja sprawdza jak d³ugi ci¹g tworzy w poziomie wrzucony ¿eton
	 * @param gracz
	 * @return jaki d³ugi tworzy ci¹g
	 */
	private int policzPoziom(Przynaleznosc gracz)
	{
		int licznik = policzPoziomLewo(gracz) + policzPoziomPrawo(gracz) + 1;
		return licznik;
		}
	
	private int policzPoziomLewo(Przynaleznosc gracz)
	{
		int licznik=0;
		for(int kolumna = ( aktualnaWspolrzedna.getKolumna() - 1);kolumna>=0;--kolumna)
			if(plansza[aktualnaWspolrzedna.getWiersz()][kolumna].getZeton()==gracz)
				++licznik;
			else
				break;
		
		return licznik;
	}
	
	private int policzPoziomPrawo(Przynaleznosc gracz)
	{
		int licznik=0;
		for(int kolumna = ( aktualnaWspolrzedna.getKolumna() + 1);kolumna<iloscKolumn;++kolumna)
			if(plansza[aktualnaWspolrzedna.getWiersz()][kolumna].getZeton()==gracz)
				++licznik;
			else
				break;
		
		return licznik;
	}
	
	/**
	 * Funkcja sprawdza jak d³ugi ci¹g tworzy na lewoskos wrzucony ¿eton
	 * @param gracz
	 * @return jaki d³ugi tworzy ci¹g
	 */
	private int policzLewoSkos(Przynaleznosc gracz)
	{
		int licznik = policzLewoSkosGora(gracz) + policzLewoSkosDol(gracz) + 1;		
		return licznik;
		}
	
	private int policzLewoSkosGora(Przynaleznosc gracz)
	{
		int licznik=0;
		int kolumna = (aktualnaWspolrzedna.getKolumna() -1 );
		int wiersz = (aktualnaWspolrzedna.getWiersz() - 1 );
		for(;kolumna>=0 && wiersz>=0;--kolumna,--wiersz)
			if(plansza[wiersz][kolumna].getZeton()==gracz)
				++licznik;
			else
				break;
		
		return licznik;
	}
	
	private int policzLewoSkosDol(Przynaleznosc gracz)
	{
		int licznik=0;
		int kolumna = ( aktualnaWspolrzedna.getKolumna() + 1);
		int wiersz = ( aktualnaWspolrzedna.getWiersz() + 1);
		for(;kolumna<iloscKolumn && wiersz<iloscWierszy;++kolumna,++wiersz)
			if(plansza[wiersz][kolumna].getZeton()==gracz)
				++licznik;
			else
				break;
		return licznik;
	}
	
	
	/**
	 * Funkcja sprawdza jak d³ugi ci¹g tworzy na prawoskos wrzucony ¿eton
	 * @param gracz
	 * @return jaki d³ugi tworzy ci¹g
	 */
	private int policzPrawoSkos(Przynaleznosc gracz)
	{
		int licznik = policzPrawoSkosGora(gracz) + policzPrawoSkosDol(gracz) + 1;
		return licznik;
		}
	
	private int policzPrawoSkosGora(Przynaleznosc gracz)
	{
		int licznik=0;
		int kolumna = ( aktualnaWspolrzedna.getKolumna() + 1 );
		int wiersz = ( aktualnaWspolrzedna.getWiersz() - 1);
		for(;kolumna<iloscKolumn && wiersz>=0;++kolumna,--wiersz)
			if(plansza[wiersz][kolumna].getZeton()==gracz)
				++licznik;
			else
				break;
		return licznik;
	}
	
	private int policzPrawoSkosDol(Przynaleznosc gracz)
	{
		int licznik=0;
		int kolumna = ( aktualnaWspolrzedna.getKolumna() - 1 );
		int wiersz = ( aktualnaWspolrzedna.getWiersz() + 1 );
		for(;kolumna>=0 && wiersz<iloscWierszy;--kolumna,++wiersz)
			if(plansza[wiersz][kolumna].getZeton()==gracz)
				++licznik;
			else
				break;
		return licznik;
	}
	

	
}
