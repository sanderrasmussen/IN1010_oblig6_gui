import java.util.concurrent.TimeUnit;

import javax.swing.JButton;

class Kontroll {

    GUI gui;
    Verden spillebrett;
    int antallLevendeCeller;
    boolean stopp = false;
    public Kontroll(){
        gui = new GUI(this);
        spillebrett = new Verden(20, 20); 

        antallLevendeCeller= spillebrett.hentAntLevende();
        
    }
    
    public void tegnGui(){
        gui.tegnGui(20,20);
    }
    public Celle[][] hentRutenettMedCeller(){
        return spillebrett.hentRutenettArray();
    }
    public void oppdaterGuiOgSpillebrett(){
            
            try {
                //oppdaterer spillebrett
                Thread.sleep(1000); //venter totalt 2 sekunder, ett sekund etter gui er oppdatert og ett sekund for neste, paa denne maaten blir overgangen best iforhold til forste gang bruker trykker start knapp
                spillebrett.oppdatering();
                //oppdaterer gui
                JButton[][] knapper = gui.hentKnappeArray();
                for (int i = 0; i < knapper.length; i++){
                    for (int j = 0; j < knapper[i].length; j++){
                        gui.fargRuter(knapper[i][j]);
                    }
                }
                gui.oppdaterAntLevendeText();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }    
    }
    public void startSpill(){
            gui.spilletStartet();
            Thread traad = new Thread(new kjorSpillTraad(this, gui));
            traad.start();
          
        
       
    }
    public void stoppSpill(){
        stopp = true;
    }
    public boolean stoppTraad(){
        return stopp;
    }
    public int hentAntLevende(){
        antallLevendeCeller= spillebrett.hentAntLevende();
        return antallLevendeCeller;
    }
}
