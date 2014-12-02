package sztucznaInteligencja;

import model.Plansza;
import model.Przynaleznosc;

public class HeurystykaMaxWplywuNaSwojePola extends Heurystyka {

	public HeurystykaMaxWplywuNaSwojePola(Przynaleznosc AI) {
		super(AI);
		// TODO Auto-generated constructor stub
	}

	protected int obliczanieWartosci(Plansza makietaRuchu, int kolumna, int ktoryGracz)
	{
		Ciagi mojeCiagi = makietaRuchu.sasiednieCiagi(AI, kolumna);
		//if sprawdzaj¹cy czy mo¿na utworzyæ czwórkê
		if((mojeCiagi.getLewoSkosDol()+mojeCiagi.getLewoSkosGora()+1)>=4||(mojeCiagi.getPrawoSkosDol()+mojeCiagi.getPrawoSkosGora()+1)>=4||(mojeCiagi.getPion()+1)>=4||(mojeCiagi.getPoziomLewo()+mojeCiagi.getPoziomPrawo()+1)>=4)
			return 100;
		else
			return algorytmMaxWplywuNaSwojePola(mojeCiagi);
	}
	
	private int algorytmMaxWplywuNaSwojePola(Ciagi mojCiag)
	{
		int lewoSkos=mojCiag.getLewoSkosDol()+mojCiag.getLewoSkosGora();
		int prawoSkos=mojCiag.getPrawoSkosDol()+mojCiag.getPrawoSkosGora();
		int pion=mojCiag.getPion();
		int poziom=mojCiag.getPoziomLewo()+mojCiag.getPoziomPrawo();
		return lewoSkos + prawoSkos + pion + poziom + 1;
	}
}
