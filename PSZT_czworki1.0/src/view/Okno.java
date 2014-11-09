package view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Wspolrzedne;

class Okno extends JFrame
{
	public Okno(int iloscWierszy, int iloscKolumn)
	{
		// TODO
		super("Gra w czw�rki");
		setDefaultCloseOperation(EXIT_ON_CLOSE);;
		setVisible(true);
		setLayout(null);
		panelPlanszy = new Plansza(iloscWierszy, iloscKolumn);
		Rectangle rect = new Rectangle(panelPlanszy.getBounds());
		
		// do modyfikacji
		setPreferredSize(new Dimension(rect.width + rect.y + 16, rect.height + rect.y + 38));
		setLocation(new Point(750, 250));
		pack();
	}
	
	/** Metoda wy��czaj�ca panel uniemo�liwja�c graczowi wrzut �etonu.*/
	public void wylaczPanelInterfejsuGracza()
	{
		// TODO
	}
	
	/** Metoda wy�wietlaj�ca panel z gr�.*/
	public void wyswietlPanelZGra()
	{
		add(panelPlanszy);
	}
	
	/** Metoda wy�wietlaj�ca �eton rodzaju zale�nego od ktoryGracz na planszy wskazanych wsp�rz�dnych.*/
	public void dodajZeton(Wspolrzedne wspolrzedne, int ktoryGracz)
	{
		panelPlanszy.dodajZeton(wspolrzedne, ktoryGracz);
	}
	
	private InterfejsGracza panelInterfejsuGracza = new InterfejsGracza();
	private Plansza panelPlanszy;
	
	final static int przesunieciePoziom = 16;
	final static int przesuneciePion = 38;
}
