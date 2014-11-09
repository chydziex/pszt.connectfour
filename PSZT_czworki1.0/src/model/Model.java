package model;

import sytuacjeWyjatkowe.*;
/*
import sytuacjeWyjatkowe.Wyjatek;
import sytuacjeWyjatkowe.WyjatekRemis;
import sytuacjeWyjatkowe.WyjatekRuchNiedozwolony;
import sytuacjeWyjatkowe.WyjatekWygrana;
*/

public class Model{// extends Throwable {
	

	/**
	 * zmienna okre�laj�ca ilo�� wierszy planszy
	 */
	public final int iloscWierszy = 6;
	
	/**
	 * zmienna okre�laj�ca ilo�� kolumn w planszy
	 */
	public final int iloscKolumn = 7;
	
	/**
	 * obiekt umo�liwiaj�cy korzystanie z klasy Gracz
	 */
	private Gracz gracz;
	
	/**
	 * obiekt umo�liwij�cy korzystanie z klasy Plansza
	 */
	private Plansza plansza;
	
	/**
	 * obiekt umo�liaj�cy korzystanie z klasy SztucznaInteligencja
	 */
	private SztucznaInteligencja AI;
	
	
	/**
	 * geter zmiennej plansza, s�u�y do test�w
	 * @return plansza
	 */
 	public Plansza getPlansza()
	{
		return plansza;
	}
 	
	/**
	 * Funkcja tworzy plansz�, graczy i losuje pierwszego do ruchu
	 * @param tryb - trzeba okre�li� tryb gry
	 * @return czyj ruch ma teraz nast�pi�
	 */
 	public RodzajeGraczy nowaGra(Tryby tryb)
	{
		
		stworzPlansze(iloscWierszy, iloscKolumn);
		stworzGraczy(tryb);
		return gracz.czyjRuch();
	}
	
	/**
	 * Funkcja wywo�uje funkcj� analizaRzutu, funkcja przeznaczona dla gracza
	 * @param kolumna - do kt�ej kolumny wrzucono �eton
	 * @return wsp�rz�dne w planszy ostatniego �etonu
	 * @throws Wyjatek - czy wrzucenie �etonu spowodowa�o remis, wygran� lub b��d
	 */
	public Wspolrzedne wrzucZeton(int kolumna)throws Wyjatek
	{
		return analizaRzutu(kolumna);
	}
	
	/**
	 * Funkcja wywo�uje funkcj� analizaRzutu, funkcja przeznaczona dla AI
	 * @param kolumna - do kt�ej kolumny wrzucono �eton
	 * @return wsp�rz�dne w planszy ostatniego �etonu
	 * @throws Wyjatek - czy wrzucenie �etonu spowodowa�o remis, wygran� lub b��d
	 */
	public Wspolrzedne wrzucZeton()throws Wyjatek
	{
		return analizaRzutu(AI.obliczanieRuchu());
	}
	
	/**
	 * Funkcja zwraca informacj� czy teraz gra cz�owiek czy komputer
	 * @return enum RodzajeGraczy
	 */
	public RodzajeGraczy czyjRuch()
	{
		return gracz.czyjRuch();
	}
	
	/**
	 * Funkcja zwraca informacj� czy teraz gra gracz1 czy gracz2
	 * @return ktoryGracz
	 */
	public int ktoryGracz()
	{
		return gracz.getKtoryGracz();
	}
	
	/**
	 * Funkcja zwraca informacj� czy wrzucenie �etonu do danej kolumny jest dozwolone
	 * @param kolumna
	 * @return true lub false
	 */
	public boolean czyRuchJestDozwolony(int kolumna)
	{
		return plansza.czyRuchJestDozwolony(kolumna);
	}
	
	/**
	 * Funkcja zwraca tryb aktualnej gry
	 * @return enum Tryby
	 */
	public Tryby getTrybGry()
	{
		return gracz.getTrybGry();
	}
	
	
	/**
	 * Konstruktor klasy model, tworz�cy obiekt AI
	 */
 	public Model()
 	{
 		AI=new SztucznaInteligencja();
 	}
 	
	/**
	 * Funkcja tworz�ca plansze zale�nie od informacji w argumencie
	 * @param ileWierszy
	 * @param ileKolumn
	 */
 	private void stworzPlansze(int ileWierszy, int ileKolumn)
	{	
		plansza = new Plansza(ileWierszy, ileKolumn);
		return;
	}	
	
 	/**
 	 * Funkcja tworz�ca i losuj�ca graczy zale�nie od danego trybu
 	 * @param tryb
 	 */
	private void stworzGraczy(Tryby tryb)
	{	
		gracz= new Gracz(tryb);
		gracz.losujGracza();
		return;
	}	
	
	/**
	 * Funkcja sprawdzaj�ca czy wrzucienie �etonu do danej kolumny zako�czy�o si� wygran�
	 * @param kolumna
	 * @return true lub false
	 */
	private boolean sprawdzCzyWygrana(int kolumna)
	{
		if(plansza.sprawdzCzyWygrana(kolumna, gracz.getKtoryGracz()))
			return true;
		else
			return false;
	}
	
	/**
	 * Funkcja analizuj�ca wrzucenie �etonu do danej kolumny i jej potencjalne skutki
	 * @param kolumna
	 * @return wsp�rz�dne danego rzutu w planszy
	 * @throws Wyjatek - b��d lub koniec gry w postaci wygranej lub zwyci�stwa
	 */
	private Wspolrzedne analizaRzutu(int kolumna) throws Wyjatek
	{
		if(!czyRuchJestDozwolony(kolumna))
		{
			throw new WyjatekRuchNiedozwolony();
		}
		if(sprawdzCzyWygrana(kolumna))
		{
			throw new WyjatekWygrana();
		}
		if(plansza.sprawdzCzyRemis())
		{
			throw new WyjatekRemis();
		}
		gracz.zmienGracza();
		return plansza.getAktualnaWspolrzedna() ;
	}
}


