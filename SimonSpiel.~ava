import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimonSpiel extends JFrame {

    private final JButton rotButton;
    private final JButton blauButton;
    private final JButton gruenButton;
    private final JButton gelbButton;
    private final JLabel statusLabel;

    private final String[] farben = {"Rot", "Blau", "Grün", "Gelb"};
    private final List<String> sequenz = new ArrayList<>();
    private final List<String> eingabe = new ArrayList<>();

    private final Random random = new Random();

    public SimonSpiel() {
        setTitle("Simon Says");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        statusLabel = new JLabel("Spiel startet...", SwingConstants.CENTER);
        add(statusLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(2, 2));

        rotButton = new JButton("Rot");
        rotButton.setBackground(Color.RED);

        blauButton = new JButton("Blau");
        blauButton.setBackground(Color.BLUE);
        blauButton.setForeground(Color.WHITE);

        gruenButton = new JButton("Grün");
        gruenButton.setBackground(Color.GREEN);

        gelbButton = new JButton("Gelb");
        gelbButton.setBackground(Color.YELLOW);

        panel.add(rotButton);
        panel.add(blauButton);
        panel.add(gruenButton);
        panel.add(gelbButton);

        add(panel, BorderLayout.CENTER);

        rotButton.addActionListener(e -> farbeGedrueckt("Rot"));
        blauButton.addActionListener(e -> farbeGedrueckt("Blau"));
        gruenButton.addActionListener(e -> farbeGedrueckt("Grün"));
        gelbButton.addActionListener(e -> farbeGedrueckt("Gelb"));

        setButtonsAktiv(false);

        setVisible(true);

        neueRunde();
    }

    private void neueRunde() {
        sequenz.add(farben[random.nextInt(farben.length)]);
        eingabe.clear();

        statusLabel.setText("Merke dir die Sequenz...");
        setButtonsAktiv(false);

        zeigeSequenz(0);
    }

    private void zeigeSequenz(int index) {
        if (index >= sequenz.size()) {
            statusLabel.setText("Jetzt bist du dran!");
            setButtonsAktiv(true);
            return;
        }

        JButton button = getButton(sequenz.get(index));
        Color original = button.getBackground();

        button.setBackground(Color.WHITE);

        Timer timer1 = new Timer(600, e -> {
            button.setBackground(original);

            Timer timer2 = new Timer(300, ev -> zeigeSequenz(index + 1));
            timer2.setRepeats(false);
            timer2.start();
        });

        timer1.setRepeats(false);
        timer1.start();
    }

    private void farbeGedrueckt(String farbe) {
        eingabe.add(farbe);

        int position = eingabe.size() - 1;

        if (!eingabe.get(position).equals(sequenz.get(position))) {
            JOptionPane.showMessageDialog(
                    this,
                    "Falsche Eingabe!\nErreichte Runde: " + (sequenz.size() - 1),
                    "Game Over",
                    JOptionPane.ERROR_MESSAGE
            );

            sequenz.clear();
            neueRunde();
            return;
        }

        if (eingabe.size() == sequenz.size()) {
            statusLabel.setText("Richtig!");
            Timer timer = new Timer(1000, e -> neueRunde());
            timer.setRepeats(false);
            timer.start();
        }
    }

    private JButton getButton(String farbe) {
        return switch (farbe) {
            case "Rot" -> rotButton;
            case "Blau" -> blauButton;
            case "Grün" -> gruenButton;
            case "Gelb" -> gelbButton;
            default -> null;
        };
    }

    private void setButtonsAktiv(boolean aktiv) {
        rotButton.setEnabled(aktiv);
        blauButton.setEnabled(aktiv);
        gruenButton.setEnabled(aktiv);
        gelbButton.setEnabled(aktiv);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimonSpiel::new);
    }
}