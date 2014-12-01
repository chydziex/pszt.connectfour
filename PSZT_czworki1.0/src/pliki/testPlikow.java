package pliki;

import java.io.FileNotFoundException;

public class testPlikow {

	public static void main(String[] args) {
		
		PlikWynikowy plikWynikowy;
		try {
			plikWynikowy = new PlikWynikowy("wynik.txt");
			plikWynikowy.piszNaglowek();
			plikWynikowy.piszNaglowek();
			plikWynikowy.piszNaglowek();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
