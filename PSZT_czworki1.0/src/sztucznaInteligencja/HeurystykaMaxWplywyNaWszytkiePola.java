package sztucznaInteligencja;

import model.Plansza;
import model.Przynaleznosc;

public class HeurystykaMaxWplywyNaWszytkiePola extends Heurystyka {

	public HeurystykaMaxWplywyNaWszytkiePola(Przynaleznosc AI) {
		super(AI);
		// TODO Auto-generated constructor stub
	}

	protected int obliczanieWartosci(Plansza makietaRuchu, int kolumna)
	{
		Ciagi mojCiag = makietaRuchu.sasiednieCiagi(AI, kolumna);
		Ciagi ciagPrzeciwnika = makietaRuchu.sasiednieCiagi(przeciwnik, kolumna);
		//if sprawdzaj¹cy czy mo¿na utworzyæ czwórkê
		if((mojCiag.getLewoSkosDol()+mojCiag.getLewoSkosGora()+1)>=4||(mojCiag.getPrawoSkosDol()+mojCiag.getPrawoSkosGora()+1)>=4||(mojCiag.getPion()+1)>=4||(mojCiag.getPoziomLewo()+mojCiag.getPoziomPrawo()+1)>=4)
			return 100;
		else
			return algorytmMaxWplywuNaWszystkiePola(mojCiag, ciagPrzeciwnika);

	}
	
	private int algorytmMaxWplywuNaWszystkiePola(Ciagi mojCiag, Ciagi przeciwnikaCiag)
	{
		int wagaSwojegoPola = 2;
		int wagaPolaPrzeciwnika = 1;
		int lewoSkos, prawoSkos, pion, poziom;
		
		
		lewoSkos=mojCiag.getLewoSkosDol()+mojCiag.getLewoSkosGora();
		prawoSkos=mojCiag.getPrawoSkosDol()+mojCiag.getPrawoSkosGora();
		pion=mojCiag.getPion();
		poziom=mojCiag.getPoziomLewo()+mojCiag.getPoziomPrawo();
		int sumaMoja = lewoSkos + prawoSkos + pion + poziom + 1;
		
		lewoSkos=przeciwnikaCiag.getLewoSkosDol()+przeciwnikaCiag.getLewoSkosGora();
		prawoSkos=przeciwnikaCiag.getPrawoSkosDol()+przeciwnikaCiag.getPrawoSkosGora();
		pion=przeciwnikaCiag.getPion();
		poziom=przeciwnikaCiag.getPoziomLewo()+przeciwnikaCiag.getPoziomPrawo();
		int sumaPrzeciwnika = lewoSkos + prawoSkos + pion + poziom;
		
		return sumaMoja * wagaSwojegoPola + sumaPrzeciwnika * wagaPolaPrzeciwnika;
	}
	
}
