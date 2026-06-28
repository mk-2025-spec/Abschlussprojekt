import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class SimonGame extends JFrame implements ActionListener {

    // Farben
    private JButton redButton;
    private JButton greenButton;
    private JButton blueButton;
    private JButton yellowButton;

    private JLabel statusLabel;
    private JLabel highscoreLabel;

    // Spielvariablen
    private ArrayList<Integer> sequence = new ArrayList<>();
    private ArrayList<Integer> playerInput = new ArrayList<>();

    private Random random = new Random();

    private int round = 1;
    private int highscore = 0;

    private boolean playerTurn = false;

    public SimonGame() {
        setTitle("Simon Says");
        setSize(500, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Statusanzeige
        JPanel topPanel = new JPanel(new GridLayout(2,1));

        statusLabel = new JLabel("Drücke Start", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 22));

        highscoreLabel = new JLabel("Highscore: 0", SwingConstants.CENTER);
        highscoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        topPanel.add(statusLabel);
        topPanel.add(highscoreLabel);

        add(topPanel, BorderLayout.NORTH);

        // Farbbuttons
        JPanel gamePanel = new JPanel(new GridLayout(2,2,10,10));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        redButton = createButton(Color.RED, 0);
        greenButton = createButton(Color.GREEN, 1);
        blueButton = createButton(Color.BLUE, 2);
        yellowButton = createButton(Color.YELLOW, 3);

        gamePanel.add(redButton);
        gamePanel.add(greenButton);
        gamePanel.add(blueButton);
        gamePanel.add(yellowButton);

        add(gamePanel, BorderLayout.CENTER);

        // Startbutton
        JButton startButton = new JButton("Spiel starten");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));

        startButton.addActionListener(e -> startGame());

        add(startButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Button erstellen
    private JButton createButton(Color color, int id) {
        JButton button = new JButton();
        button.setBackground(color);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setActionCommand(String.valueOf(id));
        button.addActionListener(this);
        return button;
    }

    // Spiel starten
    private void startGame() {
        sequence.clear();
        playerInput.clear();
        round = 1;

        statusLabel.setText("Runde 1");

        nextRound();
    }

    // Neue Runde
    private void nextRound() {
        playerInput.clear();

        // Neue Farbe hinzufügen
        sequence.add(random.nextInt(4));

        statusLabel.setText("Computer zeigt Sequenz...");

        playerTurn = false;

        showSequence();
    }

    // Sequenz anzeigen
    private void showSequence() {

        Timer timer = new Timer(700, null);

        final int[] index = {0};

        timer.addActionListener(e -> {

            if(index[0] > 0) {
                resetButtonColors();
            }

            if(index[0] < sequence.size()) {

                int color = sequence.get(index[0]);

                highlightButton(color);

                index[0]++;

            } else {

                timer.stop();

                resetButtonColors();

                playerTurn = true;

                statusLabel.setText("Jetzt bist du dran!");
            }
        });

        timer.setInitialDelay(500);
        timer.start();
    }

    // Farbe hervorheben
    private void highlightButton(int color) {

        switch(color) {

            case 0:
                redButton.setBackground(Color.PINK);
                break;

            case 1:
                greenButton.setBackground(Color.WHITE);
                break;

            case 2:
                blueButton.setBackground(Color.CYAN);
                break;

            case 3:
                yellowButton.setBackground(Color.ORANGE);
                break;
        }
    }

    // Originalfarben zurücksetzen
    private void resetButtonColors() {
        redButton.setBackground(Color.RED);
        greenButton.setBackground(Color.GREEN);
        blueButton.setBackground(Color.BLUE);
        yellowButton.setBackground(Color.YELLOW);
    }

    // Spieler klickt
    @Override
    public void actionPerformed(ActionEvent e) {

        if(!playerTurn) {
            return;
        }

        int clicked = Integer.parseInt(e.getActionCommand());

        playerInput.add(clicked);

        int currentIndex = playerInput.size() - 1;

        // Fehler?
        if(playerInput.get(currentIndex) != sequence.get(currentIndex)) {

            gameOver();
            return;
        }

        // Runde geschafft?
        if(playerInput.size() == sequence.size()) {

            round++;

            if(sequence.size() > highscore) {
                highscore = sequence.size();
                highscoreLabel.setText("Highscore: " + highscore);
            }

            statusLabel.setText("Richtig! Nächste Runde...");

            Timer delay = new Timer(1000, ev -> nextRound());
            delay.setRepeats(false);
            delay.start();
        }
    }

    // Spielende
    private void gameOver() {

        playerTurn = false;

        JOptionPane.showMessageDialog(this,
                "Game Over!\nErreichte Runde: " + sequence.size(),
                "Verloren",
                JOptionPane.ERROR_MESSAGE);

        statusLabel.setText("Game Over! Starte neu.");
    }

    // Main
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new SimonGame());
    }
}