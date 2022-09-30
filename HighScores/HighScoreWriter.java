package game.HighScores;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Saves a name and a score to a high-score file.
 */
public class HighScoreWriter {

    private String fileName;

    /**
     * Initialises a new HighScoreWriter
     * @param fileName the name of the high-score file
     */
    public HighScoreWriter(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Writes a name and a score to a file.
     * @param name the name that the player chose to save.
     * @param score the player's score
     */
    public void writeHighScore(String name, int score) throws IOException {
        boolean append = true;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, append);
            writer.write(name + "," + score + "\n");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}