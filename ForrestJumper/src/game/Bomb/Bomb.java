package game.Bomb;

import city.cs.engine.BodyImage;
import city.cs.engine.CircleShape;
import city.cs.engine.DynamicBody;
import game.Game;
import game.Levels.GameLevel;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bomb implements ActionListener {
    private static DynamicBody bomb;
    private static Explosion explosion;
    public Timer t;

    public static DynamicBody getBomb() {
        return bomb;
    }

    public static Explosion getExplosion() {
        return explosion;
    }

    public Bomb(GameLevel world, Vec2 pos, int time) {
        bomb = new DynamicBody(world, new CircleShape(0.5f));
        bomb.addImage(new BodyImage("data/bomb.png", 2));
        bomb.setPosition(pos);

        t = new Timer(time, this);
        t.start();
        t.setRepeats(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bomb.destroy();

        explosion = new Explosion(Game.getLevel());
    }
}
