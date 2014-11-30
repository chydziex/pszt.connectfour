package sztucznaInteligencja;

import model.Plansza;

/** Wezel drzewa algorytmu MinMax */

//TODO
public class Stan {

	private int ocena;
	private Plansza makieta;
	private Stan[] galezie;
	
	public Stan(Plansza makieta, int doDna)
	{
		//TODO tworzenie rekurencyjne
		/*
		galezie = new Stan[7];
		for(int i=0;i<7;++i)
		{
			if(doDna==0)
				galezie[i]=null;
			else
			{
				galezie[i]= new Stan(doDna -1);
				//dodac makiete - kopie, a nie referencje
			}
		}
		*/
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public Plansza getMakieta() {
		return makieta;
	}

	public void setMakieta(Plansza makieta) {
		this.makieta = makieta;
	}

	public Stan[] getGalezie() {
		return galezie;
	}

	public void setGalezie(Stan[] galezie) {
		this.galezie = galezie;
	}
}
