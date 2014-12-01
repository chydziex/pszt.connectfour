package sztucznaInteligencja;

import java.util.Random;
import java.util.Vector;

import model.Model;
import model.Plansza;
import model.Przynaleznosc;
import model.Wspolrzedne;

//TODO
public class SztucznaInteligencja
{
	public SztucznaInteligencja(Vector<HeurystykaZWaga> mojeHeurystyki, int kolejnosc, int glebokoscD)
	{
		this.mojeHeurystyki = mojeHeurystyki;
		glebokoscDrzewa = glebokoscD;
		ktoryJestAI = kolejnosc;
	}
	
	public int getKtoryJestAI()
	{
		return ktoryJestAI;
	}
	
	public int wybierzKolumne(final Plansza plansza)
	{
		//TODO		
		Vector<Integer> vectorNajlepszychKolumn= new Vector<Integer>();
		int najlepszaKolumna = 0, maxKolumna = -1;
		double aktualnaWartoscWezla, maxWartoscWezla = Double.NEGATIVE_INFINITY, alfa = Double.NEGATIVE_INFINITY, beta = Double.POSITIVE_INFINITY;
		Plansza kopiaPlanszy = null;
		
		for(int i = 0; i < Model.iloscKolumn; i++)
		{
			try
			{
				kopiaPlanszy = (Plansza) plansza.clone();
				
			}catch (CloneNotSupportedException e)
			{
				e.printStackTrace();
			}
			
			//wrzucamy zeton do "wirtualnej planszy do konkretnej kolumny.
			if(!kopiaPlanszy.czyRuchJestDozwolony(indeksyKolumn[i]))
				continue;
			//kopiaPlanszy.sprawdzCzyWygrana(indeksyKolumn[i], ktoryJestAI);
			aktualnaWartoscWezla = alfaBeta(kopiaPlanszy, indeksyKolumn[i], 1, alfa, beta);
			System.out.println("Kolumna: " + indeksyKolumn[i] + " Ocena: " + aktualnaWartoscWezla);
			if(aktualnaWartoscWezla > maxWartoscWezla)
			{
				maxWartoscWezla = aktualnaWartoscWezla;
				maxKolumna = indeksyKolumn[i];
				System.out.println("Znalazlem lepsza kolumne: " + maxKolumna);
			}
		}
		//int indeks = (rand.nextInt())%(vectorNajlepszychKolumn.size());
		return maxKolumna;
	}
	
	public double ocenWezel(final Plansza plansza, int doKtorejKolumnyChcemyWrzucic)
	{
		double ocena = 0;
		if(mojeHeurystyki == null)
			return 0;
		for(HeurystykaZWaga heurystyka : mojeHeurystyki)
			ocena += heurystyka.getWartosc(plansza, doKtorejKolumnyChcemyWrzucic);
		return ocena;
	}
	
	/** funkcja relizujaca algorytm alfa - beta. Zwraca wartosc f-cji oceniajacej dany ruch.
	 *  glebokosc: ile ruchow do przodu analizujemy. 1 - rozpatrujemy stan gry po naszym pierwszym ruchu.
	 *  plansza: "wirtualna" plansza po wrzuceniu do niej zetonu reprezentujacego nasz hipotetyczny ruch, ktory analizujemy.
	 */
	private double alfaBeta(final Plansza plansza, int doKtorejKolumnyChcemyWrzucic, int glebokosc, double alfa, double beta)
	{
		// TODO
		System.out.println(glebokosc);
		double temp;
		Plansza kopiaPlanszy = null;
		if(glebokosc == glebokoscDrzewa)
		{
			System.out.println("Ocena: " + ocenWezel(plansza, doKtorejKolumnyChcemyWrzucic));
			return ocenWezel(plansza, doKtorejKolumnyChcemyWrzucic);
		}
		// jesli teraz jest nasz ruch
		if(glebokosc %2 == 1)
		{
			for(int i = 0; i < Model.iloscKolumn; i++)
			{
				try
				{
					kopiaPlanszy = (Plansza) plansza.clone();
					
				}catch (CloneNotSupportedException e)
				{
					e.printStackTrace();
				}
				if(!kopiaPlanszy.czyRuchJestDozwolony(indeksyKolumn[i]))
					continue;
				kopiaPlanszy.sprawdzCzyWygrana(indeksyKolumn[i], ktoryJestAI);
				temp = alfaBeta(kopiaPlanszy, indeksyKolumn[i], glebokosc + 1, alfa, beta);
				System.out.println("Temp " + i + ": " + temp );
				if(temp > alfa)
				{
					alfa = temp;
				}
				if(alfa >= beta)
				{
					System.out.println(glebokosc + " " + i + " zwracamy bete = " + beta);
					return beta;
				}
			}
			return alfa;
		}
		// jesli teraz jest ruch przeciwnika
		else
		{
			for(int i = 0; i < Model.iloscKolumn; i++)
			{
				try
				{
					kopiaPlanszy = (Plansza) plansza.clone();
					
				}catch (CloneNotSupportedException e)
				{
					e.printStackTrace();
				}
				if(!kopiaPlanszy.czyRuchJestDozwolony(indeksyKolumn[i]))
					continue;
				kopiaPlanszy.sprawdzCzyWygrana(indeksyKolumn[i], ktoryJestAI);
				temp = alfaBeta(kopiaPlanszy, indeksyKolumn[i], glebokosc + 1, alfa, beta);
				if(temp < beta)
					beta = temp;
				if(alfa >= beta)
					return alfa;
			}
			return beta;
		}
	}
	
	/** Skladowa informujaca, ktorym w kolejnosci graczem jest AI. Jesli w grze AI nie ma, parametr ma wartosc -1. */
	// nadanie wartosci w f-cji odbierajacej ustawienia od pakietu obslugi plikow
	private final int ktoryJestAI;
	private final int glebokoscDrzewa;
	private final Vector<HeurystykaZWaga> mojeHeurystyki;
	private final int[] indeksyKolumn = {3, 4, 2, 5, 1, 6, 0};
	private Random rand = new Random();
	
	
	//private DrzewoGry drzewoGry;
	
	class DrzewoGry
	{
		/** korzen drzewa.*/
		private Stan aktualnyStan;
		private final int glebokoscDrzewa;
		//private final Plansza oryginalnaPlansza; Potrzebne?
				
		/**
		 * Konstruktor Drzewa gry. Do stworzenia drzewa wykorzystuje algorytm MinMax z przycinaniem alfa - beta.
		 * @param heurystyki - wektor heurystyk z wagami (wagi w srodku heurystyk)
		 * @param glebokoscD - poziom drzewa, > 0
		 * @param AI - Przynaleznosc, czyli tak naprawde ktore zetony sa AI.
		 */
		public DrzewoGry(int glebokoscD, final Plansza oryginalnaPlansza, Przynaleznosc AI)
		{
			glebokoscDrzewa = glebokoscD;
			//do Stanu przekazujemy kopie?
			aktualnyStan = new Stan(oryginalnaPlansza, 0);
		}
		
		/** Metoda zwraca indeks kolumny, do ktorej AI chce wrzucic zeton.
		 * 	Przeszukuje	dzieci aktualnegoStanu w poszukiwaniu tego dziecka,
		 * 	ktore ma taka sama wartosc oceny jak ocena aktualnegoStanu.
		 * 	Jesli kilka dzieci ma taka sama wartosc - zwraca losowe.
		 */
		public int ktoraKolumnaRuch()
		{
			//TODO
			return 0;
		}
		
		class Stan
		{
			private int ocena;
			private Plansza makieta;
			private Stan[] dzieci;
			
			/** Tworzy sie rekurencyjnie.
			 * glebokosc parzysta - my wybieramy ruch, nieparzysta - przeciwnik.
			 */
			public Stan(final Plansza makieta, int glebokosc)
			{
				if(glebokosc == glebokoscDrzewa)
					ocena = getOcena();
				
				/*galezie = new Stan[7];
				for(int i=0;i<7;++i)
				{
					if(doDna==0)
						galezie[i]=null;
					else
					{
						galezie[i]= new Stan(doDna -1);
						//dodac makiete - kopie, a nie referencje
					}
				}*/
			}
			
			public int getOcena()
			{
				return ocena;
			}
		
			/**
			 * Wgrywa planszê z ostatnim ruchem
			 * tworzy drzewo
			 * na podstawie drzewa wybiera kolumnê
			 * @return kolumna
			 */
			/*public int wybierzKolumne(Plansza plansza)
			{
				oryginalnaPlansza=plansza;
				tworzDrzewo();
			
				//uzyjHeurystyk()
				//To do
			
				int kolumna = analizaDrzewa();
				return kolumna;
			}*/
		
		
			//////////////////////////////////// PRIVATE///////////////////////////////	
		
			/**
			 * Funkcja oceniaj¹ca dany ruch. Sumuje oceny ruchu/stanu gry przemnozone przez wagi.
			 * @return wartoœæ danego ruchu
			 */
			private int wartoscRuchu(Plansza makietaRuchu, int kolumna)
			{
				//TODO
				//return mojaHeurystyka.getWartosc(makietaRuchu, kolumna);
				return 0;
			}
		
		}
	}

}
