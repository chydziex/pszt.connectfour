package pliki;


public class DaneAi {

	
	public DaneAi(Integer wagiHeurystyk [], int glebokoscDrzewa, int czasOczekiwaniaNaRuch){
		
		this.wagiHeurystyk = wagiHeurystyk.clone();
		this.glebokoscDrzewa = glebokoscDrzewa;
		this.czasOczekiwaniaNaRuch = czasOczekiwaniaNaRuch;

	}
	
	
	public Integer[] getWagiHeurystyk() {
		return wagiHeurystyk;
	}
	public int getGlebokoscDrzewa() {
		return glebokoscDrzewa;
	}
	public int getCzasOczekiwaniaNaRuch() {
		return czasOczekiwaniaNaRuch;
	}
	
	@Override
	public String toString(){
		
		if(wagiHeurystyk !=null)
			return glebokoscDrzewa + "\t" + czasOczekiwaniaNaRuch + "\t" +wagiHeurystyk[0] + "\t" + wagiHeurystyk[1] + "\t" + wagiHeurystyk[2] + "\t" + wagiHeurystyk[3] + "\t" + wagiHeurystyk[4]+ "\t" ;
		else
			return glebokoscDrzewa + "\t" + czasOczekiwaniaNaRuch + "\t" + "puste";
		
	}


	private Integer[] wagiHeurystyk;
	private int glebokoscDrzewa;
	private int czasOczekiwaniaNaRuch;
}
