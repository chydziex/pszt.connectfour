package view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import model.Tryby;

/**
 * Panel wyboru trybu gry (komputer vs komputer, gracz vs gracz, gracz vs
 * komputer) wyswietlany w glownym oknie gry.
 * 
 * @author Chydzio
 * 
 */
public class WyborTrybuGry extends JPanel {

	WyborTrybuGry() {

		setLayout(new GridLayout(5, 3));
		add(tytul);
		add(new JLabel());
		
		//mozna wybrac tylko jedna opcje
		listaTrybow.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(listaTrybow);
		add(new JLabel());
		add(przyciskOk);

		setSize(350, 300);
	}

	void wylacz() {

		setVisible(false);
	}
	
	void dodajListeneraPrzycisku (ActionListener l){
		
		przyciskOk.addActionListener(l);
	}

	/**
	 * Mozna zrobic bardziej elegancko: wrzucic do mapy pary
	 * (index, WiadomoscTryb) i pobierac z mapy odpowiednio, zamiast IFow.
	 * 
	 * @return
	 */
	Tryby czytajTrybGry() {

		if (listaTrybow.getSelectedIndex() == 0)
			return Tryby.CZLOWIEKvsCZLOWIEK;
		else if (listaTrybow.getSelectedIndex() == 1)
			return Tryby.AIvsCZLOWIEK;
		else if (listaTrybow.getSelectedIndex() == 2)
			return Tryby.AIvsAI;

		return null;

	}

	private JLabel tytul = new JLabel("Wybierz tryb gry");
	private String[] nazwyTrybow = { "Gracz vs Gracz", "Gracz vs Komputer",	"Komputer vs Komputer" };
	private JList listaTrybow = new JList(nazwyTrybow);
	private JButton przyciskOk = new JButton("Ok");

}
