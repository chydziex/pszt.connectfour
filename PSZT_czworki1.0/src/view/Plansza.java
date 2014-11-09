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
		return new Rectangle(wspolrzedne.getKolumna()*obrazekPustePole.getIconWidth(), wspolrzedne.getWiersz()*obrazekPustePole.getIconHeight(), obrazekPustePole.getIconWidth(), obrazekPustePole.getIconHeight());
	}
	
	/** Metoda zwracaj�ca dok�adne po�o�enie komponentu w zale�no�ci od wiersza i kolumny, w kt�rych ma si� znale��.*/
	private Rectangle polozenie(int wiersz, int kolumna)
	{
		return new Rectangle(kolumna*obrazekPustePole.getIconWidth(), wiersz*obrazekPustePole.getIconHeight(), obrazekPustePole.getIconWidth(), obrazekPustePole.getIconHeight());
	}
	
	/** Fabryka pustych p�l.*/
	private JLabel stworzPustePole()
	{
		return new JLabel(obrazekPustePole);
	}
	
	/** Fabryka �eton�w. Rodzaj �etonu zale�y od kolejno�ci gracza.*/
	private JLabel stworzZeton(int ktoryGracz)
	{
		return new JLabel(obrazkiZetonow[ktoryGracz]);
	}
	
	/** Metoda �aduj�ca pliki z obrazkami �eton�w.*/
	private void wgrajObrazkiZetonow()
	{
		obrazkiZetonow[0] = new Ikona(new String("zeton1.bmp"));
		obrazkiZetonow[1] = new Ikona(new String("zeton2.bmp"));
	}
	
	static final Ikona obrazekPustePole = new Ikona(new String("pustePole.bmp"));
	static final Ikona[] obrazkiZetonow = new Ikona[2];
}
