 import java.util.Random;

/**
 * Diese Klasse enthält die komplette Spiellogik.
 * Sie verwaltet die Farbsequenz und überprüft die Eingaben.
 */
public class GameLogic {

    private int[] sequence = new int[100]; // speichert Farbsequenz
    private int length = 0;                // aktuelle Länge
    private int inputIndex = 0;            // aktuelle Spielerposition
    private Random rand = new Random();

    /**
     * Startet eine neue Runde → fügt neue Farbe hinzu
     */
    public void nextRound() {
        sequence[length] = rand.nextInt(4); // Werte 0-3
        length++;
        inputIndex = 0;
    }

    /**
     * Gibt Farbe an Position zurück
     */
    public int getColorAt(int index) {
        return sequence[index];
    }

    /**
     * Gibt aktuelle Länge zurück
     */
    public int getLength() {
        return length;
    }

    /**
     * Prüft Spielereingabe
     */
    public boolean checkInput(int color) {
        if (sequence[inputIndex] == color) {
            inputIndex++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Prüft ob Runde abgeschlossen ist
     */
    public boolean roundFinished() {
        return inputIndex == length;
    }

    /**
     * Setzt Spiel zurück
     */
    public void resetGame() {
        length = 0;
        inputIndex = 0;
    }
}