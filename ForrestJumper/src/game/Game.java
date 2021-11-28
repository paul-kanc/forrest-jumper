// to skip levels, see lines 241 to 246.

package game;

import city.cs.engine.DebugViewer;
import city.cs.engine.SoundClip;
import game.Character.CharacterController;
import game.Heart.Lives;
import game.Coins.Pickup;
import game.Crate.MouseHandler;
import game.HighScores.HighScore;
import game.HighScores.TimeScore;
import game.Levels.*;
import game.Levels.ControlPanel;
import game.Reaper.GhostlyReaperCollision;
import game.Reaper.ReaperCollision;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Introduces the main functions to the game.
 */
public class Game {

    /**
     * The game starts on the what is saved to level.
     */
    private static GameLevel level = new GameLevelIntro();
    /**
     * Takes a world variable and displays it in the specified screen size.
     */
    private static final GameView view = new GameView(level, 1200, 700);
    /**
     * Creates a frame for the screen and takes a string to display as a title.
     */
    private static final JFrame frame = new JFrame("Forest Jumper");
    /**
     * Creates a control panel for the main menu
     */
    private static final ControlPanel controlPanel = new ControlPanel();
    /**
     * Creates a high-score screen for when the game is complete.
     */
    private static HighScore highScore;
    /**
     * Creates a debug view for the world.
     */
    private static JFrame debugView;
    private static boolean debugBoolean = false;
    /**
     * Creates a controller so the user can interact with the game.
     */
    private static final CharacterController wasd = new CharacterController();
    private static int gravity = 70;
    /**
     * Creates sound variables.
     */
    private static SoundClip gameMusic, jungleSound, loseGame, winGame;

    public static GameLevel getLevel() {
        return level;
    }

    public static GameView getView() {
        return view;
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static CharacterController getWasd() {
        return wasd;
    }

    public static ControlPanel getControlPanel() {
        return controlPanel;
    }

    public static JFrame getDebugView() {
        return debugView;
    }

    public static boolean isDebugBoolean() {
        return debugBoolean;
    }

    public static int getGravity() {
        return gravity;
    }

    public static SoundClip getGameMusic() {
        return gameMusic;
    }

    public static SoundClip getJungleSound() {
        return jungleSound;
    }

    public static SoundClip getLoseGame() {
        return loseGame;
    }

    public static SoundClip getWinGame() {
        return winGame;
    }

    public static void setLevel(GameLevel level) {
        Game.level = level;
    }

    public static void setDebugView(JFrame debugView) {
        Game.debugView = debugView;
    }

    public static void setDebugBoolean(boolean debugBoolean) {
        Game.debugBoolean = debugBoolean;
    }

    public static void setGravity(int gravity) {
        Game.gravity = gravity;
    }

    public static void setJungleSound(SoundClip jungleSound) {
        Game.jungleSound = jungleSound;
    }

    /**
     * The simulation begins when this method is called.
     */
    public Game() {
        // draw a 1-metre grid over the view
        //view.setGridResolution(1);

        // gets the position of the mouse
        view.addMouseListener(new MouseHandler(view));

        // gives the focus to the frame
        view.addMouseListener(new GiveFocus(view));

        // keyboard listener
        view.addKeyListener(wasd);
        ForegroundController foreground = new ForegroundController();
        view.addKeyListener(foreground);

        // what happens when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);

        // displays the world in the window
        //frame.add(view, BorderLayout.EAST);

        // add control panel
        frame.add(controlPanel.getMainPanel(), BorderLayout.CENTER);

        // dont let the game window be resized
        frame.setResizable(false);

        // size the game window to fit the world view
        frame.pack();

        // make the window visible
        frame.setVisible(true);

        // set gravity strength
        level.setGravity(gravity);

        // start
        level.start();

        // set lives to 3
        Lives.setLives(3);

        // creates a timer
        new TimeScore();

        // play music
        try {
            gameMusic = new SoundClip("data/sounds/song.wav");
            gameMusic.loop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        // create a jungle sound
        try {
            jungleSound = new SoundClip("data/sounds/jungleSounds.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        // create a lose game sound
        try {
            loseGame = new SoundClip("data/sounds/loseGame.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        // create a win game sound
        try {
            winGame = new SoundClip("data/sounds/winGame.wav");
            winGame.setVolume(0.5);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * The level variable is changed when this method is called
     * depending on what is saved to it.
     */
    public static void goToNextLevel() {
        // stop the current level
        level.stop();

        // removes the current character controller
        view.removeKeyListener(wasd);

        if (level instanceof GameLevelIntro) {
            // removes the control panel
            frame.remove(controlPanel.getMainPanel());

            // stops the music
            gameMusic.stop();

            // plays the jungle sound
            try {
                jungleSound = new SoundClip("data/sounds/jungleSounds.wav");
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println(e);
            }
            jungleSound.setVolume(1);
            jungleSound.loop();

            // create the new level
            level = new GameLevel4(); // change the number of the level to force the game to start at different levels

            /*Certain functions will not work properly if you skip levels but you will still be able to complete the
             * game. If you want the game to start from level 7 or beyond, you will have to change the variable in line
             * 271 to true. This will allow the bomb to be dropped without it being picked up.
             */

            // change the debug variable to determine whether the debug view opens
            if (debugBoolean) {
                debugView = new DebugViewer(level, 1200, 700);
            }

            // displays the world in the window
            frame.add(view, BorderLayout.CENTER);

            // refreshes the view
            frame.revalidate();

            // put everything on screen
            GameView.setInstructions(GameView.getLevel1());
            GameView.setHeart1(GameView.getHeart());
            GameView.setHeart2(GameView.getHeart());
            GameView.setHeart3(GameView.getHeart());
            GameView.setCoin(new ImageIcon("data/coin.png").getImage());
            GameView.setX(new ImageIcon("data/x.png").getImage());

            // resets the respawn position for level 6
            GhostlyReaperCollision.setRespawn(new Vec2(-25, -15.5f));

            // resets the bomb
            CharacterController.setBomb(false);

            // starts the timer
            TimeScore.getTime().start();
        }

        // sets the screen to the loss screen if the player has lost their lives
        else if (Lives.getLives() == 0) {
            // stop the jungle sounds
            jungleSound.close();

            // removes previous debugging view if there is one
            if (debugBoolean) {
                debugView.dispose();
            }

            // show end screen
            level = new GameLevelLose();

            // takes everything off the screen
            GameView.setInstructions(GameView.getLose());
            GameView.setHeart1(GameView.getBlank());
            GameView.setCoin(GameView.getBlank());
            GameView.setX(GameView.getBlank());
            Pickup.setNumber(GameView.getBlank());

            // plays the lose game sound
            loseGame.setVolume(0.1);
            loseGame.play();

            // stops the timer
            TimeScore.getTime().stop();
        }

        else if (level instanceof GameLevel1) {
            // removes previous debugging view if there is one
            if (debugBoolean) {
                debugView.dispose();
            }

            // create the new level
            level = new GameLevel2();

            // change the debug variable to determine whether the debug view opens
            if (debugBoolean) {
                debugView = new DebugViewer(level, 1200, 700);
            }

            // put instructions on screen
            GameView.setInstructions(GameView.getLevel2());
        }

        else if (level instanceof GameLevel2) {
            // removes previous debugging view if there is one
            if (debugBoolean) {
                debugView.dispose();
            }

            // create the new level
            level = new GameLevel3();

            // change the debug variable to determine whether the debug view opens
            if (debugBoolean) {
                debugView = new DebugViewer(level, 1200, 700);
            }

            // put instructions on screen
            GameView.setInstructions(GameView.getLevel3());
        }

        else if (level instanceof GameLevel3) {
            // removes previous debugging view if there is one
            if (debugBoolean) {
                debugView.dispose();
            }

            // create the new level
            level = new GameLevel4();

            // change the debug variable to determine whether the debug view opens
            if (debugBoolean) {
                debugView = new DebugViewer(level, 1200, 700);
            }

            // put instructions on screen
            GameView.setInstructions(GameView.getLevel4());
        }

        else if (level instanceof GameLevel4) {
            // removes previous debugging view if there is one
            if (debugBoolean) {
                debugView.dispose();
            }

            // create the new level
            level = new GameLevel5();

            // change the debug variable to determine whether the debug view opens
            if (debugBoolean) {
                debugView = new DebugViewer(level, 1200, 700);
            }

            // put instructions on screen
            GameView.setInstructions(GameView.getLevel5());
        }

        else if (level instanceof GameLevel5) {
            // removes previous debugging view if there is one
            if (debugBoolean) {
                debugView.dispose();
            }

            // create the new level
            level = new GameLevel6();

            // change the debug variable to determine whether the debug view opens
            if (debugBoolean) {
                debugView = new DebugViewer(level, 1200, 700);
            }

            // put instructions on screen
            GameView.setInstructions(GameView.getLevel6());
        }

        else if (level instanceof GameLevel6) {
            // removes previous debugging view if there is one
            if (debugBoolean) {
                debugView.dispose();
            }

            // create the new level
            level = new GameLevel7();

            // change the debug variable to determine whether the debug view opens
            if (debugBoolean) {
                debugView = new DebugViewer(level, 1200, 700);
            }

            // put instructions on screen
            GameView.setInstructions(GameView.getLevel7());

            // change the coin into a skull
            GameView.setCoin(new ImageIcon("data/skull.png").getImage());
        }

        else if (level instanceof GameLevel7) {
            // removes previous debugging view if there is one
            if (debugBoolean) {
                debugView.dispose();
            }

            // create the new level
            level = new GameLevel8();

            // change the debug variable to determine whether the debug view opens
            if (debugBoolean) {
                debugView = new DebugViewer(level, 1200, 700);
            }

            // put instructions on screen
            GameView.setInstructions(GameView.getLevel8());
        }

        else if (level instanceof GameLevel8) {
            // removes previous debugging view if there is one
            if (debugBoolean) {
                debugView.dispose();
            }

            // create the new level
            level = new GameLevel9();

            // change the debug variable to determine whether the debug view opens
            if (debugBoolean) {
                debugView = new DebugViewer(level, 1200, 700);
            }

            // put instructions on screen
            GameView.setInstructions(GameView.getLevel9());
        }

        else if (level instanceof GameLevel9) {
            // removes previous debugging view if there is one
            if (debugBoolean) {
                debugView.dispose();
            }

            // create the new level
            level = new GameLevel10();

            // change the debug variable to determine whether the debug view opens
            if (debugBoolean) {
                debugView = new DebugViewer(level, 1200, 700);
            }

            // put instructions on screen
            GameView.setInstructions(GameView.getLevel10());
        }

        else if (level instanceof GameLevel10) {
            // removes previous debugging view if there is one
            if (debugBoolean) {
                debugView.dispose();
            }

            level = new GameLevelWin();

            // stop the jungle sounds
            jungleSound.close();

            // removes previous debugging view if there is one
            if (debugBoolean) {
                debugView.dispose();
            }

            // show end screen
            level = new GameLevelWin();

            // takes everything off the screen
            GameView.setInstructions(GameView.getBlank());
            GameView.setHeart1(GameView.getBlank());
            GameView.setHeart2(GameView.getBlank());
            GameView.setHeart3(GameView.getBlank());
            GameView.setHeart4(GameView.getBlank());
            GameView.setHeart5(GameView.getBlank());
            GameView.setCoin(GameView.getBlank());
            GameView.setX(GameView.getBlank());
            Pickup.setNumber(GameView.getBlank());

            // plays the win game sound
            winGame.play();

            // stops the timer
            TimeScore.getTime().stop();

            // changes the frame to the high scores
            frame.remove(view);
            highScore = new HighScore();
            frame.add(highScore.getPnlScore(), BorderLayout.CENTER);

            // refreshes the view
            frame.revalidate();
        }

        else if (level instanceof GameLevelWin || level instanceof GameLevelLose) {
            // resets and goes back to the beginning
            level = new GameLevelIntro();
            ReaperCollision.setWalkingSpeed1(9);
            ReaperCollision.setWalkingSpeed2(-11);
            ReaperCollision.setWalkingSpeed3(7);
            frame.remove(highScore.getPnlScore());
            frame.remove(view);
            frame.add(controlPanel.getMainPanel(), BorderLayout.CENTER);
            TimeScore.setScore(0);

            // takes everything off the screen
            GameView.setInstructions(GameView.getBlank());
            GameView.setHeart1(GameView.getBlank());
            GameView.setHeart2(GameView.getBlank());
            GameView.setHeart3(GameView.getBlank());
            GameView.setHeart4(GameView.getBlank());
            GameView.setHeart5(GameView.getBlank());
            GameView.setCoin(GameView.getBlank());
            GameView.setX(GameView.getBlank());
            Pickup.setNumber(GameView.getBlank());

            // refreshes the view
            frame.repaint();

            // stop other sounds
            winGame.stop();
            loseGame.stop();

            // plays music
            gameMusic.play();
        }

        // change the view to look into new level
        view.setWorld(level);

        // re-adds the character controller
        view.addKeyListener(wasd);

        // reset score
        Pickup.pointsReset();

        // reset onscreen number
        if (!(Lives.getLives() == 0 || level instanceof GameLevelWin)) {
            Pickup.setNumber(GameView.getZero());
        }

        // set gravity strength
        level.setGravity(gravity);

        ForegroundController.setOnce(0);

        // start the simulation in the new level
        level.start();
    }

    public static void main(String[] args) {
        new Game();
    }
}