package sztucznaInteligencja;

import model.Plansza;




abstract public class Heurystyka 
{	
	
	
	/** Zwraca wartosc ruchu/stanu gry bez uwzglednienia wagi. */
	public int getWartosc(final Plansza makietaRuchu, int kolumna, int ktoryGracz)
	{
		
		return obliczanieWartosci(makietaRuchu, kolumna, ktoryGracz);
	}
	
	/** Oblicza wartosc ruchu/stanu gry bez uwzglednienia wagi danej heurystyki. */
	abstract protected int obliczanieWartosci(final Plansza makietaRuchu, int kolumna, int ktoryGracz);
	
	protected void show(Ciagi mojCiag)
	{
		
		System.out.println("Lewo skos dol: " + mojCiag.getLewoSkosDol());
		System.out.println("Lewo skos gora: " + mojCiag.getLewoSkosGora());
		System.out.println("Pion: " + mojCiag.getPion());
		System.out.println("Lewo poziom: " + mojCiag.getPoziomLewo());
		System.out.println("Prawo poziom: " + mojCiag.getPoziomPrawo());
		System.out.println("Prawo skos dol: " + mojCiag.getPrawoSkosDol());
		System.out.println("Prawo skos gora: " + mojCiag.getPrawoSkosGora());
		
	}
	
}
