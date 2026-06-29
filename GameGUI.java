import javax.swing.*;
import java.awt.*;

/**
 * GUI mit mehreren Spielern + Highscoreliste
 */
public class GameGUI extends JFrame {
    
    private GameLogic game = new GameLogic();
    private JLabel status = new JLabel("Drücke Start", SwingConstants.CENTER);

    private JButton[] buttons = new JButton[4];

    // 🔥 mehrere Spieler
    private String[] names = new String[10];
    private int[] scores = new int[10];
    private int playerCount = 0;

    private String currentPlayer = "";
/**
* GUI wird erstellt (Fenster, Buttons, Farben, Felder)
*/
    public GameGUI() {
        setTitle("Farben-Merkspiel");
        setSize(400, 400);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        buttons[0] = createButton("Rot", 0, Color.RED);
        buttons[1] = createButton("Blau", 1, Color.BLUE);
        buttons[2] = createButton("Grün", 2, Color.GREEN);
        buttons[3] = createButton("Gelb", 3, Color.YELLOW);

        panel.add(buttons[0]);
        panel.add(buttons[1]);
        panel.add(buttons[2]);
        panel.add(buttons[3]);

        JButton start = new JButton("Start");
        start.addActionListener(e -> startGame());

        add(start, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(status, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
/**
* Baut einen automatisch erzeugten Button
*/
    private JButton createButton(String name, int value, Color color) {
        JButton btn = new JButton(name);
        btn.setBackground(color);
        btn.addActionListener(e -> handleInput(value));
        return btn;
    }
/**
* Namenseingabe bei Spielstart
*/
    private void startGame() {

        // 🔥 Spielername eingeben
        currentPlayer = JOptionPane.showInputDialog(this, "Name eingeben:");

        game.resetGame();
        nextRound();
    }
/**
* Nächste Runde wird im Text erwähnt
*/
    private void nextRound() {
        game.nextRound();
        status.setText("Runde: " + game.getLength());
        showSequence();
    }
/**
* Zeigt die zufällige Farbsequenz durch Farben, die kurz aufplopen
*/
    private void showSequence() {
        new Thread(() -> {
            try {
                setButtonsEnabled(false);

                for (int i = 0; i < game.getLength(); i++) {

                    int c = game.getColorAt(i);
                    Color original = buttons[c].getBackground();

                    buttons[c].setBackground(Color.WHITE);
                    Thread.sleep(400);

                    buttons[c].setBackground(original);
                    Thread.sleep(400);
                }

                setButtonsEnabled(true);

            } catch (Exception e) {}
        }).start();
    }
/**
* Spieler kann Buttons klicken
*/
    private void setButtonsEnabled(boolean enabled) {
        for (JButton b : buttons) {
            b.setEnabled(enabled);
        }
    }
/**
* Spielerscore bei falschem Klick wird gespeichert und Spielstatus ausgegeben
*/
    private void handleInput(int color) {

        if (!game.checkInput(color)) {

            int score = game.getLength() - 1;

            // 🔥 Spieler speichern
            names[playerCount] = currentPlayer;
            scores[playerCount] = score;
            playerCount++;

            // 🔥 Highscore finden
            int bestIndex = 0;
            for (int i = 1; i < playerCount; i++) {
                if (scores[i] > scores[bestIndex]) {
                    bestIndex = i;
                }
            }

            status.setText("❌ " + currentPlayer + " Score: " + score +
                    " | Highscore: " + names[bestIndex] + " (" + scores[bestIndex] + ")");

            setButtonsEnabled(false);
            return;
        }

        if (game.roundFinished()) {
            nextRound();
        }
    }

    public static void main(String[] args) {
        new GameGUI();
    }
}