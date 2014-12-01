package controller;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import view.View;
import wiadomosc.Wiadomosc;
import wiadomosc.WiadomoscRuch;
import model.Model;
import model.Plansza;
import model.RodzajeGraczy;
import sytuacjeWyjatkowe.*;
import sztucznaInteligencja.SztucznaInteligencja;
import model.Tryby;
import model.Wspolrzedne;
import wiadomosc.*;

/** W modelu potrzebne:
 * metody interfejsu, które widaæ w run Controllera
 * enum Player = {HUMAN, CPU}
 */


public class Controller implements Runnable
{
	public Controller()
	{
		view = new View(model.iloscWierszy, model.iloscKolumn, kolejkaZadan);
		//wczytaj Ustawienia Gry Z Pakietu Obslugi Plikow
		//wczytajUstawienia();
		
		/**
		 * testowanko klonowania
		 * wyglad okna koniecznie - pobawie siê jutro - Mateusz Ch
		 * 
		 */
		//model.nowaGra(Tryby.CZLOWIEKvsCZLOWIEK);
/*
			model.getPlansza().sprawdzCzyWygrana(1, 0);
			model.getPlansza().sprawdzCzyWygrana(1, 1);
			

		
		
		model.getPlansza().pisz();
		System.out.println();
		*/
		/*Plansza kopiaPlanszy = null;
		
		try {
			
			kopiaPlanszy = (Plansza) model.getPlansza().clone();
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kopiaPlanszy.pisz();
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		kopiaPlanszy.zmien();
		
		model.getPlansza().pisz();
		System.out.println();
		kopiaPlanszy.pisz();
		*/
	}
	
	
	@Override
	public void run()
	{
		int ktoryGracz = 0;
		int xMyszki = 0;
		// Obiekt przechowuj¹cy informacje, w którym okienku planszy zosta³ w³asnie wrzucony ¿eton. Info od modelu.
		Wspolrzedne wspolrzedne = null;
		try
		{
			KONIEC_GRY: for(;;)
			{
				nowaGra();
				try
				{
					NEW_GAME: for(;;)
					{
						if(kolejkaZadan.size() > 0)
							sprzatnijKolejke();				
						ktoryGracz = model.ktoryGracz();
						
						if(model.czyjRuch() == RodzajeGraczy.KOMPUTER)
						{
							try
							{
								System.out.println("Dobrze");
								wspolrzedne = model.wrzucZeton(AI.wybierzKolumne(model.getPlansza()));
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
					view.aktualizacja(wspolrzedne, ktoryGracz);
				}
				catch(WyjatekWygrana e)
				{
					wygrana();
					view.aktualizacja(wspolrzedne, ktoryGracz);
				}
				finally
				{
					wyczyscKolejke();
				}
			}
		} catch(InterruptedException e)
		{}
	}
	
	/** Funckaj wczytujaca informacje o konfiguracji dzialania programu jesli jest komputer. */
	private void wczytajUstawienia()
	{
		//TODO
	}
	
	private void nowaGra() throws InterruptedException
	{
		//TO MODIFY
		view.wyswietlPanelWyboruGraczy();
		odbierzWiadomosc();
		//if(wiadomosc.jakiTryb() == Tryby.AIvsAI)
			//model.nowaGra(wiadomosc.jakiTryb(), ktoryJestAI);
		model.nowaGra(wiadomosc.jakiTryb());
		if(czyWGrzeKomputer())
		{	
			AI = new SztucznaInteligencja(null, 0);
		}
		view.wyswietlPanelZGra();
		view.wylaczPanelWyboruGraczy();
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
		wiadomosc = kolejkaZadan.take();
	}
	
	private void wlozWiadomosc(Wiadomosc wiadomosc)
	{
		kolejkaZadan.offer(wiadomosc);
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
	
	private void wyczyscKolejke()
	{
		kolejkaZadan.clear();
	}
	
	/** Kolejka przechowuj¹ca zadania od View. */
	private BlockingQueue<Wiadomosc> kolejkaZadan = new ArrayBlockingQueue<Wiadomosc>(5);
	
	/** Referencja na model. */
	private final Model model = new Model();
	
	private SztucznaInteligencja AI = null;
	
	/** Referencja na widok. */
	private final View view;

	private int ostatnieXMyszki = 150;
	
	/** Sk³adowa przechowuj¹ca referencje na wiadomoœci przekazywane przez BlockingQueue od View. */
	private Wiadomosc wiadomosc = null;
}