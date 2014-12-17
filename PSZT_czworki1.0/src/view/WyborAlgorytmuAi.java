package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel wyboru algorytmu Ai, wyswietlany w glownym oknie
 * 
 * @author Chydzio
 * 
 */
public class WyborAlgorytmuAi extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * TODO Wybor algorytmu dla JEDNEGO AI albo dla OBYDWU AI - zaleznie od
	 * wyboru trybu gry: gracz vs komputer ALBO komputer vs komputer. Aktualnie
	 * wybor tylko dla 1 AI.
	 * 
	 */
/*
	public WyborAlgorytmuAi() {
		// TODO Auto-generated constructor stub

		setLayout(new GridLayout(5, 3));

		add(tytul);
		add(new JLabel());

		// mozna wybrac tylko jedna opcje
		listaAlgorytmow.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(listaAlgorytmow);
		add(new JLabel());
		add(przyciskOk);

	}
*/
	/**
	 * Zamiast miliona ifow, mozna uzyc mapy - do rozwazenia.
	 * 
	 * @return Narazie int - do rozwazenia ENUM z nazwami algorytmow, albo cos
	 *         innego - zaleznie od sposobu dodawania algorytmow (refleksja lub
	 *         NIE).
	 * 
	 */
	/*
	int czytajAlgorytm() {

		// TODO Auto-generated method stub

		return listaAlgorytmow.getSelectedIndex();

	}
*/
	void wylacz() {

		setVisible(false);
	}
	
	void dodajListeneraPrzycisku (ActionListener l){
		
		przyciskOk.addActionListener(l);
	}

	//private JLabel tytul = new JLabel("Wybierz algorytm dla komputera");
	//private String[] algorytmyAi = { "jakis", "inny", "kolejny" };
	//private JList listaAlgorytmow = new JList(algorytmyAi);
	private JButton przyciskOk = new JButton("OK");

}
