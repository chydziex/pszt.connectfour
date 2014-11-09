package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

import javax.swing.JPanel;

import wiadomosc.Wiadomosc;
import wiadomosc.WiadomoscTryb;
import model.Wspolrzedne;

/**
 * Gracz pierwszy ma ___ �eton, gracz drugi ma ___ �eton.
 *
 */
public class View
{
	public View(int iloscWierszy, int iloscKolumn, BlockingQueue<Wiadomosc> kolejkaZadan)
	{
		// TODO
		this.kolejkaZdarzen = kolejkaZadan;
		okno = new Okno(iloscWierszy, iloscKolumn);
		//nie tworzyc panelu gry, na poczatku ma byc nullem.
		//stworzPanelWyboruGraczy();
		//stworzPanelWyboruAlgorytmuAi();
		
		dodajListenery();
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
		okno.wyswietlPanelWyboruTrybu();
		// TODO
	}
	
	/** Metoda wy�wietlaj�ca panel wyboru heurystyki dla AI.*/
	public void wyswietlPanelWyboruAlgorytmuAI()
	{
		okno.wyswietlPanelWyboruAlgorytmu();
		// TODO
	}
	/** Metoda wy�wietlaj�ca panel wyboru graczy.*/
	public void wylaczPanelWyboruGraczy()
	{
		okno.wylaczPanelWyboruTrybu();
		// TODO
	}
	
	/** Metoda wy�wietlaj�ca panel wyboru heurystyki dla AI.*/
	public void wylaczPanelWyboruAlgorytmuAI()
	{
		okno.wylaczPanelWyboruAlgorytmu();
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
	
	/**
	 * Metoda dodajaca listenery dla nacisniec przyciskow OK w panelach wyboru:
	 * - trybu gry
	 * - algorytmu AI
	 */
	private void dodajListenery(){
		
		wybranoTrybGryListener = new ActionListener (){

			@Override
			public void actionPerformed(ActionEvent e) {
					
				kolejkaZdarzen.offer(new WiadomoscTryb(okno.czytajTrybGry()));
			
		
			}
			
			
		};
		wybranoAlgorytmAiListener = new ActionListener (){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO 
				
				
			
				
				
			}
		
			
			
			
		};
			
		okno.dodajWyborTrybuListener(wybranoTrybGryListener);
		okno.dodajWyborAlgorytmuAiListener(wybranoAlgorytmAiListener);
		
		
	}
	
	private Okno okno;
	BlockingQueue<Wiadomosc> kolejkaZdarzen;
	
	private ActionListener wybranoTrybGryListener;
	private ActionListener wybranoAlgorytmAiListener;
}
