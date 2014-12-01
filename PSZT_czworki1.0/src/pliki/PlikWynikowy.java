package pliki;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

public class PlikWynikowy extends Plik {
	
	public PlikWynikowy(String nazwa) throws FileNotFoundException{
		
		super(nazwa);
		//wyjscie = new PrintWriter(plik);
		
	}
	
	public void piszNaglowek(Date poczatek, Date koniec, String ai1, String ai2){
			
		
		wyjscie.println("test");
		wyjscie.close();
		
	}
	
	public void piszNaglowek() throws FileNotFoundException{
			
		wyjscie = new PrintWriter(plik);
		wyjscie.println("test");
		wyjscie.close();
		wyjscie = null;
		
	}
	
	
	PrintWriter wyjscie;

}
