import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startEvent implements ActionListener{
    GUI gui;
    Kontroll kontroll;

    public startEvent(GUI gui, Kontroll kontroll){
        this.gui = gui;
        this.kontroll = kontroll;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        kontroll.startSpill(); //starter spillet naar bruker trykker start knapp
    }
    
}
