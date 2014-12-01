package pliki;

import java.io.File;
import java.io.IOException;

public abstract class Plik {

	public Plik(String nazwa) {

		plik = new File(nazwa);
		
	}

	public boolean czyIstnieje() {

		if (plik != null)
			return plik.exists();
		else
			return false;
	}
	
	public boolean stworz() throws IOException{
		
		return plik.createNewFile();
		
	}

	protected File plik;

}
