package wiadomosc;

public class WiadomoscRuchMyszki extends Wiadomosc
{
	public WiadomoscRuchMyszki(int x)
	{
		super();
		ruchMyszki = true;
		xMyszki = x;
	}
	
	public int getXMyszki()
	{
		return xMyszki;
	}
	
	protected final int xMyszki;
}
