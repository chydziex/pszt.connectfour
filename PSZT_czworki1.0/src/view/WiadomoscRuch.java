package view;

public class WiadomoscRuch extends Wiadomosc
{
	public WiadomoscRuch(int ktoraKolumna)
	{
		super();
		ruch = true;
		this.ktoraKolumna = ktoraKolumna;
	}
	
	public int getKtoraKolumna()
	{
		return ktoraKolumna;
	}
	
	private final int ktoraKolumna;
}
