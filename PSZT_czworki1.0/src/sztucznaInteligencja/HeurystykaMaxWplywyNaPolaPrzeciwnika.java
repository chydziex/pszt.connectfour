package sztucznaInteligencja;
import model.Plansza;
import model.Przynaleznosc;




public class HeurystykaMaxWplywyNaPolaPrzeciwnika extends Heurystyka {

	

	protected int obliczanieWartosci(Plansza makietaRuchu, int kolumna, int ktoryGracz)
	{
		Przynaleznosc gracz = null, przeciwnik = null;
		if(ktoryGracz == 0)
		{
			gracz = Przynaleznosc.GRACZ1;
			przeciwnik = Przynaleznosc.GRACZ2;
		}
		else if(ktoryGracz == 1)
		{
			gracz = Przynaleznosc.GRACZ2;
			przeciwnik = Przynaleznosc.GRACZ1;
		}
		Ciagi mojCiag = makietaRuchu.sasiednieCiagi(gracz, kolumna);
		Ciagi ciagPrzeciwnika = makietaRuchu.sasiednieCiagi(przeciwnik, kolumna);
		//show(ciagPrzeciwnika);
		//if sprawdzaj¹cy czy mo¿na utworzyæ czwórkê
		if((mojCiag.getLewoSkosDol()+mojCiag.getLewoSkosGora()+1)>=4||(mojCiag.getPrawoSkosDol()+mojCiag.getPrawoSkosGora()+1)>=4||(mojCiag.getPion()+1)>=4||(mojCiag.getPoziomLewo()+mojCiag.getPoziomPrawo()+1)>=4)
			return 100;
		else
			return algorytmMaxWplywuNaWszystkiePola(mojCiag, ciagPrzeciwnika);

	}
	
	private int algorytmMaxWplywuNaWszystkiePola(Ciagi mojCiag, Ciagi przeciwnikaCiag)
	{
		//int wagaSwojegoPola = 2;
		//int wagaPolaPrzeciwnika = 1;
		int lewoSkos, prawoSkos, pion, poziom;
		
/*		
		lewoSkos=mojCiag.getLewoSkosDol()+mojCiag.getLewoSkosGora();
		prawoSkos=mojCiag.getPrawoSkosDol()+mojCiag.getPrawoSkosGora();
		pion=mojCiag.getPion();
		poziom=mojCiag.getPoziomLewo()+mojCiag.getPoziomPrawo();
		int sumaMoja = lewoSkos + prawoSkos + pion + poziom + 1;
		*/
		
		lewoSkos=przeciwnikaCiag.getLewoSkosDol()+przeciwnikaCiag.getLewoSkosGora();
		prawoSkos=przeciwnikaCiag.getPrawoSkosDol()+przeciwnikaCiag.getPrawoSkosGora();
		pion=przeciwnikaCiag.getPion();
		poziom=przeciwnikaCiag.getPoziomLewo()+przeciwnikaCiag.getPoziomPrawo();
		int sumaPrzeciwnika = lewoSkos + prawoSkos + pion + poziom;
		
		//return sumaMoja * wagaSwojegoPola + sumaPrzeciwnika * wagaPolaPrzeciwnika;
		return sumaPrzeciwnika;
	}
	
}
