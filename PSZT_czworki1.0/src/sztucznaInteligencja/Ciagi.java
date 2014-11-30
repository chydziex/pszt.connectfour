package sztucznaInteligencja;

import model.Przynaleznosc;



public class Ciagi {

	private int lewoSkosGora;
	private int prawoSkosGora;
	private int lewoSkosDol;
	private int prawoSkosDol;
	private int pion;
	private int poziomLewo;
	private int poziomPrawo;
	private Przynaleznosc gracz;
	
	public Ciagi(Przynaleznosc gracz)
	{
		this.gracz = gracz;
	}
	
	public Przynaleznosc getGracz() {
		return gracz;
	}
	
	public int getLewoSkosGora() {
		return lewoSkosGora;
	}

	public void setLewoSkosGora(int lewoSkosGora) {
		this.lewoSkosGora = lewoSkosGora;
	}

	public int getPrawoSkosGora() {
		return prawoSkosGora;
	}

	public void setPrawoSkosGora(int prawoSkosGora) {
		this.prawoSkosGora = prawoSkosGora;
	}

	public int getLewoSkosDol() {
		return lewoSkosDol;
	}

	public void setLewoSkosDol(int lewoSkosDol) {
		this.lewoSkosDol = lewoSkosDol;
	}

	public int getPrawoSkosDol() {
		return prawoSkosDol;
	}

	public void setPrawoSkosDol(int prawoSkosDol) {
		this.prawoSkosDol = prawoSkosDol;
	}

	public int getPion() {
		return pion;
	}
	public void setPion(int pion) {
		this.pion = pion;
	}
	public int getPoziomLewo() {
		return poziomLewo;
	}
	public void setPoziomLewo(int poziomLewo) {
		this.poziomLewo = poziomLewo;
	}
	public int getPoziomPrawo() {
		return poziomPrawo;
	}
	public void setPoziomPrawo(int poziomPrawo) {
		this.poziomPrawo = poziomPrawo;
	}
	
	
}
