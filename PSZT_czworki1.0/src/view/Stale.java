package view;
import java.awt.Dimension;
import java.awt.Rectangle;

import model.Model;

public class Stale
{
	static final Rectangle plansza;
	static final Rectangle interfejsGracza;
	static final Rectangle panelZZetonemWInterfejsieGracza;
	static final Rectangle panelZeStrzalkaWInterfejsieGracza;
	static final Dimension okno;
	private static final int przesuniecieWPionieZetonuInterfejsu;
	private final static int przesunieciePoziom = 16;
	private final static int przesunieciePion = 38;
	
	static
	{
		przesuniecieWPionieZetonuInterfejsu = 10;
		interfejsGracza = new Rectangle(0, 0, Model.iloscKolumn*Obrazki.obrazekPustePole.getIconWidth(), (Model.iloscWierszy + 2)*Obrazki.obrazekPustePole.getIconHeight() + przesuniecieWPionieZetonuInterfejsu);
		panelZZetonemWInterfejsieGracza = new Rectangle(0, przesuniecieWPionieZetonuInterfejsu, interfejsGracza.width, Obrazki.obrazkiZetonow[0].getIconHeight());
		panelZeStrzalkaWInterfejsieGracza = new Rectangle(0, panelZZetonemWInterfejsieGracza.y + panelZZetonemWInterfejsieGracza.height, interfejsGracza.width, Obrazki.obrazkiStrzalek[0].getIconHeight());
		plansza = new Rectangle(0, panelZeStrzalkaWInterfejsieGracza.y + panelZeStrzalkaWInterfejsieGracza.height, Model.iloscKolumn*Obrazki.obrazekPustePole.getIconWidth(), Model.iloscWierszy*Obrazki.obrazekPustePole.getIconHeight());
		okno = new Dimension(interfejsGracza.width + przesunieciePoziom, interfejsGracza.height + przesunieciePion);
	}
}
