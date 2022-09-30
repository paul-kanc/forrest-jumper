package game.HighScores;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Counts the time that the player has been in game for and generates a score based on that.
 */
public class TimeScore implements ActionListener {
    private static Timer time;
    private static int score;

    public static Timer getTime() {
        return time;
    }

    public static int getScore() {
        return score;
    }

    public static void setGameTime(Timer gameTime) {
        TimeScore.time = gameTime;
    }

    public static void setScore(int score) {
        TimeScore.score = score;
    }

    public TimeScore() {
        time = new Timer(1, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        score++;
    }
}
