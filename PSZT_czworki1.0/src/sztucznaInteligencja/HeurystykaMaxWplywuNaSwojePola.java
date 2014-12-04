package sztucznaInteligencja;
import model.Plansza;
import model.Przynaleznosc;



public class HeurystykaMaxWplywuNaSwojePola extends Heurystyka {

	
	protected int obliczanieWartosci(Plansza makietaRuchu, int kolumna, int ktoryGracz)
	{
		Przynaleznosc gracz = null;
		if(ktoryGracz == 0)
			gracz = Przynaleznosc.GRACZ1;
		else if(ktoryGracz == 1)
			gracz = Przynaleznosc.GRACZ2;
		Ciagi mojCiag = makietaRuchu.sasiednieCiagi(gracz, kolumna);
		
		//if sprawdzaj¹cy czy mo¿na utworzyæ czwórkê
		if((mojCiag.getLewoSkosDol()+mojCiag.getLewoSkosGora()+1)>=4||(mojCiag.getPrawoSkosDol()+mojCiag.getPrawoSkosGora()+1)>=4||(mojCiag.getPion()+1)>=4||(mojCiag.getPoziomLewo()+mojCiag.getPoziomPrawo()+1)>=4)
			return 100;
		else
			return algorytmMaxWplywuNaSwojePola(mojCiag);
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
