package game.Character;

import city.cs.engine.BodyImage;
import city.cs.engine.DebugViewer;
import city.cs.engine.SoundClip;
import game.Bomb.Bomb;
import game.Coins.Pickup;
import game.ForegroundController;
import game.Game;
import game.GameView;
import game.Heart.Lives;
import game.Levels.*;
import game.Reaper.GhostlyReaperCollision;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class CharacterController implements KeyListener {

    private static final int walkingSpeed = 15;
    private static int jumpingStrength = 30;

    private static final BodyImage manC = new BodyImage("data/manC.png", 2);
    private static final BodyImage manL = new BodyImage("data/manL.gif", 2);
    private static final BodyImage manR = new BodyImage("data/manR.gif", 2);

    private static boolean bomb = false;

    private static final Vec2 bombLeft = new Vec2(-1, 0);
    private static final Vec2 bombRight = new Vec2(1, 0);
    private static Vec2 bombPos = bombRight;

    public static BodyImage getManC() {
        return manC;
    }

    public static void setBomb(boolean bomb) {
        CharacterController.bomb = bomb;
    }

    @Override
    public void keyTyped(KeyEvent typed) {

    }

    @Override
    public void keyPressed(KeyEvent pressed) {

        int code = pressed.getKeyCode();

        if (code == KeyEvent.VK_A) {
            GameLevel.getMan().startWalking(-walkingSpeed);
            GameLevel.getMan().removeAllImages();
            GameLevel.getMan().addImage(manL);
            bombPos = bombLeft;
        }

        if (code == KeyEvent.VK_D) {
            GameLevel.getMan().startWalking(walkingSpeed);
            GameLevel.getMan().removeAllImages();
            GameLevel.getMan().addImage(manR);
            bombPos = bombRight;
        }

        if (code == KeyEvent.VK_W) {
            if (jumpingStrength == 30) {
                GameLevel.getMan().jump(jumpingStrength);
                jumpingStrength = 0;
            }
        }

        if (code == KeyEvent.VK_E) {
            if ((!(Game.getLevel() instanceof GameLevelIntro || Game.getLevel() instanceof GameLevelWin || Game.getLevel() instanceof GameLevelLose)) && bomb) {
                Bomb bomb = new Bomb(Game.getLevel(), GameLevel.getMan().getPosition().add(bombPos), 2000);
                CharacterController.bomb = false;
            }
        }

        if (code == KeyEvent.VK_R) {
            if (!(Game.getLevel() instanceof GameLevelIntro || Game.getLevel() instanceof GameLevelWin || Game.getLevel() instanceof GameLevelLose) &&
                    ForegroundController.getPaused()) {
                // unpause
                ForegroundController.setPaused(false);

                ForegroundController.setOnce(0);

                // stop the current level
                Game.getLevel().stop();

                // removes previous debugging view if there is one
                if (Game.isDebugBoolean()) {
                    Game.getDebugView().dispose();
                }

                // removes the current character controller
                Game.getView().removeKeyListener(Game.getWasd());

                if (Game.getLevel() instanceof GameLevel1) {
                    Game.setLevel(new GameLevel1());
                    GameView.setInstructions(GameView.getLevel1());
                }

                else if (Game.getLevel() instanceof GameLevel2) {
                    Game.setLevel(new GameLevel2());
                    GameView.setInstructions(GameView.getLevel2());
                }

                else if (Game.getLevel() instanceof GameLevel3) {
                    Game.setLevel(new GameLevel3());
                    GameView.setInstructions(GameView.getLevel3());
                }

                else if (Game.getLevel() instanceof GameLevel4) {
                    Game.setLevel(new GameLevel4());
                    GameView.setInstructions(GameView.getLevel4());
                }

                else if (Game.getLevel() instanceof GameLevel5) {
                    Game.setLevel(new GameLevel5());
                    GameView.setInstructions(GameView.getLevel5());
                }

                else if (Game.getLevel() instanceof GameLevel6) {
                    Game.setLevel(new GameLevel6());
                    GameView.setInstructions(GameView.getLevel6());
                    GhostlyReaperCollision.setRespawn(new Vec2(-25, -15.5f));
                    Game.getJungleSound().stop();
                    try {
                        Game.setJungleSound(new SoundClip("data/sounds/jungleSounds.wav"));
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                        System.out.println(e);
                    }
                    Game.getJungleSound().setVolume(1);
                    Game.getJungleSound().loop();
                }

                else if (Game.getLevel() instanceof GameLevel7) {
                    Game.setLevel(new GameLevel7());
                    GameView.setInstructions(GameView.getLevel7());
                }

                else if (Game.getLevel() instanceof GameLevel8) {
                    Game.setLevel(new GameLevel8());
                    GameView.setInstructions(GameView.getLevel8());
                }

                else if (Game.getLevel() instanceof GameLevel9) {
                    Game.setLevel(new GameLevel9());
                    GameView.setInstructions(GameView.getLevel9());
                }

                else if (Game.getLevel() instanceof GameLevel10) {
                    Game.setLevel(new GameLevel10());
                    GameView.setInstructions(GameView.getLevel10());
                }

                // change the view to look into new level
                Game.getView().setWorld(Game.getLevel());

                // re-adds the character controller
                Game.getView().addKeyListener(Game.getWasd());

                // reset score
                Pickup.pointsReset();

                // reset onscreen number
                Pickup.setNumber(GameView.getZero());

                // set gravity strength
                Game.getLevel().setGravity(Game.getGravity());

                // change the debug variable to determine whether the debug view opens
                if (Game.isDebugBoolean()) {
                    Game.setDebugView(new DebugViewer(Game.getLevel(), 1200, 700));
                }

                // start the simulation in the new level
                Game.getLevel().start();

                // play menu sound
                ControlPanel.getMenu().play();
            }
        }

        if (code == KeyEvent.VK_ENTER) {
            if (Game.getLevel() instanceof GameLevelLose || Game.getLevel() instanceof GameLevelWin) {
                // resets lives
                Lives.setLives(3);

                // goes back to the beginning
                Game.goToNextLevel();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent released) {

        int code = released.getKeyCode();

        if (code == KeyEvent.VK_A) {
            GameLevel.getMan().startWalking(0);
            GameLevel.getMan().removeAllImages();
            GameLevel.getMan().addImage(manC);
        }

        if (code == KeyEvent.VK_D) {
            GameLevel.getMan().startWalking(0);
            GameLevel.getMan().removeAllImages();
            GameLevel.getMan().addImage(manC);
        }

        if (code == KeyEvent.VK_W) {
            jumpingStrength = 30;
        }
    }
}