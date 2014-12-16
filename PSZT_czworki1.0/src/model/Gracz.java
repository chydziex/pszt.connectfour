package model;

public class Gracz {

	/**Zmienna informuj�ca czy aktualny gracz to cz�owiek czy komuter*/
	private RodzajeGraczy aktualnyGracz;
	
	/**Zmienna informuj�ca kt�ry gracz aktualnie gra, liczone od 0*/
	private int ktoryGracz;
	
	/**zmienna informuj�ca jaki jest tryb gry, na podstawie enum Tryby*/
	private Tryby trybGry;
	
	/**Konstruktor klasy gracz*/
	Gracz(Tryby tryb)
	{
		trybGry=tryb;
		ktoryGracz=0;
		if(trybGry.equals(Tryby.AIvsAI))
			aktualnyGracz=RodzajeGraczy.KOMPUTER;
		else if(trybGry.equals(Tryby.CZLOWIEKvsCZLOWIEK))
			aktualnyGracz=RodzajeGraczy.CZLOWIEK;
	}
	
	/**Geter do zmiennej trybGry*/
	public Tryby getTrybGry()
	{
		return trybGry;
	}
	
	public void ustawAI(int ktoreAI)
	{
		if(trybGry.equals(Tryby.AIvsCZLOWIEK))
			if(ktoreAI==0)
				aktualnyGracz=RodzajeGraczy.KOMPUTER;
			else
				aktualnyGracz=RodzajeGraczy.CZLOWIEK;
		
		return;
	}
	
	/**Funkcja zmienia warto�� zmiennej ktoryGracz i przestawia zmienn� aktualny gracz gdy gra jest w trybie AIvsCZLOWIEK*/
	public void zmienGracza()
	{
			ktoryGracz=(++ktoryGracz)%2;
			
		if(trybGry.equals(Tryby.AIvsCZLOWIEK))
			if(aktualnyGracz.equals(RodzajeGraczy.CZLOWIEK))
				aktualnyGracz=RodzajeGraczy.KOMPUTER;
			else
				aktualnyGracz=RodzajeGraczy.CZLOWIEK;
		return;
	}
	
	
	/**Geter do zmiennej aktualnyGracz*/
	public RodzajeGraczy czyjRuch()
	{
		return aktualnyGracz;
	}
	
	/**Geter do zmiennej ktoryGracz*/
	public int getKtoryGracz()
	{
		return ktoryGracz;
	}
}
