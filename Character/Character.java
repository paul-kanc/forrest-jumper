package game.Character;

import city.cs.engine.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Character extends Walker {

    private int points;

    private static final Shape manShape = new PolygonShape(
            0.021f,1.004f, 0.817f,0.684f, 0.817f,-0.984f, -0.811f,-0.984f, -0.811f,0.684f
    );

    private static SoundClip jump;

    public Character(World world) {
        super(world, manShape);
    }

    static {
        // create a jump sound
        try {
            jump = new SoundClip("data/sounds/jump.wav");
            jump.setVolume(0.1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    @Override
    public void jump(float speed) {
        jump.play();
        super.jump(speed);
    }
}