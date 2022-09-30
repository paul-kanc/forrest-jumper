package game.Levels;

import city.cs.engine.SoundClip;
import game.Game;
import game.GameSaverLoader;
import game.GameView;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ControlPanel {
    private JPanel mainPanel;
    private JRadioButton easyRadioButton;
    private JRadioButton mediumRadioButton;
    private JRadioButton hardRadioButton;
    private JCheckBox lowGravityCheckBox;
    private JButton startButton;
    private JLabel titleLabel;
    private JLabel difficultyLabel;
    private JLabel gravityLabel;
    private JCheckBox debugViewerCheckBox;
    private JLabel debugViewerLabel;
    private JButton quitButton;
    private JButton loadButton;
    private static SoundClip menu;

    static {
        // create a menu interaction sound
        try {
            menu = new SoundClip("data/sounds/menu.wav");
            menu.setVolume(0.1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public static SoundClip getMenu() {
        return menu;
    }

    public ControlPanel() {
        titleLabel.setIcon(new ImageIcon("data/forrestJumper.png"));
        difficultyLabel.setIcon(new ImageIcon("data/difficulty.png"));
        debugViewerLabel.setIcon(new ImageIcon("data/debugViewer.png"));
        gravityLabel.setIcon(new ImageIcon("data/gravity.png"));
        startButton.setIcon(new ImageIcon("data/start.png"));
        loadButton.setIcon(new ImageIcon("data/load.png"));
        quitButton.setIcon(new ImageIcon("data/quit.png"));

        easyRadioButton.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                GameLevel.easy();
            }
        });

        easyRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.play();
            }
        });

        mediumRadioButton.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                GameLevel.medium();
            }
        });

        mediumRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.play();
            }
        });

        hardRadioButton.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                GameLevel.hard();
            }
        });

        hardRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.play();
            }
        });

        debugViewerCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Game.setDebugBoolean(true);
                } else {
                    Game.setDebugBoolean(false);
                }
                menu.play();
            }
        });

        lowGravityCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Game.setGravity(30);
                } else {
                    Game.setGravity(70);
                }
                menu.play();
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.goToNextLevel();
                menu.play();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // removes the control panel
                Game.getFrame().remove(Game.getControlPanel().getMainPanel());

                // displays the world in the window
                Game.getFrame().add(Game.getView(), BorderLayout.CENTER);

                // refreshes the view
                Game.getFrame().revalidate();

                try {
                    GameSaverLoader.load("data/txtFiles/save.txt");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                // put everything on screen
                GameView.setInstructions(GameView.getLevel1());
                GameView.setHeart1(GameView.getHeart());
                GameView.setHeart2(GameView.getHeart());
                GameView.setHeart3(GameView.getHeart());
                GameView.setX(new ImageIcon("data/x.png").getImage());
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
