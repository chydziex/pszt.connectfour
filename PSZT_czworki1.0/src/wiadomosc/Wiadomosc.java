package wiadomosc;
import model.Tryby;

public abstract class Wiadomosc
{
	public Wiadomosc()
	{
		//TODO
	}
	
	public boolean czyNowaGra()
	{
		return nowaGra;
	}
	
	public Tryby jakiTryb()
	{
		return tryb;
	}
	
	public boolean czyRuch()
	{
		return ruch;
	}
	
	public boolean czyRuchMyszki()
	{
		return ruchMyszki;
	}
	

	
	/** Czy to wiadomo�� informuj�ca o wyborze kolumny przez cz�owieka.*/
	protected boolean ruch = false;
	
	protected Tryby tryb = null;
	
	protected boolean nowaGra = false;
	
	protected boolean ruchMyszki = false;
}
