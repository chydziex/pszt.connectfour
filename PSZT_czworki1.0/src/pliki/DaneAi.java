package pliki;


public class DaneAi {

	
	public DaneAi(Integer wagiHeurystyk [], int glebokoscDrzewa, int kolejnosc){
		
		this.wagiHeurystyk = wagiHeurystyk.clone();
		this.glebokoscDrzewa = glebokoscDrzewa;
		this.kolejnosc = kolejnosc;

	}
	
	
	public Integer[] getWagiHeurystyk() {
		return wagiHeurystyk;
	}
	public int getGlebokoscDrzewa() {
		return glebokoscDrzewa;
	}
	public int getKolejnosc() {
		return kolejnosc;
	}
	
	@Override
	public String toString(){
		
		if(wagiHeurystyk !=null)
			return kolejnosc + "\t" + glebokoscDrzewa + "\t" +wagiHeurystyk[0] + "\t" + wagiHeurystyk[1] + "\t" + wagiHeurystyk[2] + "\t" + wagiHeurystyk[3] + "\t" + wagiHeurystyk[4]+ "\t" ;
		else
			return kolejnosc + "\t" + glebokoscDrzewa + "\t" + "wagi puste";
		
	}


	private Integer[] wagiHeurystyk;
	private int glebokoscDrzewa;
	private int kolejnosc;
}
