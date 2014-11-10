package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Wspolrzedne;

class Plansza extends JPanel
{
	public Plansza(int iloscWierszy, int iloscKolumn)
	{
		// TODO
		setLayout(null);
		setBackground(Color.white);
		setBounds(Stale.plansza);
		//setOpaque(false);
		stworzPustaPlansze(iloscWierszy, iloscKolumn);

	}
	
	/** Metoda dodaj�ca do panelu �eton rodzaju zale�nym od ktoryGracz.*/
	public void dodajZeton(Wspolrzedne wspolrzedne, int ktoryGracz)
	{
		JLabel label = stworzZeton(ktoryGracz);
		label.setBounds(polozenie(wspolrzedne));
		add(label);
	}
	
	/** Metoda wype�niaj�ca panel Plansza pustymi polami.*/
	private void stworzPustaPlansze(final int iloscWierszy, final int iloscKolumn)
	{
		// TODO
		JLabel label;
		for(int w = 0; w < iloscWierszy; w++)
			for(int h = 0; h < iloscKolumn; h++)
			{
				label = stworzPustePole();
				add(label);
				label.setBounds(polozenie(w, h));
			}
	}
	
	/** Metoda zwracaj�ca dok�adne po�o�enie komponentu w zale�no�ci od wiersza i kolumny, w kt�rych ma si� znale��.*/
	private Rectangle polozenie(Wspolrzedne wspolrzedne)
	{
		return new Rectangle(wspolrzedne.getKolumna()*Obrazki.obrazekPustePole.getIconWidth(), wspolrzedne.getWiersz()*Obrazki.obrazekPustePole.getIconHeight(), Obrazki.obrazekPustePole.getIconWidth(), Obrazki.obrazekPustePole.getIconHeight());
	}
	
	/** Metoda zwracaj�ca dok�adne po�o�enie komponentu w zale�no�ci od wiersza i kolumny, w kt�rych ma si� znale��.*/
	private Rectangle polozenie(int wiersz, int kolumna)
	{
		return new Rectangle(kolumna*Obrazki.obrazekPustePole.getIconWidth(), wiersz*Obrazki.obrazekPustePole.getIconHeight(), Obrazki.obrazekPustePole.getIconWidth(), Obrazki.obrazekPustePole.getIconHeight());
	}
	
	/** Fabryka pustych p�l.*/
	private JLabel stworzPustePole()
	{
		return new JLabel(Obrazki.obrazekPustePole);
	}
	
	/** Fabryka �eton�w. Rodzaj �etonu zale�y od kolejno�ci gracza.*/
	private JLabel stworzZeton(int ktoryGracz)
	{
		return new JLabel(Obrazki.obrazkiZetonow[ktoryGracz]);
	}
}
