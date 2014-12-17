package pliki;

import java.io.FileNotFoundException;
import java.io.StringBufferInputStream;
import java.util.LinkedList;
import java.util.Scanner;

import sytuacjeWyjatkowe.WyjatekZlyPlikKonfiguracyjny;

public class PlikKonfiguracyjnyAiCzlowiek extends Plik {

	public PlikKonfiguracyjnyAiCzlowiek(String nazwa) {
		super(nazwa);
	
	}

	public void otworz() throws FileNotFoundException {

		wejscie = new Scanner(plik);
	}
	
	public void zamknij(){
		
		wejscie.close();
	}

	public DaneAi [] czytajDaneAi() throws WyjatekZlyPlikKonfiguracyjny{

		DaneAi[] dane = new DaneAi[1];
		dane[0] = parsujLinie(wejscie.nextLine());
		return dane;
	}

	protected DaneAi parsujLinie(String linia) throws WyjatekZlyPlikKonfiguracyjny{
		
		Scanner scn = new Scanner(linia);
		int glebokoscDrzewa, kolejnosc;
		LinkedList <Integer> wagiHeurystyk = new LinkedList<Integer>();

		if (scn.hasNextInt())
			kolejnosc = scn.nextInt();
		else
			throw new WyjatekZlyPlikKonfiguracyjny();
		
		if (scn.hasNextInt())
			glebokoscDrzewa = scn.nextInt();
		else
			throw new WyjatekZlyPlikKonfiguracyjny();

		while (scn.hasNextInt()) {
			wagiHeurystyk.add(new Integer(scn.nextInt()));

		}
		
		return new DaneAi( wagiHeurystyk.toArray(new Integer [0]), glebokoscDrzewa, kolejnosc );
		
	}

	protected Scanner wejscie;

}
