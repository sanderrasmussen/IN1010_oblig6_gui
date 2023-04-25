
public class Verden {

	Rutenett rutenett;
	
	int antallRader;
	int antallKolonner;
	int genNr;
	int antLevende; 
	Celle[][] rutenettVerdier;
	
	public Verden(int antRader, int antKolonner) {

		rutenett = new Rutenett(antRader, antKolonner);
		
		antallRader = antRader;
		antallKolonner = antKolonner;
		genNr = 0;
		rutenett.fyllMedTilfeldigeCeller();
		rutenett.kobleAlleCeller();
		antLevende = rutenett.antallLevende();
		rutenettVerdier= rutenett.hentRutenettTilGui(); //dette er for aa kunne tenge i gui
		
	}
	public int hentAntLevende(){
		return antLevende;
	}
	public Celle[][] hentRutenettArray(){
		return rutenettVerdier;
	}
	
	public void tegn() {
		rutenett.tegnRutenett();
		System.out.println("generasjon: " + Integer.toString(genNr)+ " - Antall levende celler: " + Integer.toString(antLevende));
		
	}
	
	public void oppdatering() {
		// går igjennom alle celler og setter doed eller levende basert på spillereglene , opdaterer også generasjonsnummer og telelr antall levende
		
		//oppdaterer alle naboenes status forst
		for (int rad = 0; rad < antallRader; rad++) {
			for (int kolonne = 0; kolonne < antallKolonner; kolonne++) {
				Celle denneCellen = rutenett.hentCelle(rad, kolonne);
				denneCellen.tellLevendeNaboer(); //teller naboer og instansvariablen til cellen oppdateres
			}
		}
		
		//nå skal jeg bestemme om cellen skal være doed eller levende basert på spillets regler
		//looper igjennom paa nytt og endrer status
		for (int rad = 0; rad < antallRader; rad++) {
			for (int kolonne = 0; kolonne < antallKolonner; kolonne++) {
				Celle denneCellen = rutenett.hentCelle(rad, kolonne);
				//oppdaterer statusen til cellen basert paa betingelsene
				denneCellen.oppdaterStatus();
			}
		}
		genNr+=1;
		antLevende = rutenett.antallLevende();
		//tegn(); //gaar utifra at brettet skal tegnes paa nytt også i oppdaterings metoden
	}

}
