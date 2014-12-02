package sztucznaInteligencja;

import model.Plansza;
import model.Przynaleznosc;


public class HeurystykaMaxDl extends Heurystyka {

	
	
	public HeurystykaMaxDl(Przynaleznosc AI) {
		super(AI);
		// TODO Auto-generated constructor stub
	}

	protected int obliczanieWartosci(Plansza makietaRuchu, int kolumna, int ktoryGracz)
	{
		Przynaleznosc gracz = null;
		if(ktoryGracz == 0)
			gracz = Przynaleznosc.GRACZ1;
		else if(ktoryGracz == 1)
			gracz = Przynaleznosc.GRACZ2;
		Ciagi mojCiag = makietaRuchu.sasiednieCiagi(gracz, kolumna);
		
		//if sprawdzaj¹cy czy mo¿na utworzyæ czwórkê:
		
		if((mojCiag.getLewoSkosDol()+mojCiag.getLewoSkosGora()+1)>=4||(mojCiag.getPrawoSkosDol()+mojCiag.getPrawoSkosGora()+1)>=4||(mojCiag.getPion()+1)>=4||(mojCiag.getPoziomLewo()+mojCiag.getPoziomPrawo()+1)>=4)
			return 100;
		else
			return algorytmMaxDlugoscCiagu(mojCiag);
	}
	
	/**
	 * Alogrytm oceniaj¹cy danej heurystyki
	 * na podstawie paczki mojCiag wyszyukuje najdluzszy ciag i zwraca jego dlugosc
	 * @param mojCiag
	 * @return dlugosc najdluzszego ciagu
	 */
	private int algorytmMaxDlugoscCiagu(Ciagi mojCiag)
	{
		int lewoSkos=mojCiag.getLewoSkosDol()+mojCiag.getLewoSkosGora()+1;
		int prawoSkos=mojCiag.getPrawoSkosDol()+mojCiag.getPrawoSkosGora()+1;
		int pion=mojCiag.getPion()+1;
		int poziom=mojCiag.getPoziomLewo()+mojCiag.getPoziomPrawo()+1;
		int skosy=(int)(Math.max(lewoSkos, prawoSkos));
		int glowne=(int)(Math.max(pion, poziom));
		return (int)(Math.max(skosy, glowne));
	}
		
}
