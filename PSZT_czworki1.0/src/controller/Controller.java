package controller;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import view.View;
import wiadomosc.Wiadomosc;
import wiadomosc.WiadomoscRuch;
import model.Model;
import model.RodzajeGraczy;
import sytuacjeWyjatkowe.*;
import sztucznaInteligencja.HeurystykaMaxDl;
import sztucznaInteligencja.HeurystykaMaxIloscCiagow;
import sztucznaInteligencja.HeurystykaMaxWplywuNaSwojePola;
import sztucznaInteligencja.HeurystykaMaxWplywyNaPolaPrzeciwnika;
import sztucznaInteligencja.HeurystykaZWaga;
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
		view = new View(Model.iloscWierszy, Model.iloscKolumn, kolejkaZadan);
		wczytajUstawienia();
		//wczytaj Ustawienia Gry Z Pakietu Obslugi Plikow
		//wczytajUstawienia();
		
		/*
		//testy heurystyki
		Plansza plansza = new Plansza(Model.iloscWierszy, Model.iloscKolumn);
		plansza.sprawdzCzyWygrana(6, 0);
		plansza.sprawdzCzyWygrana(5, 0);
		plansza.sprawdzCzyWygrana(3, 0);
		/*plansza.sprawdzCzyWygrana(2, 1);
		plansza.sprawdzCzyWygrana(2, 0);
		plansza.sprawdzCzyWygrana(2, 1);
		plansza.sprawdzCzyWygrana(1, 0);
		plansza.sprawdzCzyWygrana(1, 1);
		
		plansza.pisz();
		
		Heurystyka heu = new HeurystykaMaxIloscCiagow(Przynaleznosc.GRACZ1);
		HeurystykaZWaga heuzw= new HeurystykaZWaga(heu, 1);
		System.out.println();
		System.out.println("Heurystyka: " + heu.getWartosc(plansza, 4, 1));
		*/
	
		
		
		//dobre
		try {
			nowaGra();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
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
				try
				{
					for(;;)
					{
						if(kolejkaZadan.size() > 0)
							sprzatnijKolejke();				
						ktoryGracz = model.ktoryGracz();
						
						//System.out.println(model.czyjRuch());
						if(model.czyjRuch() == RodzajeGraczy.KOMPUTER)
						{
							try
							{
								if(ktoryGracz == AI.getKtoryJestAI())
								{
									wspolrzedne = model.wrzucZeton(AI.wybierzKolumne(model.getPlansza()));
									AI.pisz();
								}
								else
								{
									wspolrzedne = model.wrzucZeton(AI1.wybierzKolumne(model.getPlansza()));
									AI1.pisz();
								}
								System.out.println("----- KONIEC RUCHU AI -----");
								System.out.println("------------------------------");
							} catch(WyjatekRuchNiedozwolony e)
							{
								System.out.println("Blad algorytmu! Ruch niedozwolony!");
								e.printStackTrace();
								break;
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
					System.out.println("Wygrana!");
				}
				finally
				{
					wyczyscKolejke();
				}
		} catch(InterruptedException e)
		{}
	}
	
	/** Funkcja wczytujaca informacje o konfiguracji dzialania programu jesli jest komputer. */
	private void wczytajUstawienia()
	{
		//TODO
	}
	
	/** Funkcja inicjalizujaca model: wysyla mu tryb gry i potrzebne informacje. */
	private void inicjalizujModel()
	{
		if(wiadomosc.jakiTryb() == Tryby.AIvsCZLOWIEK)
			model.nowaGra(wiadomosc.jakiTryb(), AI.getKtoryJestAI());
		else
			model.nowaGra(wiadomosc.jakiTryb());
	}
	
	private void nowaGra() throws InterruptedException
	{
		//TO MODIFY
		int ktoryKomputer = 0;
		int glebokoscDrzewa = 5;
		view.wyswietlPanelWyboruGraczy();
		odbierzWiadomosc();
		if(czyWGrzeKomputer())
		{	
			//stworzyc wektor heurystyk z wagami i przekazac
			Vector<HeurystykaZWaga> vectorHeurystyk = new Vector<HeurystykaZWaga>();
			Vector<HeurystykaZWaga> vectorHeurystyk1 = new Vector<HeurystykaZWaga>();
			
			vectorHeurystyk.add(new HeurystykaZWaga(new HeurystykaMaxDl(), 2));
			vectorHeurystyk.add(new HeurystykaZWaga(new HeurystykaMaxIloscCiagow(), 1));
			vectorHeurystyk.add(new HeurystykaZWaga(new HeurystykaMaxWplywuNaSwojePola(), 3));
			vectorHeurystyk.add(new HeurystykaZWaga(new HeurystykaMaxWplywyNaPolaPrzeciwnika(), 1));
			
			vectorHeurystyk1.add(new HeurystykaZWaga(new HeurystykaMaxDl(), 1));
			vectorHeurystyk1.add(new HeurystykaZWaga(new HeurystykaMaxIloscCiagow(), 1));
			vectorHeurystyk1.add(new HeurystykaZWaga(new HeurystykaMaxWplywuNaSwojePola(), 1));
			vectorHeurystyk1.add(new HeurystykaZWaga(new HeurystykaMaxWplywyNaPolaPrzeciwnika(), 1));

			AI = new SztucznaInteligencja(vectorHeurystyk, ktoryKomputer, glebokoscDrzewa);
			AI1 = new SztucznaInteligencja(vectorHeurystyk1, (ktoryKomputer + 1)%2, glebokoscDrzewa);
		}
		
		inicjalizujModel();
		
		view.wyswietlPanelZGra();
		view.wylaczPanelWyboruGraczy();
	}
	/*
	private Przynaleznosc wyznaczPrzynaleznosc(int ktoryGracz)
	{
		if(ktoryGracz == 0)
			return Przynaleznosc.GRACZ1;
		if(ktoryGracz == 1)
			return Przynaleznosc.GRACZ2;
		return null;
	}
	*/
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
		if(wiadomosc.jakiTryb() == Tryby.AIvsAI || wiadomosc.jakiTryb() == Tryby.AIvsCZLOWIEK)
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
	//////////////
	private SztucznaInteligencja AI1 = null;
	
	/** Referencja na widok. */
	private final View view;

	private int ostatnieXMyszki = 150;
	
	/** Sk³adowa przechowuj¹ca referencje na wiadomoœci przekazywane przez BlockingQueue od View. */
	private Wiadomosc wiadomosc = null;
}