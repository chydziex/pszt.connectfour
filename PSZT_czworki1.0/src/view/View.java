package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.BlockingQueue;

import wiadomosc.Wiadomosc;
import wiadomosc.WiadomoscRuch;
import wiadomosc.WiadomoscRuchMyszki;
import wiadomosc.WiadomoscTryb;
import model.Wspolrzedne;

/**
 * Gracz pierwszy ma ___ ¿eton, gracz drugi ma ___ ¿eton.
 *
 */
public class View
{
	public View(int iloscWierszy, int iloscKolumn, BlockingQueue<Wiadomosc> kolejkaZadan)
	{
		// TODO
		kolejkaZdarzen = kolejkaZadan;
		okno = new Okno(iloscWierszy, iloscKolumn);
		//nie tworzyc panelu gry, na poczatku ma byc nullem.
		//stworzPanelWyboruGraczy();
		//stworzPanelWyboruAlgorytmuAi();
		
		dodajListenery();
	}
	
	/** Metoda wyœwietlaj¹ca ¿eton rodzaju zale¿nego od ktoryGracz na planszy wskazanych wspó³rzêdnych.*/
	public void aktualizacja(Wspolrzedne wspolrzedne, int ktoryGracz)
	{
		okno.dodajZeton(wspolrzedne, ktoryGracz);
	}
	
	/** Metoda aktualizuj¹ca pozycjê ¿etonu i strza³ki na panelu interfejsu gracza.*/
	public void aktualizacjaPaneluIntergejsuGracza(int xMyszki)
	{
		okno.aktualizujPanelInterfejsuGracza(xMyszki);
	}
	
	public void ustawRodzajStrzalki(int ktoraStrzalka)
	{
		okno.ustawRodzajStrzalki(ktoraStrzalka);
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
		okno.wyswietlInterfejsGracza(ktoryGracz);
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
		okno.wyswietlPanelWyboruTrybu();
		// TODO
	}
	
	/** Metoda wyœwietlaj¹ca panel wyboru heurystyki dla AI.*/
	public void wyswietlPanelWyboruAlgorytmuAI()
	{
		okno.wyswietlPanelWyboruAlgorytmu();
		// TODO
	}
	/** Metoda wyœwietlaj¹ca panel wyboru graczy.*/
	public void wylaczPanelWyboruGraczy()
	{
		okno.wylaczPanelWyboruTrybu();
		// TODO
	}
	
	/** Metoda wyœwietlaj¹ca panel wyboru heurystyki dla AI.*/
	public void wylaczPanelWyboruAlgorytmuAI()
	{
		okno.wylaczPanelWyboruAlgorytmu();
		// TODO
	}
	
	public int ktoraKolumneWskazujeGracz()
	{
		return okno.ktoraKolumneWskazujeGracz();
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
		
		ruchMyszkiListener = new MouseMotionListener()
		{
			@Override
			public void mouseDragged(MouseEvent arg0)
			{}
			
			@Override
			public void mouseMoved(MouseEvent arg0)
			{
				kolejkaZdarzen.offer(new WiadomoscRuchMyszki(arg0.getX()));
			}
		};
		
		klikMyszkiListener = new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				kolejkaZdarzen.offer(new WiadomoscRuch(okno.ktoraKolumneWskazujeGracz()));
			}

			@Override
			public void mouseEntered(MouseEvent arg0)
			{}

			@Override
			public void mouseExited(MouseEvent arg0)
			{}

			@Override
			public void mousePressed(MouseEvent arg0)
			{}

			@Override
			public void mouseReleased(MouseEvent arg0)
			{}
		};
			
		okno.dodajWyborTrybuListener(wybranoTrybGryListener);
		okno.dodajWyborAlgorytmuAiListener(wybranoAlgorytmAiListener);
		okno.dodajPozycjaZetonuIStrzalkiInterfejsListener(ruchMyszkiListener);
		okno.dodajRuchGraczaListener(klikMyszkiListener);
		
	}
	
	private Okno okno;
	BlockingQueue<Wiadomosc> kolejkaZdarzen;
	
	private ActionListener wybranoTrybGryListener;
	private ActionListener wybranoAlgorytmAiListener;
	private MouseMotionListener ruchMyszkiListener;
	private MouseListener klikMyszkiListener;
}
