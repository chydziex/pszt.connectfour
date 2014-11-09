package controller;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import view.View;
import view.Wiadomosc;
import view.WiadomoscRuch;
import model.Model;
import model.RodzajeGraczy;
import sytuacjeWyjatkowe.*;
import model.Tryby;
import model.Wspolrzedne;


/** W modelu potrzebne:
 * metody interfejsu, które widaæ w run Controllera
 * enum Player = {HUMAN, CPU}
 */


public class Controller implements Runnable
{
	public Controller()
	{
		view = new View(model.iloscWierszy, model.iloscKolumn);
		view.wyswietlPanelZGra();
		view.aktualizacja(new Wspolrzedne(5, 0), 0);
		view.aktualizacja(new Wspolrzedne(5, 1), 1);
		view.aktualizacja(new Wspolrzedne(4, 0), 0);
		view.aktualizacja(new Wspolrzedne(3, 0), 1);
	}
	
	@Override
	public void run()
	{
		int ktoryGracz = 0;
		// Obiekt przechowuj¹cy informacje, w którym okienku planszy zosta³ w³asnie wrzucony ¿eton. Info od modelu.
	/*	Wspolrzedne wspolrzedne = null;
		try
		{
			KONIEC_GRY: for(;;)
			{
				ustawieniaGry();
				view.wyswietlPanelZGra();
				try
				{
					NEW_GAME: for(;;)
					{
						if(kolejkaZadan.size() > 0)
							sprzatnijKolejke();
						if(wiadomosc.czyNowaGra())
							break NEW_GAME;
				
						ktoryGracz = model.ktoryGracz();
						if(model.czyjRuch() == RodzajeGraczy.KOMPUTER)
						{
							try
							{
								wspolrzedne = model.wrzucZeton();
							} catch(WyjatekRuchNiedozwolony e)
							{
								System.out.println("Blad algorytmu! Ruch niedozwolony!");
								e.printStackTrace();
								break KONIEC_GRY;
							}
						}
						//ruch cz³owieka
						else
						{
							view.wyswietlPanelInterfejsuGracza(ktoryGracz);
							for(;;)
							{
								try
								{
									odbierzWiadomosc();
									if(wiadomosc.czyRuch())
									{
										wspolrzedne = model.wrzucZeton(((WiadomoscRuch) wiadomosc).getKtoraKolumna());
										break;
									}
									else if(wiadomosc.czyNowaGra())
									{
										view.wylaczPanelInterfejsuGracza();
										view.nowaGra();
										break NEW_GAME;
									}
								} catch(WyjatekRuchNiedozwolony e)
								{
									//do zmiany
									System.out.println("Ruch niedozwolony!");
								}
							}
							view.wylaczPanelInterfejsuGracza();
						}
						view.aktualizacja(wspolrzedne, ktoryGracz);
					}
				} catch(WyjatekRemis e)
				{
					remis();
				}
				catch(WyajtekWygrana e)
				{
					wygrana();
				}
			}
		} catch(InterruptedException e)
		{}
		*/
	}

	private void ustawieniaGry() throws InterruptedException
	{
		//view.nowaGra();
		view.wyswietlPanelWyboruGraczy();
		odbierzWiadomosc();
		model.nowaGra(wiadomosc.jakiTryb());
		if(czyWGrzeKomputer())
		{
			view.wyswietlPanelWyboruAlgorytmuAI();
			odbierzWiadomosc();
		}
	}
	
	/** Metoda obs³uguj¹ca sytuacjê koñca gry - remis.*/
	private void remis()
	{
		view.remis();
	}
	
	/** Metoda obs³uguj¹ca sytuacjê koñca gry - wygrana. Pyta modelu, kto wykona³ ostatni ruch - ten wygra³.
	 * Jeœli cz³owiek wygra³, przekazuje view jego kolejnoœæ. Jeœli komputer, przekazuje view nazwe algorytmu.*/
	private void wygrana()
	{
		// TODO
		if(model.czyjRuch() == RodzajeGraczy.CZLOWIEK)
			view.wygrana(model.ktoryGracz());
		//else
			//view.wygrana(model.getNazwaAlgorytmu());
		
	}
	
	/** Metoda odbieraj¹ca wiadomoœci od view przekazywane przez kolejkê. Zapisuje referencje wiadomoœci do pola message. 
	 * @throws InterruptedException */
	private void odbierzWiadomosc() throws InterruptedException
	{
		kolejkaZadan.take();
	}
	
	/** Metoda obs³uguj¹ca inne zadania umieszczone do kolejki - nowa gra lub jakiœ b³¹d, np kilkukrotnego wyboru kolumny przez tego samego gracza
	 * zanim view wy³¹czy³ panel interfejsu wyboru kolumny. Zapisuje wiadomoœci do pola wiadomosc bez ich obs³ugi.*/
	private void sprzatnijKolejke()
	{
		// TODO
		// jesli nowa gra, to tylko wroc, nie obsluguj, bo obsluga w run().
		Wiadomosc temp = kolejkaZadan.poll();
		for(; temp != null;)
		{
			if(!temp.czyRuch())
				wiadomosc = temp;
			temp = kolejkaZadan.poll();
		}
	}
	
	/** Metoda sprawdzaj¹ca, czy w grze jest komputer: tryby Player vs CPU lub CPU vs CPU. Jeœli tak, zwraca true, w przeciwnym przypadku false. */
	private boolean czyWGrzeKomputer()
	{
		if(model.getTrybGry() == Tryby.AIvsAI || model.getTrybGry() == Tryby.AIvsCZLOWIEK)
			return true;
		else
			return false;
	}
	
	/** Kolejka przechowuj¹ca zadania od View. */
	private BlockingQueue<Wiadomosc> kolejkaZadan = new ArrayBlockingQueue<Wiadomosc>(5);
	
	/** Referencja na model. */
	private final Model model = new Model();
	
	/** Referencja na widok. */
	private final View view;
	
	/** Sk³adowa przechowuj¹ca referencje na wiadomoœci przekazywane przez BlockingQueue od View. */
	private Wiadomosc wiadomosc = null;
}