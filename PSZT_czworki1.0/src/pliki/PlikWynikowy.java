package pliki;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

public class PlikWynikowy extends Plik {

	public PlikWynikowy(String nazwa) throws IOException {

		super(nazwa);
	
	}

	
	public void piszNaglowek(Calendar dataPoczatku, String ai1, String ai2) throws IOException{
		
		if (licznikZapisow == 0)
			wyjscie = new PrintWriter(new BufferedWriter(new FileWriter(plik, true)), true);
		licznikZapisow++;
				
		piszDatePoczatku(dataPoczatku);
		piszKtoZKim(ai1, ai2);
		
	}
	public void piszDateKonca(Calendar dataKonca) {
	
		wyjscie.println(dataKonca.get(Calendar.DATE) + "." +dataKonca.get(Calendar.MONTH) + "." +dataKonca.get(Calendar.YEAR));
		wyjscie.println(dataKonca.getTime().getTime() - dataPoczatku.getTime().getTime());
		
		wyjscie.close();
			

	}
	public void zamknij(){
		
		wyjscie.close();
	}

	private void piszDatePoczatku(Calendar poczatek) {
			
		dataPoczatku = poczatek;
		wyjscie.println(poczatek.get(Calendar.DATE) + "." +poczatek.get(Calendar.MONTH) + "."  +poczatek.get(Calendar.YEAR));
			

	}
	
	private void piszKtoZKim(String ai1, String ai2) {
		
		wyjscie.println(ai1 + "\t" + ai2);
		

	}


	public void piszWynikGry(String ai1, String ai2, int liczbaRuchow, String rezultat) throws IOException {

		if (licznikZapisow == 0)
			wyjscie = new PrintWriter(new BufferedWriter(new FileWriter(plik, true)), true);
		licznikZapisow++;

		wyjscie.println(ai1 + "\t" + ai2 + "\t" + liczbaRuchow + "\t"+ rezultat + "\t" + licznikZapisow);

		licznikZapisow %= 5;
		if (licznikZapisow == 0) {

			wyjscie.close();
			wyjscie = null;

		}

	}

	private PrintWriter wyjscie;
	private int licznikZapisow = 0;
	private Calendar dataPoczatku;

}
