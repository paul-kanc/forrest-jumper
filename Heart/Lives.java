package game.Heart;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import game.Bomb.BombItemPickup;
import game.Character.Character;
import game.Coins.Pickup;
import game.Game;
import game.Levels.*;
import game.GameView;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Lives implements CollisionListener {
    private final Character man;
    private final Heart heart;
    private static int lives;
    private static SoundClip loseLife;

    public static int getLives() {
        return lives;
    }

    public static SoundClip getLoseLife() {
        return loseLife;
    }

    public static void setLives(int lives) {
        Lives.lives = lives;
    }

    public Lives(Character man, Heart heart) {
        this.man = man;
        this.heart = heart;
    }

    static {
        //create a lose life sound
        try {
            loseLife = new SoundClip("data/sounds/loseLife.wav");
            loseLife.setVolume(0.8);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    @Override
    public void collide(CollisionEvent loseLife) {
        if ((loseLife.getOtherBody() == GameLevel.getReaper1() || loseLife.getOtherBody() == GameLevel.getReaper2() || loseLife.getOtherBody() ==
                GameLevel.getReaper3()) && loseLife.getReportingBody() == man) {
            if (Game.getLevel() instanceof GameLevel6 && BombItemPickup.isBombPickedUp()) {
                man.setPosition(new Vec2(25, 12));
            }

            if (Game.getLevel() instanceof GameLevel7) {
                man.setPosition(new Vec2(27, 10));
            }

            else if (Game.getLevel() instanceof GameLevel8) {
                man.setPosition(new Vec2(27, 4.5f));
            }

            else if (Game.getLevel() instanceof GameLevel9) {
                man.setPosition(new Vec2(25, -15.5f));
            }

            else if (Game.getLevel() instanceof GameLevel10) {
                man.setPosition(new Vec2(25, -15.5f));
            }

            else {
                man.setPosition(new Vec2(-25, -15.5f));
            }

            lives--;

            if (lives == 4) {
                GameView.setHeart5(GameView.getBlank());

                // plays the loseLife sound
                Lives.loseLife.play();
            }

            else if (lives == 3) {
                GameView.setHeart4(GameView.getBlank());

                // plays the loseLife sound
                Lives.loseLife.play();
            }

            else if (lives == 2) {
                GameView.setHeart3(GameView.getBlank());

                // plays the loseLife sound
                Lives.loseLife.play();
            }

            else if (lives == 1) {
                GameView.setHeart2(GameView.getBlank());

                // plays the loseLife sound
                Lives.loseLife.play();
            }

            else if (lives == 0) {
                Game.goToNextLevel();
            }
        }

        if (loseLife.getReportingBody() == heart && loseLife.getOtherBody() == man) {
            loseLife.getReportingBody().destroy();

            if (lives != 5) {
                lives++;
                Pickup.getPickup().play();
            }

            if (lives == 2) {
                GameView.setHeart2(GameView.getHeart());
            }

            else if (lives == 3) {
                GameView.setHeart3(GameView.getHeart());
            }

            else if (lives == 4) {
                GameView.setHeart4(GameView.getHeart());
            }

            else if (lives == 5) {
                GameView.setHeart5(GameView.getHeart());
            }
        }
    }
}