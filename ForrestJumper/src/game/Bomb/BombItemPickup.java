package game.Bomb;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import game.Character.CharacterController;
import game.Coins.Pickup;
import game.Game;
import game.GameView;
import game.Levels.GameLevel;
import game.Levels.GameLevel6;
import game.Reaper.GhostlyReaperCollision;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class BombItemPickup implements CollisionListener {
    private static boolean bombPickedUp = false;

    public static boolean isBombPickedUp() {
        return bombPickedUp;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getReportingBody() == GameLevel.getMan() && collisionEvent.getOtherBody() == GameLevel6.getBomb()) {
            GameLevel6.getBomb().destroy();
            Pickup.getPickup().play();
            GameLevel.getCoin3().setPosition(new Vec2(-25, -15.5f));
            GhostlyReaperCollision.setRespawn(new Vec2 (25, -15.5f));
            Game.getJungleSound().stop();
            try {
                Game.setJungleSound(new SoundClip("data/sounds/rock.wav"));
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println(e);
            }
            Game.getJungleSound().setVolume(0.3);
            Game.getJungleSound().loop();
            bombPickedUp = true;
            CharacterController.setBomb(true);
            GameView.setBackground(new ImageIcon("data/jungle6R.png").getImage());
            Game.getFrame().repaint();
        }
    }
}
