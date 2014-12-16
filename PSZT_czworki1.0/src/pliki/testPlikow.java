package pliki;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import sytuacjeWyjatkowe.WyjatekZlyPlikKonfiguracyjny;

public class testPlikow {

	public static void main(String[] args) {
		
		PlikKonfiguracyjnyAiAi konf = new PlikKonfiguracyjnyAiAi("konfiguracja komputer komputer.txt");
		try {
			
			if(konf.czyIstnieje())
				System.out.println("istnieje");
			konf.otworz();
			DaneAi dane [];
			dane = konf.czytajDaneAi();
			System.out.println(dane[0].toString());
			System.out.println(dane[1].toString());
			konf.zamknij();
			
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (WyjatekZlyPlikKonfiguracyjny e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
				
				
		

	}


}
