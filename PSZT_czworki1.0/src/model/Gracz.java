package model;

public class Gracz {

	/**Zmienna informuj¹ca czy aktualny gracz to cz³owiek czy komuter*/
	private RodzajeGraczy aktualnyGracz;
	
	/**Zmienna informuj¹ca który gracz aktualnie gra, liczone od 0*/
	private int ktoryGracz;
	
	/**zmienna informuj¹ca jaki jest tryb gry, na podstawie enum Tryby*/
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
	
	/**Funkcja zmienia wartoœæ zmiennej ktoryGracz i przestawia zmienn¹ aktualny gracz gdy gra jest w trybie AIvsCZLOWIEK*/
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
