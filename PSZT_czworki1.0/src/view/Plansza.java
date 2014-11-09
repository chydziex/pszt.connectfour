package view;

import java.awt.Color;
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
		wgrajObrazkiZetonow();
		setBounds(new Rectangle(0, 0, iloscKolumn*obrazekPustePole.getIconWidth(), iloscWierszy*obrazekPustePole.getIconHeight()));
		stworzPustaPlansze(iloscWierszy, iloscKolumn);

	}
	
	/** Metoda dodaj¹ca do panelu ¿eton rodzaju zale¿nym od ktoryGracz.*/
	public void dodajZeton(Wspolrzedne wspolrzedne, int ktoryGracz)
	{
		JLabel label = stworzZeton(ktoryGracz);
		label.setBounds(polozenie(wspolrzedne));
		add(label);
	}
	
	/** Metoda wype³niaj¹ca panel Plansza pustymi polami.*/
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
	
	/** Metoda zwracaj¹ca dok³adne po³o¿enie komponentu w zale¿noœci od wiersza i kolumny, w których ma siê znale¿æ.*/
	private Rectangle polozenie(Wspolrzedne wspolrzedne)
	{
		return new Rectangle(wspolrzedne.getKolumna()*obrazekPustePole.getIconWidth(), wspolrzedne.getWiersz()*obrazekPustePole.getIconHeight(), obrazekPustePole.getIconWidth(), obrazekPustePole.getIconHeight());
	}
	
	/** Metoda zwracaj¹ca dok³adne po³o¿enie komponentu w zale¿noœci od wiersza i kolumny, w których ma siê znale¿æ.*/
	private Rectangle polozenie(int wiersz, int kolumna)
	{
		return new Rectangle(kolumna*obrazekPustePole.getIconWidth(), wiersz*obrazekPustePole.getIconHeight(), obrazekPustePole.getIconWidth(), obrazekPustePole.getIconHeight());
	}
	
	/** Fabryka pustych pól.*/
	private JLabel stworzPustePole()
	{
		return new JLabel(obrazekPustePole);
	}
	
	/** Fabryka ¿etonów. Rodzaj ¿etonu zale¿y od kolejnoœci gracza.*/
	private JLabel stworzZeton(int ktoryGracz)
	{
		return new JLabel(obrazkiZetonow[ktoryGracz]);
	}
	
	/** Metoda ³aduj¹ca pliki z obrazkami ¿etonów.*/
	private void wgrajObrazkiZetonow()
	{
		obrazkiZetonow[0] = new Ikona(new String("zeton1.bmp"));
		obrazkiZetonow[1] = new Ikona(new String("zeton2.bmp"));
	}
	
	static final Ikona obrazekPustePole = new Ikona(new String("pustePole.bmp"));
	static final Ikona[] obrazkiZetonow = new Ikona[2];
}
