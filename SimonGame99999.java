import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class SimonGame extends JFrame implements ActionListener {

    // Buttons
    JButton rot;
    JButton gruen;
    JButton blau;
    JButton gelb;
    JButton start;

    // Labels
    JLabel text;
    JLabel highscoreText;

    // Listen
    ArrayList<Integer> computer = new ArrayList<Integer>();
    ArrayList<Integer> spieler = new ArrayList<Integer>();

    Random zufall = new Random();

    int highscore = 0;
    boolean spielerIstDran = false;

    public SimonGame() {

        setTitle("Simon Spiel");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Oben
        JPanel oben = new JPanel();
        oben.setLayout(new GridLayout(2,1));

        text = new JLabel("Drücke Start", SwingConstants.CENTER);
        highscoreText = new JLabel("Highscore: 0", SwingConstants.CENTER);

        oben.add(text);
        oben.add(highscoreText);

        add(oben, BorderLayout.NORTH);

        // Mitte
        JPanel mitte = new JPanel();
        mitte.setLayout(new GridLayout(2,2));

        rot = new JButton();
        rot.setBackground(Color.RED);

        gruen = new JButton();
        gruen.setBackground(Color.GREEN);

        blau = new JButton();
        blau.setBackground(Color.BLUE);

        gelb = new JButton();
        gelb.setBackground(Color.YELLOW);

        rot.addActionListener(this);
        gruen.addActionListener(this);
        blau.addActionListener(this);
        gelb.addActionListener(this);

        mitte.add(rot);
        mitte.add(gruen);
        mitte.add(blau);
        mitte.add(gelb);

        add(mitte, BorderLayout.CENTER);

        // Unten
        start = new JButton("Start");
        start.addActionListener(this);

        add(start, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Neue Runde
    public void neueRunde() {

        spieler.clear();

        int zahl = zufall.nextInt(4);
        computer.add(zahl);

        text.setText("Merke dir die Farben");

        zeigeFarben();
    }

    // Farben zeigen
    public void zeigeFarben() {

        spielerIstDran = false;

        try {

            for(int i = 0; i < computer.size(); i++) {

                int farbe = computer.get(i);

                if(farbe == 0) {
                    rot.setBackground(Color.WHITE);
                }

                if(farbe == 1) {
                    gruen.setBackground(Color.WHITE);
                }

                if(farbe == 2) {
                    blau.setBackground(Color.WHITE);
                }

                if(farbe == 3) {
                    gelb.setBackground(Color.WHITE);
                }

                Thread.sleep(500);

                rot.setBackground(Color.RED);
                gruen.setBackground(Color.GREEN);
                blau.setBackground(Color.BLUE);
                gelb.setBackground(Color.YELLOW);

                Thread.sleep(300);
            }

        } catch(Exception e) {

        }

        text.setText("Du bist dran");
        spielerIstDran = true;
    }

    // Klicks
    public void actionPerformed(ActionEvent e) {

        // Startbutton
        if(e.getSource() == start) {

            computer.clear();
            spieler.clear();

            neueRunde();
        }

        // Nur wenn Spieler dran ist
        if(spielerIstDran == true) {

            int zahl = -1;

            if(e.getSource() == rot) {
                zahl = 0;
            }

            if(e.getSource() == gruen) {
                zahl = 1;
            }

            if(e.getSource() == blau) {
                zahl = 2;
            }

            if(e.getSource() == gelb) {
                zahl = 3;
            }

            if(zahl != -1) {

                spieler.add(zahl);

                int stelle = spieler.size() - 1;

                // Fehler?
                if(spieler.get(stelle) != computer.get(stelle)) {

                    spielerIstDran = false;

                    JOptionPane.showMessageDialog(null,
                            "Game Over!");

                    // Highscore
                    if(computer.size() > highscore) {

                        highscore = computer.size();

                        highscoreText.setText("Highscore: " + highscore);
                    }

                    text.setText("Verloren");
                }

                // Runde geschafft?
                else if(spieler.size() == computer.size()) {

                    text.setText("Richtig!");

                    neueRunde();
                }
            }
        }
    }

    public static void main(String[] args) {

        new SimonGame();
    }
}