
public class Celle {
	
	Celle[] naboer;
	int antNaboer;
	int antLevendeNaboer;
	public boolean levende;


	public Celle(){
		levende= false;
		naboer = new Celle[8];
		antNaboer=0;
		antLevendeNaboer=0;
		
	}
	
	public void settDoed() {
		levende=false;
	}
	public void settLevende() {
		levende=true;
	}
	
	
	public boolean erLevende() {
		if(levende==true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public char hentStatusTegn() {
		if(erLevende()) {
			return 'O'; //tegnet for levende celler som vises på brettet
		}
		else {
			return '.'; // tegnet for dode celler på brettet
		}
	}
	
	public void leggTilNabo(Celle nabo) {
		naboer[antNaboer] = nabo;
		antNaboer= antNaboer +1;
	}
	
	public void tellLevendeNaboer() {
		//her skal jeg loope igjennom arrayen og legge til 1 i antLevendeNaboer om cellen er levende
		antLevendeNaboer = 0;
		for (int teller = 0; teller< naboer.length; teller++) {
			if (naboer[teller]!=null) {
				if (naboer[teller].erLevende()) {
					antLevendeNaboer = antLevendeNaboer + 1;
				}
			}
			
		}
	}
	
	public void oppdaterStatus() {
	//bruker reglene til å bestemme om cellen skal være levende
		if (erLevende()==false) { //om doed
			if (antLevendeNaboer==3) {
				settLevende();
			}
		}
		if (erLevende()) {
			if (antLevendeNaboer<2 || antLevendeNaboer>3) {
				settDoed();
			}
		}
	}
}
