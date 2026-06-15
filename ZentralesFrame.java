import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ZentralesFrame extends Frame implements ActionListener {

  Button rot = new Button("");
  Button blau = new Button("");
  Button gruen = new Button("");
  Button gelb = new Button("");
  Button start = new Button("Start");

  TextField nameFeld = new TextField();

  Label text = new Label("Name eingeben und Start druecken");
  Label punkte = new Label("Punkte: 0");
  Label highscoreText = new Label("Highscore: -");
  Label rundeText = new Label("Runde: 0");

  ArrayList<Integer> folge = new ArrayList<Integer>();

  // NEU: Namen + Scores
  ArrayList<String> namen = new ArrayList<String>();
  ArrayList<Integer> scores = new ArrayList<Integer>();

  String aktuellerName = "";

  int spielerPos = 0;
  boolean spielLaeuft = false;

  int runde = 0;

  public ZentralesFrame() {

    setLayout(null);
    setSize(600, 600);
    setTitle("Farben merken mit Namen");

    nameFeld.setBounds(200, 20, 200, 30);

    start.setBounds(420, 20, 100, 30);

    rot.setBounds(100, 120, 150, 150);
    blau.setBounds(300, 120, 150, 150);
    gruen.setBounds(100, 290, 150, 150);
    gelb.setBounds(300, 290, 150, 150);

    rot.setBackground(Color.red);
    blau.setBackground(Color.blue);
    gruen.setBackground(Color.green);
    gelb.setBackground(Color.yellow);

    rundeText.setBounds(20, 80, 150, 20);
    punkte.setBounds(20, 100, 150, 20);
    highscoreText.setBounds(20, 120, 200, 20);

    text.setBounds(150, 500, 400, 30);

    add(nameFeld);
    add(start);

    add(rot);
    add(blau);
    add(gruen);
    add(gelb);

    add(rundeText);
    add(punkte);
    add(highscoreText);
    add(text);

    start.addActionListener(this);
    rot.addActionListener(this);
    blau.addActionListener(this);
    gruen.addActionListener(this);
    gelb.addActionListener(this);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        dispose();
      }
    });

    setVisible(true);
  }

  public void pause(int zeit) {
    try {
      Thread.sleep(zeit);
    }
    catch (Exception e) {
    }
  }

  public void neueRunde() {

    runde++;
    rundeText.setText("Runde: " + runde);

    int zufall = (int)(Math.random() * 4);
    folge.add(zufall);

    text.setText("Merke dir die Farben");

    anzeigen();

    spielerPos = 0;
    spielLaeuft = true;
  }

  public void anzeigen() {

    for (int i = 0; i < folge.size(); i++) {

      int farbe = folge.get(i);

      if (farbe == 0) {
        rot.setBackground(Color.white);
        pause(400);
        rot.setBackground(Color.red);
      }

      if (farbe == 1) {
        blau.setBackground(Color.white);
        pause(400);
        blau.setBackground(Color.blue);
      }

      if (farbe == 2) {
        gruen.setBackground(Color.white);
        pause(400);
        gruen.setBackground(Color.green);
      }

      if (farbe == 3) {
        gelb.setBackground(Color.white);
        pause(400);
        gelb.setBackground(Color.yellow);
      }

      pause(300);
    }

    text.setText("Jetzt bist du dran");
  }

  public void pruefen(int farbe) {

    if (!spielLaeuft) return;

    if (farbe == folge.get(spielerPos)) {

      spielerPos++;

      text.setText("Richtig! " + spielerPos + "/" + folge.size());

      if (spielerPos == folge.size()) {

        punkte.setText("Punkte: " + folge.size());

        pause(800);

        neueRunde();
      }

    } else {

      int punktzahl = folge.size() - 1;

      text.setText("Falsch! Punkte: " + punktzahl);

      spielLaeuft = false;

      // NEU: Name speichern
      aktuellerName = nameFeld.getText();

      if (!aktuellerName.equals("")) {

        namen.add(aktuellerName);
        scores.add(punktzahl);

        // besten Score finden
        int bester = 0;

        for (int i = 0; i < scores.size(); i++) {
          if (scores.get(i) > bester) {
            bester = scores.get(i);
          }
        }

        highscoreText.setText("Highscore: " + bester + " (" + namen.get(scores.indexOf(bester)) + ")");
      }
    }
  }

  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == start) {

      folge.clear();
      runde = 0;
      spielerPos = 0;

      neueRunde();
    }

    if (e.getSource() == rot) pruefen(0);
    if (e.getSource() == blau) pruefen(1);
    if (e.getSource() == gruen) pruefen(2);
    if (e.getSource() == gelb) pruefen(3);
  }

  public static void main(String[] args) {
    new ZentralesFrame();
  }
}