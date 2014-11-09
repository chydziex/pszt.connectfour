package view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Tryby;
import model.Wspolrzedne;

class Okno extends JFrame {
	public Okno(int iloscWierszy, int iloscKolumn) {
		// TODO
		super("Gra w czwórki");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setVisible(true);
		setLayout(null);
		panelPlanszy = new Plansza(iloscWierszy, iloscKolumn);
		Rectangle rect = new Rectangle(panelPlanszy.getBounds());

		// do modyfikacji
		setPreferredSize(new Dimension(rect.width + rect.y + 16, rect.height
				+ rect.y + 38));
		setLocation(new Point(750, 250));

		setResizable(false);
		pack();
	}

	/** Metoda wy³¹czaj¹ca panel uniemo¿liwja¹c graczowi wrzut ¿etonu. */
	public void wylaczPanelInterfejsuGracza() {
		// TODO
		panelInterfejsuGracza.wylacz();
	}

	/** Metoda wyœwietlaj¹ca panel z gr¹. */
	public void wyswietlPanelZGra() {
		add(panelPlanszy);
	}

	void wyswietlPanelWyboruTrybu() {

		add(panelWyboruTrybu);
		panelWyboruTrybu.setVisible(true);

	}

	void wyswietlPanelWyboruAlgorytmu() {

		panelWyborAlgorytmuAi.setVisible(true);
		add(panelWyborAlgorytmuAi);
	}

	void wylaczPanelWyboruTrybu() {

		panelWyboruTrybu.wylacz();
		remove(panelWyboruTrybu);
	}

	void wylaczPanelWyboruAlgorytmu() {

		panelWyborAlgorytmuAi.wylacz();
		remove(panelWyborAlgorytmuAi);
	}

	/**
	 * Metoda wyœwietlaj¹ca ¿eton rodzaju zale¿nego od ktoryGracz na planszy
	 * wskazanych wspó³rzêdnych.
	 */
	public void dodajZeton(Wspolrzedne wspolrzedne, int ktoryGracz) {
		panelPlanszy.dodajZeton(wspolrzedne, ktoryGracz);
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

	private InterfejsGracza panelInterfejsuGracza = new InterfejsGracza();
	private Plansza panelPlanszy;

	private WyborTrybuGry panelWyboruTrybu = new WyborTrybuGry();
	private WyborAlgorytmuAi panelWyborAlgorytmuAi = new WyborAlgorytmuAi();

	final static int przesunieciePoziom = 16;
	final static int przesuneciePion = 38;

}
