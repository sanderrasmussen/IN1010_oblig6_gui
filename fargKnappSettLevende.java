import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class fargKnappSettLevende implements ActionListener {
    int rad;
    int kolonne;
    Kontroll kontroll;
    GUI gui;
    public fargKnappSettLevende(int rad, int kolonne, Kontroll kontroll, GUI gui){
        this.rad = rad;
        this.kolonne = kolonne;
        this.kontroll = kontroll;
        this.gui = gui;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       
        //finn knapp basert paa rad kolonne og sett celle til levende
        if (!gui.spilletStartet){
            if (kontroll.hentRutenettMedCeller()[rad][kolonne].erLevende()){
                kontroll.hentRutenettMedCeller()[rad][kolonne].settDoed();
                ((JButton)e.getSource()).setBackground(Color.BLACK);
                gui.minkAntLevendeText();
            }
            else {
                kontroll.hentRutenettMedCeller()[rad][kolonne].settLevende(); //setter celle til levende ved trykk dersom spillet ikke er startet
                ((JButton)e.getSource()).setBackground(Color.ORANGE);
                gui.oekAntLevendeText();
            }
            
        }
        
    }
    
}
