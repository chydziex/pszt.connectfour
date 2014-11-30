package sztucznaInteligencja;

import java.util.Vector;

import model.Model;
import model.Plansza;
import model.Przynaleznosc;

//TODO
public class SztucznaInteligencja
{
	public SztucznaInteligencja(Vector<HeurystykaZWaga> mojeHeurystyki)
	{
		this.mojeHeurystyki = mojeHeurystyki;
	}
	
	public int wybierzKolumne(final Plansza plansza)
	{
		//TODO		

		int najlepszaKolumna = 0, max = Integer.MIN_VALUE, temp;
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
			//wrzucenie zetonu do "wirtualnej" planszy
			//kopiaPlanszy.wrzucZeton(i);
			temp = alfaBeta(kopiaPlanszy);
		}
		
		return 0;
	}
	
	private final Vector<HeurystykaZWaga> mojeHeurystyki;
	private final int[] indeksyKolumn = {3, 4, 2, 5, 1, 6, 0};
	
	/** funkcja relizujaca algorytm alfa - beta. Zwraca wartosc f-cji oceniajacej dany ruch. */
	private int alfaBeta(final Plansza plansza)
	{
		// TODO
		return 0;
	}
	
	
	
	
	
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
