import java.util.Random;

/**
 * Spiellogik: verwaltet Sequenz und Eingabeprüfung
 */
public class GameLogic {

/**
* Zahlen durch Namen ersetzt
*/
    public static final int ROT = 0;
    public static final int BLAU = 1;
    public static final int GRUEN = 2;
    public static final int GELB = 3;

    private int[] sequence = new int[100];
    private int length = 0;
    private int inputIndex = 0;

    private Random rand = new Random();

/**
* Bereitet eine Runde vor
*/
    public void nextRound() {
        sequence[length] = rand.nextInt(4);
        length++;
        inputIndex = 0;
    }
/**
*Gibt ne Farbe aus der Sequenz zurück
*/
    public int getColorAt(int index) {
        return sequence[index];
    }
/**
* Gibt die Länge der Sequenz zurück
*/
    public int getLength() {
        return length;
    }
/**
* Prüft, ob die richtige Farbe gedrückt wurde
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
* Prüft, ob Eingabe richtig ist
*/
    public boolean roundFinished() {
        return inputIndex == length;
    }
/**
* Alles wird zurückgesetzt
*/
    public void resetGame() {
        length = 0;
        inputIndex = 0;
    }
}