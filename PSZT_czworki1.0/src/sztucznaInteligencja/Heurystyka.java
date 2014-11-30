package sztucznaInteligencja;

import model.Plansza;
import model.Przynaleznosc;

abstract public class Heurystyka 
{	
	protected final Przynaleznosc AI;
	protected final Przynaleznosc przeciwnik;
	
	public Heurystyka(Przynaleznosc AI)
	{
		this.AI= AI;
		if(AI == Przynaleznosc.GRACZ1)
			przeciwnik = Przynaleznosc.GRACZ2;
		else
			przeciwnik = Przynaleznosc.GRACZ1;
	}
	
	/** Zwraca wartosc ruchu/stanu gry bez uwzglednienia wagi. */
	public int getWartosc(final Plansza makietaRuchu, int kolumna)
	{
		return obliczanieWartosci(makietaRuchu, kolumna);
	}
	
	/** Oblicza wartosc ruchu/stanu gry bez uwzglednienia wagi danej heurystyki. */
	abstract protected int obliczanieWartosci(final Plansza makietaRuchu, int kolumna);
	
}
