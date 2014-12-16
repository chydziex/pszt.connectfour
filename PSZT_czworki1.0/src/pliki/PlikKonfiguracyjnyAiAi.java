package pliki;

import sytuacjeWyjatkowe.WyjatekZlyPlikKonfiguracyjny;

public class PlikKonfiguracyjnyAiAi extends PlikKonfiguracyjnyAiCzlowiek {

	public PlikKonfiguracyjnyAiAi(String nazwa) {
		super(nazwa);
		// TODO Auto-generated constructor stub
	}
	
	public DaneAi [] czytajDaneAi() throws WyjatekZlyPlikKonfiguracyjny{
		
		DaneAi[] dane = new DaneAi[2];
		dane[0] = parsujLinie(wejscie.nextLine());
		dane[1] = parsujLinie(wejscie.nextLine());
			
			return dane;
				
	}
	

}
