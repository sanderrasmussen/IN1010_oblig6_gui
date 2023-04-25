public class kjorSpillTraad implements Runnable{
    Kontroll kontroll;
    GUI gui;

    public kjorSpillTraad(Kontroll kontroll, GUI gui){
        this.kontroll = kontroll;
        this.gui = gui;
    }
    @Override
    public void run() { //kjorer spillets oppdateringer i traad slik at hovedprogrammet kan fortsette uten aa matte vente
        while (!kontroll.stoppTraad()){
            kontroll.oppdaterGuiOgSpillebrett();
            
        }

    }
    
}
