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
	
	public int getWartosc(Plansza makietaRuchu, int kolumna, int ktoryGracz)
	{
		return heurystyka.getWartosc(makietaRuchu, kolumna, ktoryGracz);
	}
}
