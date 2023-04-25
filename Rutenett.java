import java.util.Arrays;


public class Rutenett {
	
	int antRader;
	int antKolonner;
	Celle[][] rutene;
	
	public Rutenett(int antallRader, int antallKolonner) {
		antRader = antallRader;
		antKolonner = antallKolonner;
		rutene = new Celle[antRader][antKolonner];
		
	}
	//EN NY METODE FOR OBLIG 6
	public Celle[][] hentRutenettTilGui(){
		return rutene;
	}
	
	public void lagCelle() {
		
		//for å legge inn celle på neste ledige rute, sjekker jeg hvilke ruter som == null og legger cellen inn på første rute som == null
		
		for (int radNr = 0; radNr< (antRader); radNr++) {
			for (int kollonneNr = 0; kollonneNr < (antKolonner); kollonneNr++) {
				if (rutene[radNr][kollonneNr]==null) {
					Celle nyCelle = new Celle();
					if (Math.random()<=0.3333) {
						nyCelle.settLevende();
					}
					rutene[radNr][kollonneNr]= nyCelle;
					break; //stopper loopen om vi finner en ledig array plass til cellen
				}
			}
		}
	}
	
	public void fyllMedTilfeldigeCeller() {
	
		int antallOnsketCeller = antRader * antKolonner;
		
		for (int antallCeller = 0; antallCeller< antallOnsketCeller; antallCeller++) {
			lagCelle();
		}
	}
	
	public Celle hentCelle(int rad, int kolonne) {
		if (rad < antRader && rad >=0 && kolonne < antKolonner && kolonne>=0) {
			return rutene[rad][kolonne];
		}
		else {
			return null;
		}
	}
	
	public void tegnRutenett() {
		//skriver ut tomme linjer i terminal for å "oppdatere rutenettet" 
		for (int i = 0; i < 10;i++) {
			System.out.println(" ");
		}
		

		for (int radNr = 0; radNr< (antRader); radNr++) {
			//en rad er en linje som skal printes ut sammen
			String radUtskrift = "|";
			for (int kollonneNr = 0; kollonneNr < (antKolonner); kollonneNr++) {
				Celle denneCellen = hentCelle(radNr, kollonneNr);
				if (denneCellen.erLevende()) { //om cellen er levende print dette
					radUtskrift+="O|";
				}
				else { // om cellen er doed print dette
					radUtskrift+=" |";
				}			
			}
			//etter alle kollonner i en rad er sjekket tegner jeg hele raden
			System.out.println(radUtskrift);
		}
	}
	public void settNaboer(int rad, int kolonne) {
		//henter først cellen og finner deretter naboene 
		
		Celle denneCellen = hentCelle(rad, kolonne);

		//gaar igjennom denne cellens rad forhje rad og neste rad samt kolonne-1 +1 og samme kolonnes
		for (int rd = (rad-1); rd <= (rad+1); rd++){ //gaar igjennom alle tre rader hvor naboene ligger i 
			//deretter gaar jeg igennom alle 3 kolonnene i hver rad som potensielt kan være naboer om ikke utenfor brettet
			
			for (int kl = (kolonne-1); kl<=(kolonne+1); kl++) {
				Celle nabo = hentCelle(rd, kl);
				
				if (nabo!=null && (nabo.equals(denneCellen)==false)) {
					denneCellen.leggTilNabo(nabo);
				}
			}
		}
		//System.out.println(Arrays.toString(denneCellen.naboer)); //brukt for å teste at de objecter blir lagt til som nabo og ikke null
	}
	
	public void kobleAlleCeller() {
		for (int rad = 0 ; rad <antRader; rad++) {
			for (int kolonne = 0; kolonne <antKolonner; kolonne++) {
				settNaboer(rad, kolonne);
			}
		}
	}
	
	
	public int antallLevende() {
		int levendeTeller = 0;
		
		for (int rad = 0; rad < antRader; rad++) {
			for (int kolonne = 0; kolonne < antKolonner; kolonne++) {
				if (hentCelle(rad,kolonne).erLevende()) {
					levendeTeller = levendeTeller +1;
				}
			}
		}
		return levendeTeller;
	}
	

}
