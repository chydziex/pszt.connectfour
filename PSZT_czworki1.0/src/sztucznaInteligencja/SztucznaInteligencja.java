package sztucznaInteligencja;

import java.util.Vector;

import model.Model;
import model.Plansza;

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
		
		drzewoGry = new DrzewoGry(plansza);
		return drzewoGry.ktoraKolumnaRuch();
		
		
		/* wersja bez drzewa
		Vector<Integer> vectorNajlepszychKolumn= new Vector<Integer>();
		int najlepszaKolumna = 0, maxKolumna = -1;
		double aktualnaWartoscWezla, maxWartoscWezla = Double.NEGATIVE_INFINITY, alfa = Double.NEGATIVE_INFINITY, beta = Double.POSITIVE_INFINITY;
		Plansza kopiaPlanszy = null;
		
		for(int i = 0; i < Model.iloscKolumn; i++)
		{
			System.out.println("Kolumna: " + indeksyKolumn[i]);
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
		*/
	}
	
	/** Metoda wypisujaca interesujace komunikaty o drzewie, czyli */
	public void pisz()
	{
		// TODO
		drzewoGry.wypiszOcenaRoot();
		drzewoGry.wypiszGlebokoscDrzewaOsiagnieta();
		drzewoGry.wypiszIloscWezlowWDrzewie();
		drzewoGry.wypiszWartosciWezlow();
		System.out.println("WYBIERAM KOLUMNE " + drzewoGry.ktoraKolumnaRuch());
	}
	
	public double ocenWezel(final Plansza plansza, int doKtorejKolumnyChcemyWrzucic, int ktoryGracz)
	{
		double ocena = 0;
		if(mojeHeurystyki == null)
			return 0;
		for(HeurystykaZWaga heurystyka : mojeHeurystyki)
			ocena += heurystyka.getWartosc(plansza, doKtorejKolumnyChcemyWrzucic, ktoryGracz);
		return ocena;
	}
	
	/** funkcja relizujaca algorytm alfa - beta. Zwraca wartosc f-cji oceniajacej dany ruch.
	 *  glebokosc: ile ruchow do przodu analizujemy. 1 - rozpatrujemy stan gry po naszym pierwszym ruchu.
	 *  plansza: "wirtualna" plansza po wrzuceniu do niej zetonu reprezentujacego nasz hipotetyczny ruch, ktory analizujemy.
	 */
	/*
	private double alfaBeta(final Plansza plansza, int doKtorejKolumnyChcemyWrzucic, int glebokosc, double alfa, double beta)
	{
		// TODO
		System.out.println("Glebokosc: " + glebokosc + " Kolumna: " + doKtorejKolumnyChcemyWrzucic);
		double temp;
		Plansza kopiaPlanszy = null;
		try
		{
			kopiaPlanszy = (Plansza) plansza.clone();
			
		}catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
		if(glebokosc == glebokoscDrzewa)
		{
			if(glebokosc%2 == 0)
			{
				//plansza.pisz();
				System.out.println("Ocena: " + -ocenWezel(plansza, doKtorejKolumnyChcemyWrzucic, (ktoryJestAI + 1)%2));
				return -ocenWezel(plansza, doKtorejKolumnyChcemyWrzucic, (ktoryJestAI + 1)%2);
			}
			else
			{
				//plansza.pisz();
				System.out.println("Ocena: " + ocenWezel(plansza, doKtorejKolumnyChcemyWrzucic, ktoryJestAI));
				return ocenWezel(plansza, doKtorejKolumnyChcemyWrzucic, ktoryJestAI);
			}
		}
		if(glebokosc %2 == 1)
		{
			if(kopiaPlanszy.sprawdzCzyWygrana(doKtorejKolumnyChcemyWrzucic, ktoryJestAI))
				return 300;
		}
		else
			if(kopiaPlanszy.sprawdzCzyWygrana(doKtorejKolumnyChcemyWrzucic, (ktoryJestAI + 1)%2))
				return -300;
	
		// minimalizujemy
		if(glebokosc %2 == 1)
		{
			for(int i = 0; i < Model.iloscKolumn; i++)
			{
				if(!kopiaPlanszy.czyRuchJestDozwolony(indeksyKolumn[i]))
					continue;
				temp = alfaBeta(kopiaPlanszy, indeksyKolumn[i], glebokosc + 1, alfa, beta);
				//System.out.println("Temp " + indeksyKolumn[i] + ": " + temp );
				
			
				if(temp < beta)
					beta = temp;
				if(alfa >= beta)
					return alfa;
			}
			return beta;
			
		}
		// maksymalizujemy
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
				//kopiaPlanszy.sprawdzCzyWygrana(indeksyKolumn[i], (ktoryJestAI + 1)%2 );
				temp = alfaBeta(kopiaPlanszy, indeksyKolumn[i], glebokosc + 1, alfa, beta);
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
	}
	*/
	/** Skladowa informujaca, ktorym w kolejnosci graczem jest AI. Jesli w grze AI nie ma, parametr ma wartosc -1. */
	// nadanie wartosci w f-cji odbierajacej ustawienia od pakietu obslugi plikow
	private final int ktoryJestAI;
	private final int glebokoscDrzewa;
	private final Vector<HeurystykaZWaga> mojeHeurystyki;
	private final int[] indeksyKolumn = {3, 4, 2, 5, 1, 6, 0};
	private final int[] indeksyWezlow = {6, 4, 2, 0, 1, 3, 5};	
	private DrzewoGry drzewoGry;
	
	class DrzewoGry
	{
		/** korzen drzewa.*/
		private Stan aktualnyStan;
		private final Plansza oryginalnaPlansza;
		private int glebokoscOsiagnieta = 0;
		private int iloscWezlow = 0;
				
		/**
		 * Konstruktor Drzewa gry. Do stworzenia drzewa wykorzystuje algorytm MinMax z przycinaniem alfa - beta.
		 * GLEBOKOSC LICZYMY OD 1!
		 * 
		 * @param heurystyki - wektor heurystyk z wagami (wagi w srodku heurystyk)
		 * @param glebokoscD - poziom drzewa, > 1
		 * @param AI - Przynaleznosc, czyli tak naprawde ktore zetony sa AI.
		 */
		public DrzewoGry(final Plansza orygPlansza)
		{
			oryginalnaPlansza = orygPlansza;
			aktualnyStan = new Stan(oryginalnaPlansza, 1, -1, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
		}

		public void wypiszOcenaRoot()
		{
			System.out.println("Wartosc root: " + aktualnyStan.getOcena());
		}
		
		public void wypiszWartosciWezlow()
		{
			for(int i = 0; i < Model.iloscKolumn; i++)
			{
				if(aktualnyStan.dzieci[indeksyWezlow[i]] != null)
				{
					if(aktualnyStan.dzieci[indeksyWezlow[i]].getCzyCiecie())
						System.out.println("Kolumna " + i + " Wartosc: " + "?");
					else
						System.out.println("Kolumna " + i + " Wartosc: " + aktualnyStan.dzieci[indeksyWezlow[i]].getOcena());
				}
				else
					System.out.println("Kolumna " + i + " Wartosc: ?");
			}
		}
		
		public void wypiszIloscWezlowWDrzewie()
		{
			System.out.println("Ilosc Wezlow w drzewie: " + getIloscWezlow());
		}
		
		public void wypiszGlebokoscDrzewaOsiagnieta()
		{
			System.out.println("Glebokosc drzewa osiagnieta: " + glebokoscOsiagnieta);
		}
		
		/** Metoda zwraca indeks kolumny, do ktorej AI chce wrzucic zeton.
		 * 	Przeszukuje	dzieci aktualnegoStanu w poszukiwaniu tego dziecka,
		 * 	ktore ma taka sama wartosc oceny jak ocena aktualnegoStanu.
		 * 	Jesli kilka dzieci ma taka sama wartosc - zwraca losowe.
		 */
		public int ktoraKolumnaRuch()
		{
			for(int i = 0; i < Model.iloscKolumn; i++)
			{
				if(aktualnyStan.dzieci[i] != null && (aktualnyStan.dzieci[i].getOcena() >= aktualnyStan.getOcena()))
					return indeksyKolumn[i];
			}
			return -1;
		}
		
		public int getGlebokoscOsiagnieta()
		{
			return glebokoscOsiagnieta;
		}
		
		public int getIloscWezlow()
		{
			return iloscWezlow;
		}
		
		class Stan
		{
			private double ocena;
			//private Plansza makieta;
			private Stan[] dzieci = new Stan[7];
			private boolean czyCiecie = false;
			
			public Stan(double o)
			{
				ocena = o;
			}
			
			/** Tworzy sie rekurencyjnie.
			 * glebokosc parzysta - my wybieramy ruch, nieparzysta - przeciwnik.
			 */
			public Stan(final Plansza plansza, int glebokosc, int doKtorejKolumnyChcemyWrzucicZeton, double alfa, double beta)
			{
				//System.out.println("tu");
				if(glebokosc > glebokoscOsiagnieta)
					glebokoscOsiagnieta = glebokosc;
				Plansza kopiaPlanszy = null;
				iloscWezlow++;
				//System.out.println("tu " + iloscWezlow);
				if(glebokosc == glebokoscDrzewa)
				{
					//System.out.print("Glebokosc: " + glebokosc + " Kolumna: " + doKtorejKolumnyChcemyWrzucicZeton + " ");
					//ruch AI
					if(glebokosc%2 == 0)
					{
						//plansza.pisz();
						//System.out.println("Ocena: " + ocenWezel(plansza, doKtorejKolumnyChcemyWrzucicZeton, ktoryJestAI));
						ocena =  ocenWezel(plansza, doKtorejKolumnyChcemyWrzucicZeton, ktoryJestAI);
					}
					//ruch przeciwnika
					else
					{
						//plansza.pisz();
						//System.out.println("Ocena: " + -ocenWezel(plansza, doKtorejKolumnyChcemyWrzucicZeton, (ktoryJestAI + 1)%2));
						ocena = -ocenWezel(plansza, doKtorejKolumnyChcemyWrzucicZeton, (ktoryJestAI + 1)%2);
					}
					return;
				}
				
				// maksymalizujemy
				else if(glebokosc%2 == 1)
				{
					//System.out.println("Glebokosc: " + glebokosc + " wrzucilismy do: " + doKtorejKolumnyChcemyWrzucicZeton);
					for(int i = 0; i < Model.iloscKolumn; i++)
					{
						try
						{
							kopiaPlanszy = (Plansza) plansza.clone();	
						}catch (CloneNotSupportedException e)
						{
							e.printStackTrace();
						}
						if(!plansza.czyRuchJestDozwolony(indeksyKolumn[i]))
							continue;
						if(glebokosc + 1 < glebokoscDrzewa)									// jesli nie przedostania gleobokosc, to nie wrzucamy, bo potem bedzie ocena
							if(kopiaPlanszy.sprawdzCzyWygrana(indeksyKolumn[i], ktoryJestAI))
							{
								dzieci[i] = new Stan(300);
								ocena = alfa > 300 ? alfa : 300;
								//System.out.println("Glebokosc: " + glebokosc + " Kolumna do wrzutu: " + indeksyKolumn[i] + " Wygrana, zamykam!");
								return;
							}
						
						dzieci[i] = new Stan(kopiaPlanszy, glebokosc + 1, indeksyKolumn[i], alfa, beta);
						if(dzieci[i].getOcena() > alfa)
							alfa = dzieci[i].getOcena();
						if(alfa >= beta)
						{
							ocena = beta;
							czyCiecie = true;
							//System.out.println("Glebokosc: " + glebokosc + " Kolumna: " + doKtorejKolumnyChcemyWrzucicZeton + " Ocena: " + ocena + " PO CIECIU");
							return;
						}
				
					}
					ocena = alfa;
					//System.out.println("Glebokosc: " + glebokosc + " Kolumna: " + doKtorejKolumnyChcemyWrzucicZeton + " Ocena: " + ocena);
				}
				// minimalizujemy
				else
				{
					//System.out.println("Glebokosc: " + glebokosc + " wrzucilismy do: " + doKtorejKolumnyChcemyWrzucicZeton);
					for(int i = 0; i < Model.iloscKolumn; i++)
					{
						try
						{
							kopiaPlanszy = (Plansza) plansza.clone();	
						}catch (CloneNotSupportedException e)
						{
							e.printStackTrace();
						}
						if(!plansza.czyRuchJestDozwolony(indeksyKolumn[i]))
							continue;
						if(glebokosc + 1 < glebokoscDrzewa)									// jesli nie przedostania gleobokosc, to nie wrzucamy, bo potem bedzie ocena
							if(kopiaPlanszy.sprawdzCzyWygrana(indeksyKolumn[i], (ktoryJestAI + 1)%2))
							{
								dzieci[i] = new Stan(-300);
								ocena = beta < -300 ? beta : -300;
								//System.out.println("Glebokosc: " + glebokosc + " Kolumna do wrzutu: " + indeksyKolumn[i] + " Przegrana, zamykam!");
								return;
							}
						
						dzieci[i] = new Stan(kopiaPlanszy, glebokosc + 1, indeksyKolumn[i], alfa, beta);
						if(dzieci[i].getOcena() < beta)
							beta = dzieci[i].getOcena();
						if(alfa >= beta)
						{
							ocena = alfa;
							czyCiecie = true;
							//System.out.println("Glebokosc: " + glebokosc + " Kolumna: " + doKtorejKolumnyChcemyWrzucicZeton + " Ocena: " + ocena + " PO CIECIU");
							return;
						}
					}
					ocena = beta;
					//System.out.println("Glebokosc: " + glebokosc + " Kolumna: " + doKtorejKolumnyChcemyWrzucicZeton + " Ocena: " + ocena);
				}
			}
			
			public boolean getCzyCiecie()
			{
				return czyCiecie;
			}
			
			public double getOcena()
			{
				return ocena;
			}
		
			/**
			 * Wgrywa plansz� z ostatnim ruchem
			 * tworzy drzewo
			 * na podstawie drzewa wybiera kolumn�
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
		
		}
	}

}
