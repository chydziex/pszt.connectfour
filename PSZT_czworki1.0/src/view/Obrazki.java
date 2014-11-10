package view;

public class Obrazki
{
	/** obrazek z putym polem musi miec taki sam rozmiar jak obrazki �eton�w.*/
	static final Ikona obrazekPustePole = new Ikona(new String("pustePole.bmp"));
	static final Ikona[] obrazkiZetonow = new Ikona[2];
	static final Ikona[] obrazkiStrzalek = new Ikona[2];
	
	static
	{
		/** Obrazki obu zetonow musz� mie� takie same rozmiary.*/
		obrazkiZetonow[0] = new Ikona(new String("zeton1.bmp"));
		obrazkiZetonow[1] = new Ikona(new String("zeton2.bmp"));
		
		/** Obrazki obu strza�ek musz� mie� takie same rozmiary. Musz� mie� takie same rozmiary jak obrazek z pustym polem*/
		obrazkiStrzalek[0] = new Ikona(new String("strzalka.bmp"));
		obrazkiStrzalek[1] = new Ikona(new String("strzalkaCzerwona.bmp"));
	}
}
