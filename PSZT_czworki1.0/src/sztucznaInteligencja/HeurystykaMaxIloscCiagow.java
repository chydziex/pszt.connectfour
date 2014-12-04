package sztucznaInteligencja;
import model.Plansza;
import model.Przynaleznosc;




public class HeurystykaMaxIloscCiagow extends Heurystyka {

	
	protected int obliczanieWartosci(final Plansza makietaRuchu, int kolumna, int ktoryGracz)
	{
		Przynaleznosc gracz = null;
		if(ktoryGracz == 0)
			gracz = Przynaleznosc.GRACZ1;
		else if(ktoryGracz == 1)
			gracz = Przynaleznosc.GRACZ2;
		Ciagi mojCiag = makietaRuchu.sasiednieCiagi(gracz, kolumna);
		
		
		show(mojCiag);
		
		//if sprawdzaj¹cy czy mo¿na utworzyæ czwórkê
		if((mojCiag.getLewoSkosDol()+mojCiag.getLewoSkosGora()+1)>=4||(mojCiag.getPrawoSkosDol()+mojCiag.getPrawoSkosGora()+1)>=4||(mojCiag.getPion()+1)>=4||(mojCiag.getPoziomLewo()+mojCiag.getPoziomPrawo()+1)>=4)
			return 100;
		else
			return algorytmMaxIloscCiagow(mojCiag);
	}
	
	private int algorytmMaxIloscCiagow(Ciagi mojCiag)
	{
		int iloscCiagow=0;
		if(mojCiag.getLewoSkosDol()!=0||mojCiag.getLewoSkosGora()!=0)
			++iloscCiagow;
		if(mojCiag.getPrawoSkosDol()!=0||mojCiag.getPrawoSkosGora()!=0)
			++iloscCiagow;
		if(mojCiag.getPion()!=0)
			++iloscCiagow;
		if(mojCiag.getPoziomLewo()!=0||mojCiag.getPoziomPrawo()!=0)
			++iloscCiagow;
		return iloscCiagow;
	}
}
