package game.HighScores;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Reads the scores and names in a file and saves them to a String list.
 */
public class HighScoreReader {

    private String fileName;

    /**
     * Initialises a new HighScoreReader
     * @param fileName the name of the high-score file
     */
    public HighScoreReader(String fileName) {
        this.fileName = fileName;

    }

    /**
     * Reads the high-score data from the high-score file and saves
     * the 10 highest scores to a list. The positions are numbered.
     */
    public List<String> readScores() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        List<String> scores = new ArrayList<>();
        try {
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                // file is assumed to contain one name, score pair per line
                String[] tokens = line.split(",");
                String name = tokens[0];
                int score = Integer.parseInt(tokens[1]);
                scores.add("Score: " + score + "                                " + "Name: " + name);
                line = reader.readLine();
            }
            Collections.sort(scores);

            for (String i : scores) {
                if (scores.indexOf(i)+1 <= 10) {
                    scores.set(scores.indexOf(i), scores.indexOf(i) + 1 + "                                " + i);
                }
            }

            for (int i = scores.size()-1; i > 9; i--) {
                scores.remove(i);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
        return scores;
    }
}