package game.Bomb;

import city.cs.engine.*;
import game.Character.CharacterController;
import game.Coins.Pickup;
import game.Game;
import game.GameView;
import game.Heart.Lives;
import game.Levels.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Explosion extends DynamicBody implements ActionListener {
    private Sensor explosion;
    private static SoundClip kill;

    static {
        // create a kill sound
        try {
            kill = new SoundClip("data/sounds/kill.wav");
            kill.setVolume(1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Explosion(World w) {
        super(w);

        explosion = new Sensor(this, new CircleShape(3));
        addImage(new BodyImage("data/explosion.png", 6));
        setPosition(Bomb.getBomb().getPosition());
        setGravityScale(0);

        Timer t = new Timer(500, this);
        t.start();
        t.setRepeats(false);

        explosion.addSensorListener(new SensorListener() {
            @Override
            public void beginContact(SensorEvent sensorBegin) {
                if (sensorBegin.getContactBody() == GameLevel.getMan()) {
                    if (Game.getLevel() instanceof GameLevel6 && BombItemPickup.isBombPickedUp()) {
                        GameLevel.getMan().setPosition(new Vec2(25, 12));
                    }

                    if (Game.getLevel() instanceof GameLevel7) {
                        GameLevel.getMan().setPosition(new Vec2(27, 10));
                    }

                    else if (Game.getLevel() instanceof GameLevel8) {
                        GameLevel.getMan().setPosition(new Vec2(27, 4.5f));
                    }

                    else if (Game.getLevel() instanceof GameLevel9) {
                        GameLevel.getMan().setPosition(new Vec2(25, -15.5f));
                    }

                    else if (Game.getLevel() instanceof GameLevel10) {
                        GameLevel.getMan().setPosition(new Vec2(25, -15.5f));
                    }

                    else {
                        GameLevel.getMan().setPosition(new Vec2(-25, -15.5f));
                    }

                    Lives.setLives(Lives.getLives()-1);

                    if (Lives.getLives() == 4) {
                        GameView.setHeart5(GameView.getBlank());

                        // plays the loseLife sound
                        Lives.getLoseLife().play();
                    }

                    else if (Lives.getLives() == 3) {
                        GameView.setHeart4(GameView.getBlank());

                        // plays the loseLife sound
                        Lives.getLoseLife().play();
                    }

                    else if (Lives.getLives() == 2) {
                        GameView.setHeart3(GameView.getBlank());

                        // plays the loseLife sound
                        Lives.getLoseLife().play();
                    }

                    else if (Lives.getLives() == 1) {
                        GameView.setHeart2(GameView.getBlank());

                        // plays the loseLife sound
                        Lives.getLoseLife().play();
                    }

                    else if (Lives.getLives() == 0) {
                        Game.goToNextLevel();
                    }
                }

                if (sensorBegin.getContactBody().equals(GameLevel.getReaper1())) {
                    GameLevel.getReaper1().destroy();
                    Pickup.setPoints(Pickup.getPoints()+1);

                    if (Pickup.getPoints() == 1) {
                        Pickup.setNumber(GameView.getOne());
                    }

                    else if (Pickup.getPoints() == 2) {
                        Pickup.setNumber(GameView.getTwo());
                    }

                    else if (Pickup.getPoints() == 3) {
                        Pickup.setNumber(GameView.getThree());
                    }

                    if (Game.getLevel().isComplete()) {
                        if (!(Game.getLevel() instanceof GameLevel6)) {
                            Pickup.getWinLevel().play();
                        }
                        Game.goToNextLevel();
                    } else {
                        kill.play();
                    }
                }

                if (sensorBegin.getContactBody().equals(GameLevel.getReaper2())) {
                    GameLevel.getReaper2().destroy();
                    Pickup.setPoints(Pickup.getPoints()+1);

                    if (Pickup.getPoints() == 1) {
                        Pickup.setNumber(GameView.getOne());
                    }

                    else if (Pickup.getPoints() == 2) {
                        Pickup.setNumber(GameView.getTwo());
                    }

                    else if (Pickup.getPoints() == 3) {
                        Pickup.setNumber(GameView.getThree());
                    }

                    if (Game.getLevel().isComplete()) {
                        if (!(Game.getLevel() instanceof GameLevel6)) {
                            Pickup.getWinLevel().play();
                        }
                        Game.goToNextLevel();
                    } else {
                        kill.play();
                    }
                }

                if (sensorBegin.getContactBody().equals(GameLevel.getReaper3())) {
                    GameLevel.getReaper3().destroy();
                    Pickup.setPoints(Pickup.getPoints()+1);

                    if (Pickup.getPoints() == 1) {
                        Pickup.setNumber(GameView.getOne());
                    }

                    else if (Pickup.getPoints() == 2) {
                        Pickup.setNumber(GameView.getTwo());
                    }

                    else if (Pickup.getPoints() == 3) {
                        Pickup.setNumber(GameView.getThree());
                    }

                    if (Game.getLevel().isComplete()) {
                        if (!(Game.getLevel() instanceof GameLevel6)) {
                            Pickup.getWinLevel().play();
                        }
                        Game.goToNextLevel();
                    } else {
                        kill.play();
                    }
                }
            }

            @Override
            public void endContact(SensorEvent sensorEnd) {

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Bomb.getExplosion().destroy();
        CharacterController.setBomb(true);
    }
}
