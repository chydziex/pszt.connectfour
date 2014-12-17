package view;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

class InterfejsGracza extends JPanel
{
	// TODO
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InterfejsGracza()
	{
		
		// TODO Auto-generated constructor stub
		setLayout(null);
		setBounds(Stale.interfejsGracza);
		setOpaque(false);
		this.setBackground(Color.MAGENTA);
		dodajPaneleInterfejsu();
	}
	
	public void dodajListeneraRuchuMyszki(MouseMotionListener l)
	{
		addMouseMotionListener(l);
	}
	
	public void dodajListeneraRuchuGracza(MouseListener l)
	{
		addMouseListener(l);
	}
	
	/** Metoda wywo³ywana przez mouseMotionListener. Wyœwietla odpowiednia strza³kê nad odpowiedni¹ kolumna
	 * w zale¿noœci od po³o¿enia myszki.
	 * @param xMyszki
	 * @param yMyszki
	 */
	public void aktualizujPolozenieStrzalki(int xMyszki)
	{
		// TODO
		polozenieStrzalki = ((int)(xMyszki / Obrazki.obrazekPustePole.getIconWidth())) * Obrazki.obrazekPustePole.getIconWidth();
		Rectangle rect = new Rectangle(polozenieStrzalki, 0, Obrazki.obrazkiStrzalek[0].getIconWidth(), Obrazki.obrazkiStrzalek[1].getIconHeight());
		strzalka.setBounds(rect);
	}
	
	public void aktualizujPolozenieZetonu(int xMyszki)
	{
		// TO_TEST
		Rectangle rect = new Rectangle(xMyszki - (int)(Obrazki.obrazkiZetonow[0].getIconWidth()*0.5) + 1, 0, Obrazki.obrazkiZetonow[0].getIconWidth(), Obrazki.obrazkiZetonow[0].getIconHeight());
		zeton.setBounds(rect);
	}
	
	public void ustawRodzajStrzalki(int ktoraStrzalka)
	{
		strzalka.setIcon(Obrazki.obrazkiStrzalek[ktoraStrzalka]);
	}
	
	/** Metoda wyœwietlaj¹ca ¿eton danego gracza.*/
	public void ustawRodzajZetonu(int ktoryGracz)
	{
		zeton.setIcon(Obrazki.obrazkiZetonow[ktoryGracz]);
	}
	
	//Potrzebne? Moze lepiej dodawac panel do okno i ewentualnie go usuwac?
	public void wylacz()
	{
		//TODO
		setVisible(false);
		
	//	setEnabled(false);
		
		
		
	}
	
	/** Metoda zwracaj¹ca wspó³rzêdn¹ x strza³ki.*/
	public int getKtoraKolumneWskazujeStrzalka()
	{
		return (int)(polozenieStrzalki/Obrazki.obrazekPustePole.getIconWidth());
	}
	
	private void dodajPaneleInterfejsu()
	{
		JPanel pan = new JPanel();
		pan.setBounds(Stale.panelZZetonemWInterfejsieGracza);
		pan.setLayout(null);
		//pan.setBackground(Color.BLUE);
		pan.setOpaque(false);
		pan.add(zeton);
		zeton.setBounds(0, 0, Obrazki.obrazkiZetonow[0].getIconWidth(), Obrazki.obrazkiZetonow[0].getIconHeight());
		add(pan);
		
		pan = new JPanel();
		pan.setBounds(Stale.panelZeStrzalkaWInterfejsieGracza);
		pan.setLayout(null);
		pan.setOpaque(false);
		//pan.setBackground(Color.YELLOW);
		pan.add(strzalka);
		strzalka.setBounds(0, 0, Obrazki.obrazkiStrzalek[0].getIconWidth(), Obrazki.obrazkiStrzalek[0].getIconHeight());
		add(pan);
	}
	
	/** Jeden label, w którym tylko zmieniamy ikonê ¿etonu w zale¿noœci od gracza.*/
	private final JLabel strzalka = new JLabel();
	private final JLabel zeton = new JLabel();
	private int polozenieStrzalki;
}
