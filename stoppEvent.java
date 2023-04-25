import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class stoppEvent implements ActionListener{
    GUI gui;    
    Kontroll kontroll;

    public stoppEvent(GUI gui, Kontroll kontroll){
        this.gui = gui;
        this.kontroll= kontroll;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       kontroll.stoppSpill();
       System.exit(0);// avslutter programmet dersom brukeren trykker paa avslutt knappen
    }
    
    
}
