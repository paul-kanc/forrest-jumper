package game.HighScores;

import game.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class HighScore {
    private JPanel pnlScore;
    private JPanel pnlControls;
    private JLabel lblPlayerName;
    private JTextField txtName;
    private JLabel lblScoreTitle;
    private JLabel lblScore;
    private JButton btnSave;
    private JButton btnCancel;
    private JList<String> lstScore;

    private static final String fileName = "data/txtFiles/highScoreTable.txt";
    private static final HighScoreReader highScoreReader = new HighScoreReader(fileName);
    private static final HighScoreWriter highScoreWriter = new HighScoreWriter(fileName);

    public HighScore() {
        lblScore.setText(String.valueOf(TimeScore.getScore()));

        File scores = new File(fileName);
        try {
            scores.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    highScoreWriter.writeHighScore(txtName.getText(), TimeScore.getScore());
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
                Game.goToNextLevel();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.goToNextLevel();
            }
        });

        DefaultListModel<String> model = new DefaultListModel<>();
        try {
            model.addAll(highScoreReader.readScores());
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        lstScore.setModel(model);
    }

    public JPanel getPnlScore() {
        return pnlScore;
    }
}
