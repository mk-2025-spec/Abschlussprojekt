import javax.swing.*;
import java.awt.*;

/**
 * GUI für das Farbmerkspiel
 */
public class GameGUI extends JFrame {

    private GameLogic game = new GameLogic();
    private Highscore highscore = new Highscore();

    private JLabel status = new JLabel("Drücke Start", SwingConstants.CENTER);
    private JButton[] buttons = new JButton[4];

    private String currentPlayer = "";

    public GameGUI() {

        setTitle("Farben-Merkspiel");
        setSize(400, 400);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        buttons[0] = createButton("Rot", GameLogic.ROT, Color.RED);
        buttons[1] = createButton("Blau", GameLogic.BLAU, Color.BLUE);
        buttons[2] = createButton("Grün", GameLogic.GRUEN, Color.GREEN);
        buttons[3] = createButton("Gelb", GameLogic.GELB, Color.YELLOW);

        panel.add(buttons[0]);
        panel.add(buttons[1]);
        panel.add(buttons[2]);
        panel.add(buttons[3]);

        JButton start = new JButton("Start");
        start.addActionListener(e -> startGame());

        JButton restart = new JButton("Restart");
        restart.addActionListener(e -> restartGame());

        JPanel top = new JPanel();
        top.add(start);
        top.add(restart);

        add(top, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(status, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JButton createButton(String name, int value, Color color) {
        JButton btn = new JButton(name);
        btn.setBackground(color);
        btn.addActionListener(e -> handleInput(value));
        return btn;
    }

    private void startGame() {

        currentPlayer = JOptionPane.showInputDialog(this, "Name eingeben:");

        if (currentPlayer == null || currentPlayer.equals("")) {
            currentPlayer = "Spieler";
        }

        game.resetGame();
        nextRound();
    }

    private void restartGame() {
        game.resetGame();
        status.setText("Drücke Start");
        setButtonsEnabled(true);
    }

    private void nextRound() {
        game.nextRound();
        status.setText("Runde: " + game.getLength());
        showSequence();
    }

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

            } catch (InterruptedException e) {
                System.out.println("Anzeige-Fehler");
            }
        }).start();
    }

    private void setButtonsEnabled(boolean enabled) {
        for (JButton b : buttons) {
            b.setEnabled(enabled);
        }
    }

    private void handleInput(int color) {

        if (!game.checkInput(color)) {

            int score = game.getLength() - 1;

            highscore.addScore(currentPlayer, score);

            status.setText("❌ " + currentPlayer +
                    " Score: " + score +
                    " | Highscore: " + highscore.getBestScoreText());

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