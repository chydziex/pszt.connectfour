package model;

import java.util.Random;

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
	}
	
	/**Geter do zmiennej trybGry*/
	public Tryby getTrybGry()
	{
		return trybGry;
	}
	
	/**W przypadku gdy gracze s¹ tego samego rodzaju funkcja losuje wartoœæ zmiennej ktoryGracz, w przeciwnym przypadku losuje zmienn¹ aktualnyGracz*/
	public void losujGracza()
	{
		Random r = new Random(); 
		int temp;
		temp=r.nextInt(2);
		
		if(trybGry==Tryby.CZLOWIEKvsCZLOWIEK||trybGry==Tryby.AIvsAI)
			ktoryGracz=temp;
		
		if(trybGry.equals(Tryby.AIvsCZLOWIEK))
			if(temp==0)
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
