package view;
import javax.swing.JPanel;

import model.Wspolrzedne;

/**
 * Gracz pierwszy ma ___ �eton, gracz drugi ma ___ �eton.
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
	
	/** Metoda wy�wietlaj�ca �eton rodzaju zale�nego od ktoryGracz na planszy wskazanych wsp�rz�dnych.*/
	public void aktualizacja(Wspolrzedne wspolrzedne, int ktoryGracz)
	{
		okno.dodajZeton(wspolrzedne, ktoryGracz);
	}
	
	/** Metoda tworz�ca now� gr� - czyst� plansz�.*/
	public void nowaGra()
	{
		// TODO, usunac wczesniejsze panele.
		// jesli by�a juz gra, to usun�� z panelu.
	}
	
	/** Metoda wy�wietlaj�ca panel umo�liwiaj�cy graczowi wyb�r kolumny do wrzucenia �etonu. R�ne �etony w zale�no�ci od ktoryGracz*/
	public void wyswietlPanelInterfejsuGracza(int ktoryGracz)
	{
		// TODO
	}
	
	/** Metoda wy��czaj�ca panel uniemo�liwja�c graczowi wrzut �etonu.*/
	public void wylaczPanelInterfejsuGracza()
	{
		// TODO
	}
	
	/** Metoda wy�wietlaj�ca panel z gr�.*/
	public void wyswietlPanelZGra()
	{
		okno.wyswietlPanelZGra();
	}
	
	/** Metoda wy�wietlaj�ca panel wyboru graczy.*/
	public void wyswietlPanelWyboruGraczy()
	{
		// TODO
	}
	
	/** Metoda wy�wietlaj�ca panel wyboru heurystyki dla AI.*/
	public void wyswietlPanelWyboruAlgorytmuAI()
	{
		// TODO
	}
	
	/** Metoda wy�wietlaj�ca informacje o ko�cu gry i remisie.*/
	public void remis()
	{
		// TODO
	}
	
	/** Metoda wy�wietlaj�ca informacje o tym kt�ry gracz wygra�, je�li by� to cz�owiek (jego kolejno��). Wygra� gracz1/2/3 itd*/
	public void wygrana(int ktoWygral)
	{
		// TODO
	}
	
	/** Metoda wy�wetlaj�ca informacje o nazwie algorytmu kt�ry wygra�.*/
	public void wygrana(String nazwaHeurystyki)
	{
		// TODO
	}
	
	private Okno okno;
}
