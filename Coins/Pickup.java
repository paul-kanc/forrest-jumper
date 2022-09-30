package game.Coins;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import game.Character.Character;
import game.Game;
import game.GameView;
import game.Levels.GameLevel5;
import game.Levels.GameLevel6;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

public class Pickup implements CollisionListener {

    private final Character man;
    private final Coin coin;
    private static int points = 0;
    private static Image number;
    private static SoundClip pickup, winLevel;

    public static Image getNumber() {
        return number;
    }

    public static int getPoints() {
        return points;
    }

    public static SoundClip getPickup() {
        return pickup;
    }

    public static SoundClip getWinLevel() {
        return winLevel;
    }

    public static void setNumber(Image number) {
        Pickup.number = number;
    }

    public static void setPoints(int points) {
        Pickup.points = points;
    }

    public Pickup(Character man, Coin coin) {
        this.man = man;
        this.coin = coin;
    }

    public static void pointsReset() {
        points = 0;
    }

    static {
        // create a pickup sound
        try {
            pickup = new SoundClip("data/sounds/pickup.wav");
            pickup.setVolume(0.2);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        // create a win level sound
        try {
            winLevel = new SoundClip("data/sounds/winLevel.wav");
            winLevel.setVolume(0.7);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    @Override
    public void collide(CollisionEvent pickup) {
        if (pickup.getOtherBody() == man && pickup.getReportingBody() == coin) {
            pickup.getReportingBody().destroy();

            points++;

            if (points == 1) {
                number = GameView.getOne();
            }

            else if (points == 2) {
                number = GameView.getTwo();
            }

            else if (points == 3) {
                number = GameView.getThree();
            }

            else if (points == 4) {
                number = GameView.getFour();
            }

            else if (points == 5) {
                number = GameView.getFive();
            }

            if (Game.getLevel().isComplete()) {
                if (!(Game.getLevel() instanceof GameLevel6)) {
                    winLevel.play();
                }
                Game.goToNextLevel();
            } else {
                Pickup.pickup.play();
            }
        }
    }
}