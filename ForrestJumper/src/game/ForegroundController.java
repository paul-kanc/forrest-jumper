package game;

import game.Coins.Pickup;
import game.Heart.Lives;
import game.Levels.ControlPanel;
import game.Levels.GameLevelIntro;
import game.Levels.GameLevelLose;
import game.Levels.GameLevelWin;
import game.Reaper.ReaperCollision;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class ForegroundController implements KeyListener {

    private static Boolean paused = false;
    private static int once = 0;

    public static Boolean getPaused() {
        return paused;
    }

    public static void setPaused(Boolean paused) {
        ForegroundController.paused = paused;
    }

    public static void setOnce(int once) {
        ForegroundController.once = once;
    }

    @Override
    public void keyTyped(KeyEvent typed) {

    }

    @Override
    public void keyPressed(KeyEvent pressed) {

        int code = pressed.getKeyCode();

        if (!(Game.getLevel() instanceof GameLevelIntro || Game.getLevel() instanceof GameLevelWin || Game.getLevel() instanceof GameLevelLose)) {
            if (code == KeyEvent.VK_SPACE) {
                GameView.setInstructions(GameView.getBlank());
                if (once == 0) {
                    ControlPanel.getMenu().play();
                    once++;
                }
            }
        }

        if (!(Game.getLevel() instanceof GameLevelIntro || Game.getLevel() instanceof GameLevelWin || Game.getLevel() instanceof GameLevelLose)) {
            if (code == KeyEvent.VK_ESCAPE) {
                ControlPanel.getMenu().play();
                if (paused) {
                    Game.getLevel().start();
                    Game.getJungleSound().resume();
                    GameView.setInstructions(GameView.getBlank());
                } else {
                    GameView.setInstructions(GameView.getPaused());
                    Game.getJungleSound().pause();
                    Game.getLevel().stop();
                }
                paused = !paused;
            }

            if (paused) {
                if (code == KeyEvent.VK_ENTER) {
                    paused = false;

                    // saves the game
                    try {
                        GameSaverLoader.save(Game.getLevel(), "data/txtFiles/save.txt");
                        System.out.println("Game Saved");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // resets and goes back to the beginning
                    Game.setLevel(new GameLevelIntro());
                    ReaperCollision.setWalkingSpeed1(9);
                    ReaperCollision.setWalkingSpeed2(-11);
                    ReaperCollision.setWalkingSpeed3(7);
                    Lives.setLives(3);
                    Game.getFrame().remove(Game.getView());
                    Game.getFrame().add(Game.getControlPanel().getMainPanel(), BorderLayout.CENTER);

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
                    Game.getFrame().repaint();

                    // stop other sounds
                    Game.getWinGame().stop();
                    Game.getLoseGame().stop();

                    // plays music
                    Game.getGameMusic().play();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent released) {

    }
}
