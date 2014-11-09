package view;
import javax.swing.JPanel;

import model.Wspolrzedne;

/**
 * Gracz pierwszy ma ___ ¿eton, gracz drugi ma ___ ¿eton.
 *
 */
public class View
{
	public View(int iloscWierszy, int iloscKolumn)
	{
		// TODO
		okno = new Okno(iloscWierszy, iloscKolumn);
		//nie tworzyc panelu gry, na poczatku ma byc nullem.
		//stworzPanelWyboruGraczy();
		//stworzPanelWyboruAlgorytmuAi();
	}
	
	/** Metoda wyœwietlaj¹ca ¿eton rodzaju zale¿nego od ktoryGracz na planszy wskazanych wspó³rzêdnych.*/
	public void aktualizacja(Wspolrzedne wspolrzedne, int ktoryGracz)
	{
		okno.dodajZeton(wspolrzedne, ktoryGracz);
	}
	
	/** Metoda tworz¹ca now¹ grê - czyst¹ planszê.*/
	public void nowaGra()
	{
		// TODO, usunac wczesniejsze panele.
		// jesli by³a juz gra, to usun¹æ z panelu.
	}
	
	/** Metoda wyœwietlaj¹ca panel umo¿liwiaj¹cy graczowi wybór kolumny do wrzucenia ¿etonu. Ró¿ne ¿etony w zale¿noœci od ktoryGracz*/
	public void wyswietlPanelInterfejsuGracza(int ktoryGracz)
	{
		// TODO
	}
	
	/** Metoda wy³¹czaj¹ca panel uniemo¿liwja¹c graczowi wrzut ¿etonu.*/
	public void wylaczPanelInterfejsuGracza()
	{
		// TODO
	}
	
	/** Metoda wyœwietlaj¹ca panel z gr¹.*/
	public void wyswietlPanelZGra()
	{
		okno.wyswietlPanelZGra();
	}
	
	/** Metoda wyœwietlaj¹ca panel wyboru graczy.*/
	public void wyswietlPanelWyboruGraczy()
	{
		// TODO
	}
	
	/** Metoda wyœwietlaj¹ca panel wyboru heurystyki dla AI.*/
	public void wyswietlPanelWyboruAlgorytmuAI()
	{
		// TODO
	}
	
	/** Metoda wyœwietlaj¹ca informacje o koñcu gry i remisie.*/
	public void remis()
	{
		// TODO
	}
	
	/** Metoda wyœwietlaj¹ca informacje o tym który gracz wygra³, jeœli by³ to cz³owiek (jego kolejnoœæ). Wygra³ gracz1/2/3 itd*/
	public void wygrana(int ktoWygral)
	{
		// TODO
	}
	
	/** Metoda wyœwetlaj¹ca informacje o nazwie algorytmu który wygra³.*/
	public void wygrana(String nazwaHeurystyki)
	{
		// TODO
	}
	
	private Okno okno;
}
