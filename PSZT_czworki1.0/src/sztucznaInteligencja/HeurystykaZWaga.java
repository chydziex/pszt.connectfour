package sztucznaInteligencja;

import model.Plansza;

public class HeurystykaZWaga
{
	public HeurystykaZWaga(final Heurystyka heurystyka, int waga)
	{
		this.heurystyka = heurystyka;
		this.waga = waga;
	}
	
	private final Heurystyka heurystyka;
	private int waga;
	
	int getWartosc(Plansza makietaRuchu, int kolumna)
	{
		return heurystyka.getWartosc(makietaRuchu, kolumna);
	}
}