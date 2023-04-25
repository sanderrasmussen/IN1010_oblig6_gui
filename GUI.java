import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class GUI {
    //skal opprette et rutenett hvor hver knapp korresponderer til en rute i game of life
    // jeg maa lage en metode for aa hente alle kolonnene fra rutenettet slik at jeg kan tegne i gui
    JButton[][] ruter;
    Kontroll kontroll ;
    boolean spilletStartet = false; //har en status slik at gui lar bruker farge/sette celler til levende saa lenge spillet ikke er startet
    JLabel antallLevendeText;
    int antallLevendeKnapper; 
    //ActionListener fargKnappOgsettLevede = new fargKnappSettLevende(); //action listener saa jeg bare trenger et instans object for alle de flere titals knappene
    public GUI(Kontroll kontroll){
        this.kontroll = kontroll;

    }
    void tegnGui(int antRader, int antKolonner){

        
        try {UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());}
        catch(Exception e){ System.exit(1);}

        JFrame vindu = new JFrame("Game of life");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
        JPanel kontainer = new JPanel(new GridLayout(0,1));
        

        ruter = new JButton[antRader][antKolonner];
  
        //toppelement med stopp og start knapp
        JPanel oversiktKontainer = new JPanel(new GridLayout(3,2));
        oversiktKontainer.setBackground(Color.BLACK);
        kontainer.add(oversiktKontainer);

        //bare white space
        JLabel whitespace1 = new JLabel(" ");
        JLabel whitespace2 = new JLabel(" ");
        JLabel whitespace3 = new JLabel(" ");
        oversiktKontainer.add(whitespace1);
        oversiktKontainer.add(whitespace2);
        oversiktKontainer.add(whitespace3);
        
        antallLevendeKnapper = kontroll.hentAntLevende();
        antallLevendeText = new JLabel("Antall levende: " + Integer.toString(antallLevendeKnapper), SwingConstants.CENTER);
        antallLevendeText.setForeground(Color.WHITE);
        oversiktKontainer.add(antallLevendeText);

        JButton startKnapp = new JButton("Start");
        startKnapp.addActionListener(new startEvent(this, kontroll));
   
        startKnapp.setFocusPainted(false);
        startKnapp.setBackground(Color.WHITE);
        oversiktKontainer.add(startKnapp);

        JButton avsluttKnapp = new JButton("Avslutt");
        avsluttKnapp.addActionListener(new stoppEvent(this, kontroll));
        avsluttKnapp.setFocusPainted(false);
        avsluttKnapp.setBackground(Color.WHITE);
        oversiktKontainer.add(avsluttKnapp);

        //ogsaa bare white space
        JLabel whitespace4 = new JLabel(" ");
        JLabel whitespace5 = new JLabel(" ");
        JLabel whitespace6 = new JLabel(" ");
        oversiktKontainer.add(whitespace4);
        oversiktKontainer.add(whitespace5);
        oversiktKontainer.add(whitespace6);

        kontainer.add(oversiktKontainer);

        //selve rutenettet
        JPanel rutenettKontainer = new JPanel(new GridLayout(antRader,antKolonner));
        for (int i = 0; i < antRader; i++){
            // for alle -----
            //lag ||||
            for (int j = 0; j < antKolonner;j++){
                JButton rute = new JButton();
                ruter[i][j]= rute;
                //maa legge til action listener paa knappen ogsa
                rute.addActionListener(new fargKnappSettLevende(i,j, kontroll, this));
                rutenettKontainer.add(rute, i, j);
                rute.setBackground(Color.BLACK);
                rute.setFocusPainted(false);
            }
        }
        fargRuter(avsluttKnapp);
        kontainer.add(rutenettKontainer);
    
        vindu.add(kontainer);
        vindu.pack();
        vindu.setLocationRelativeTo(null);
        vindu.setVisible(true);
    }
    //farger ruten basert paa om levende eller doed
    public void fargRuter(JButton rute){
        Celle[][] spilleBrettArray = kontroll.hentRutenettMedCeller();
        for (int rad= 0; rad< spilleBrettArray.length;rad++ ){
            for (int kolonne = 0; kolonne < spilleBrettArray[rad].length;kolonne++){
                if (spilleBrettArray[rad][kolonne].erLevende()){
                    ruter[rad][kolonne].setBackground(Color.ORANGE);
                }
                else {
                    ruter[rad][kolonne].setBackground(Color.BLACK);
                }
            }
        }
        
    }
    public boolean spillStartet(){
        return spilletStartet;
    }
    public void oppdaterAntLevendeText(){
        //oppdaterer ogsaa text elementet som viser dette
        antallLevendeText.setText("Antall levende: "+Integer.toString(kontroll.hentAntLevende()));
    }
    public void oekAntLevendeText(){
        antallLevendeKnapper+=1;
        antallLevendeText.setText("Antall levende: "+Integer.toString(antallLevendeKnapper));
    }
    public void minkAntLevendeText(){
        antallLevendeKnapper-=1;
        antallLevendeText.setText("Antall levende: "+Integer.toString(antallLevendeKnapper));
    }
    public JButton[][] hentKnappeArray(){
        return ruter;
    }
    public void spilletStartet(){
        spilletStartet=true;
    }
   
}
