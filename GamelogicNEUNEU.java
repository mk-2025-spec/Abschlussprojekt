import java.util.Random;

/**
 * Spiellogik: verwaltet Sequenz und Eingabeprüfung
 */
public class GameLogic {

    public static final int ROT = 0;
    public static final int BLAU = 1;
    public static final int GRUEN = 2;
    public static final int GELB = 3;

    private int[] sequence = new int[100];
    private int length = 0;
    private int inputIndex = 0;

    private Random rand = new Random();

    public void nextRound() {
        sequence[length] = rand.nextInt(4);
        length++;
        inputIndex = 0;
    }

    public int getColorAt(int index) {
        return sequence[index];
    }

    public int getLength() {
        return length;
    }

    public boolean checkInput(int color) {
        if (sequence[inputIndex] == color) {
            inputIndex++;
            return true;
        } else {
            return false;
        }
    }

    public boolean roundFinished() {
        return inputIndex == length;
    }

    public void resetGame() {
        length = 0;
        inputIndex = 0;
    }
}