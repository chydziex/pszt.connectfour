package controller;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import view.View;
import wiadomosc.Wiadomosc;
import wiadomosc.WiadomoscRuch;
import model.Model;
import model.RodzajeGraczy;
import sytuacjeWyjatkowe.*;
import model.Tryby;
import model.Wspolrzedne;
import wiadomosc.*;

/** W modelu potrzebne:
 * metody interfejsu, kt�re wida� w run Controllera
 * enum Player = {HUMAN, CPU}
 */


public class Controller implements Runnable
{
	public Controller()
	{
		view = new View(model.iloscWierszy, model.iloscKolumn, kolejkaZadan);
		/**
		 * testowanko wywietlania okna wyboru trybu, bez wyswietlania planszy
		 * TODO
		 * wyglad okna koniecznie - pobawie si� jutro - Mateusz Ch
		 * 
		 */
		/*
		view.wyswietlPanelWyboruGraczy();
		try {
			odbierzWiadomosc();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		view.wylaczPanelWyboruGraczy();
					
		model.nowaGra(( (WiadomoscTryb) wiadomosc).jakiTryb());
		
		System.out.println(model.getTrybGry());
		
		view.wyswietlPanelZGra();
		view.wyswietlPanelInterfejsuGracza(0);
		
		view.wyswietlPanelZGra();
		view.aktualizacja(new Wspolrzedne(5, 0), 0);
		view.aktualizacja(new Wspolrzedne(5, 1), 1);
		view.aktualizacja(new Wspolrzedne(4, 0), 0);
		view.aktualizacja(new Wspolrzedne(3, 0), 1);
		
		*/
		
	}
	
	
	@Override
	public void run()
	{
		
		int ktoryGracz = 0;
		int xMyszki = 0;
		// Obiekt przechowuj�cy informacje, w kt�rym okienku planszy zosta� w�asnie wrzucony �eton. Info od modelu.
		Wspolrzedne wspolrzedne = null;
		try
		{
			KONIEC_GRY: for(;;)
			{
				ustawieniaGry();
				view.wyswietlPanelZGra();
				view.wylaczPanelWyboruGraczy();
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
						//ruch cz�owieka
						else
						{
							view.wyswietlPanelInterfejsuGracza(ktoryGracz);
							wlozWiadomosc(new WiadomoscRuchMyszki(ostatnieXMyszki));
							for(;;)
							{
								try
								{
									odbierzWiadomosc();
									if(wiadomosc.czyRuchMyszki())
									{
										//System.out.println("Ruch myszki");
										xMyszki = ((WiadomoscRuchMyszki)wiadomosc).getXMyszki();
										ostatnieXMyszki = xMyszki;
										view.aktualizacjaPaneluIntergejsuGracza(xMyszki);
										if(model.czyRuchJestDozwolony(view.ktoraKolumneWskazujeGracz()))
											view.ustawRodzajStrzalki(0);
										else
											view.ustawRodzajStrzalki(1);
									}
									else if(wiadomosc.czyRuch())
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
				//w e info o wspolrzednych, na ktore wrzucono zeton!!

				} catch(WyjatekRemis e)
				{
					remis();
					//view.aktualizacja(wspolrzedne, ktoryGracz);
				}
				catch(WyjatekWygrana e)
				{
					wygrana();
					//view.aktualizacja(wspolrzedne, ktoryGracz);
				}
				finally
				{
					wyczyscKolejke();
				}
			}
		} catch(InterruptedException e)
		{}

		
		
		
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
	
	/** Metoda obs�uguj�ca sytuacj� ko�ca gry - remis.*/
	private void remis()
	{
		view.remis();
	}
	
	/** Metoda obs�uguj�ca sytuacj� ko�ca gry - wygrana. Pyta modelu, kto wykona� ostatni ruch - ten wygra�.
	 * Je�li cz�owiek wygra�, przekazuje view jego kolejno��. Je�li komputer, przekazuje view nazwe algorytmu.*/
	private void wygrana()
	{
		// TODO
		if(model.czyjRuch() == RodzajeGraczy.CZLOWIEK)
			view.wygrana(model.ktoryGracz());
		//else
			//view.wygrana(model.getNazwaAlgorytmu());
		
	}
	
	/** Metoda odbieraj�ca wiadomo�ci od view przekazywane przez kolejk�. Zapisuje referencje wiadomo�ci do pola message. 
	 * @throws InterruptedException */
	private void odbierzWiadomosc() throws InterruptedException
	{
		wiadomosc = kolejkaZadan.take();
	}
	
	private void wlozWiadomosc(Wiadomosc wiadomosc)
	{
		kolejkaZadan.offer(wiadomosc);
	}
	
	/** Metoda obs�uguj�ca inne zadania umieszczone do kolejki - nowa gra lub jaki� b��d, np kilkukrotnego wyboru kolumny przez tego samego gracza
	 * zanim view wy��czy� panel interfejsu wyboru kolumny. Zapisuje wiadomo�ci do pola wiadomosc bez ich obs�ugi.*/
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
	
	/** Metoda sprawdzaj�ca, czy w grze jest komputer: tryby Player vs CPU lub CPU vs CPU. Je�li tak, zwraca true, w przeciwnym przypadku false. */
	private boolean czyWGrzeKomputer()
	{
		if(model.getTrybGry() == Tryby.AIvsAI || model.getTrybGry() == Tryby.AIvsCZLOWIEK)
			return true;
		else
			return false;
	}
	
	private void wyczyscKolejke()
	{
		kolejkaZadan.clear();
	}
	
	/** Kolejka przechowuj�ca zadania od View. */
	private BlockingQueue<Wiadomosc> kolejkaZadan = new ArrayBlockingQueue<Wiadomosc>(5);
	
	/** Referencja na model. */
	private final Model model = new Model();
	
	/** Referencja na widok. */
	private final View view;

	private int ostatnieXMyszki = 150;
	
	/** Sk�adowa przechowuj�ca referencje na wiadomo�ci przekazywane przez BlockingQueue od View. */
	private Wiadomosc wiadomosc = null;
}