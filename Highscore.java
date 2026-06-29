public class Highscore {

    private String[] names = new String[10];
    private int[] scores = new int[10];
    private int count = 0;
/**
* Ein neuer Spieler bekommt einen neuen Score
*/
    public void addScore(String name, int score) {
        if (count < 10) {
            names[count] = name;
            scores[count] = score;
            count++;
        }
    }
/**
* Sucht besten Spieler und erwähnt ihn
*/
    public String getBestScoreText() {

        if (count == 0) {
            return "Kein Highscore";
        }

        int best = 0;

        for (int i = 1; i < count; i++) {
            if (scores[i] > scores[best]) {
                best = i;
            }
        }

        return names[best] + " (" + scores[best] + ")";
    }
}