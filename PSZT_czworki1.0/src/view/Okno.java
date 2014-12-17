package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Tryby;
import model.Wspolrzedne;

class Okno extends JFrame {
	public Okno(int iloscWierszy, int iloscKolumn) {
		// TODO
		super("Gra w czwórki");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		setVisible(true);
		setLayout(null);
		panelPlanszy = new Plansza(iloscWierszy, iloscKolumn);
		// do modyfikacji
		setPreferredSize(Stale.okno);
		setLocation(new Point(750, 250));

		setResizable(false);
		pack();
	}

	/** Metoda wy³¹czaj¹ca panel uniemo¿liwja¹c graczowi wrzut ¿etonu. */
	public void wylaczPanelInterfejsuGracza() {
		// TODO
		panelInterfejsuGracza.wylacz();
		odswiez();
	}

	/** Metoda wyœwietlaj¹ca panel z gr¹. */
	public void wyswietlPanelZGra() {
		add(panelPlanszy);
		odswiez();
	}

	void wyswietlPanelWyboruTrybu() {

		add(panelWyboruTrybu);
		//panelWyboruTrybu.setVisible(true);
		odswiez();
	}

	void wyswietlPanelWyboruAlgorytmu() {

		panelWyborAlgorytmuAi.setVisible(true);
		add(panelWyborAlgorytmuAi);
		odswiez();
	}

	void wylaczPanelWyboruTrybu() {

		//panelWyboruTrybu.wylacz();
		remove(panelWyboruTrybu);
		odswiez();
	}

	void wylaczPanelWyboruAlgorytmu() {

		panelWyborAlgorytmuAi.wylacz();
		remove(panelWyborAlgorytmuAi);
		odswiez();
	}

	/**
	 * Metoda wyœwietlaj¹ca ¿eton rodzaju zale¿nego od ktoryGracz na planszy
	 * wskazanych wspó³rzêdnych.
	 */
	public void dodajZeton(Wspolrzedne wspolrzedne, int ktoryGracz) {
		panelPlanszy.dodajZeton(wspolrzedne, ktoryGracz);
		odswiez();
	}

	/**
	 * Odczytuje wybor trybu gry z panelu wyboru trybu gry.
	 * 
	 * @return
	 */
	public Tryby czytajTrybGry() {

		return panelWyboruTrybu.czytajTrybGry();
	}

	/***
	 * Odczytuje wybor algorytmu AI z panelu wyboru algorytmu. TODO Rozwazyc
	 * ENUM (lub cos innego) do oznaczania roznych algorytmow AI.
	 * 
	 * @return Narazie int - w przyszlosci moze ENUM.
	 */
	public int czytajAlgorytmAi() {

		// TODO
		return panelWyborAlgorytmuAi.czytajAlgorytm();

	}

	void dodajWyborTrybuListener(ActionListener l) {

		panelWyboruTrybu.dodajListeneraPrzycisku(l);
	}

	public void dodajWyborAlgorytmuAiListener(ActionListener l) {

		panelWyborAlgorytmuAi.dodajListeneraPrzycisku(l);

	}
	
	public void dodajPozycjaZetonuIStrzalkiInterfejsListener(MouseMotionListener l) {
		
		panelInterfejsuGracza.dodajListeneraRuchuMyszki(l);
	}
	
	public void dodajRuchGraczaListener(MouseListener l) {
		
		panelInterfejsuGracza.dodajListeneraRuchuGracza(l);
	}
	
	public void dodajKlawiaturaListener(KeyAdapter l) {
			
		setFocusable(true);
		addKeyListener(l);
	}

	public void wyswietlInterfejsGracza(int ktoryGracz)
	{
		panelInterfejsuGracza.ustawRodzajZetonu(ktoryGracz);
		panelInterfejsuGracza.ustawRodzajStrzalki(0);
		add(panelInterfejsuGracza, 0);
		odswiez();
	}
	
	public void wylaczInterfejsGracza()
	{
		remove(panelInterfejsuGracza);
		odswiez();
	}
	
	public void ustawRodzajStrzalki(int ktoraStrzalka)
	{
		panelInterfejsuGracza.ustawRodzajStrzalki(ktoraStrzalka);
	}
	
	public void aktualizujPanelInterfejsuGracza(int xMyszki)
	{
		//TODO
		panelInterfejsuGracza.aktualizujPolozenieZetonu(xMyszki);
		panelInterfejsuGracza.aktualizujPolozenieStrzalki(xMyszki);
		odswiez();
	}
	
	public int ktoraKolumneWskazujeGracz()
	{
		return panelInterfejsuGracza.getKtoraKolumneWskazujeStrzalka();
	}
	
	private void odswiez()
	{
		repaint();
		revalidate();
	}
	
	private InterfejsGracza panelInterfejsuGracza = new InterfejsGracza();
	private Plansza panelPlanszy;

	private WyborTrybuGry panelWyboruTrybu = new WyborTrybuGry();
	private WyborAlgorytmuAi panelWyborAlgorytmuAi = new WyborAlgorytmuAi();
		
}
