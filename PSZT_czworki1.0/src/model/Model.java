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
	 * zmienna okreœlaj¹ca iloœæ wierszy planszy
	 */
	public final int iloscWierszy = 6;
	
	/**
	 * zmienna okreœlaj¹ca iloœæ kolumn w planszy
	 */
	public final int iloscKolumn = 7;
	
	/**
	 * obiekt umo¿liwiaj¹cy korzystanie z klasy Gracz
	 */
	private Gracz gracz;
	
	/**
	 * obiekt umo¿liwij¹cy korzystanie z klasy Plansza
	 */
	private Plansza plansza;
	
	/**
	 * obiekt umo¿liaj¹cy korzystanie z klasy SztucznaInteligencja
	 */
	private SztucznaInteligencja AI;
	
	
	/**
	 * geter zmiennej plansza, s³u¿y do testów
	 * @return plansza
	 */
 	public Plansza getPlansza()
	{
		return plansza;
	}
 	
	/**
	 * Funkcja tworzy planszê, graczy i losuje pierwszego do ruchu
	 * @param tryb - trzeba okreœliæ tryb gry
	 * @return czyj ruch ma teraz nast¹piæ
	 */
 	public RodzajeGraczy nowaGra(Tryby tryb)
	{
		
		stworzPlansze(iloscWierszy, iloscKolumn);
		stworzGraczy(tryb);
		return gracz.czyjRuch();
	}
	
	/**
	 * Funkcja wywo³uje funkcjê analizaRzutu, funkcja przeznaczona dla gracza
	 * @param kolumna - do któej kolumny wrzucono ¿eton
	 * @return wspó³rzêdne w planszy ostatniego ¿etonu
	 * @throws Wyjatek - czy wrzucenie ¿etonu spowodowa³o remis, wygran¹ lub b³¹d
	 */
	public Wspolrzedne wrzucZeton(int kolumna)throws Wyjatek
	{
		return analizaRzutu(kolumna);
	}
	
	/**
	 * Funkcja wywo³uje funkcjê analizaRzutu, funkcja przeznaczona dla AI
	 * @param kolumna - do któej kolumny wrzucono ¿eton
	 * @return wspó³rzêdne w planszy ostatniego ¿etonu
	 * @throws Wyjatek - czy wrzucenie ¿etonu spowodowa³o remis, wygran¹ lub b³¹d
	 */
	public Wspolrzedne wrzucZeton()throws Wyjatek
	{
		return analizaRzutu(AI.obliczanieRuchu());
	}
	
	/**
	 * Funkcja zwraca informacjê czy teraz gra cz³owiek czy komputer
	 * @return enum RodzajeGraczy
	 */
	public RodzajeGraczy czyjRuch()
	{
		return gracz.czyjRuch();
	}
	
	/**
	 * Funkcja zwraca informacjê czy teraz gra gracz1 czy gracz2
	 * @return ktoryGracz
	 */
	public int ktoryGracz()
	{
		return gracz.getKtoryGracz();
	}
	
	/**
	 * Funkcja zwraca informacjê czy wrzucenie ¿etonu do danej kolumny jest dozwolone
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
	 * Konstruktor klasy model, tworz¹cy obiekt AI
	 */
 	public Model()
 	{
 		AI=new SztucznaInteligencja();
 	}
 	
	/**
	 * Funkcja tworz¹ca plansze zale¿nie od informacji w argumencie
	 * @param ileWierszy
	 * @param ileKolumn
	 */
 	private void stworzPlansze(int ileWierszy, int ileKolumn)
	{	
		plansza = new Plansza(ileWierszy, ileKolumn);
		return;
	}	
	
 	/**
 	 * Funkcja tworz¹ca i losuj¹ca graczy zale¿nie od danego trybu
 	 * @param tryb
 	 */
	private void stworzGraczy(Tryby tryb)
	{	
		gracz= new Gracz(tryb);
		gracz.losujGracza();
		return;
	}	
	
	/**
	 * Funkcja sprawdzaj¹ca czy wrzucienie ¿etonu do danej kolumny zakoñczy³o siê wygran¹
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
	 * Funkcja analizuj¹ca wrzucenie ¿etonu do danej kolumny i jej potencjalne skutki
	 * @param kolumna
	 * @return wspó³rzêdne danego rzutu w planszy
	 * @throws Wyjatek - b³¹d lub koniec gry w postaci wygranej lub zwyciêstwa
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


