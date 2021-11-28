package game.Reaper;

import city.cs.engine.*;
import game.Levels.GameLevel;
import game.Levels.GameLevel6;
import org.jbox2d.common.Vec2;

public class GhostlyReaperSensor extends Walker implements StepListener {

    private static Sensor ghostlyReaper;
    private final BodyImage left = new BodyImage("data/ghostlyReaperL.png", 2.5f), right = new BodyImage("data/ghostlyReaperR.png", 2.5f);

    public static Sensor getGhostlyReaper() {
        return ghostlyReaper;
    }

    private enum State {
        UP, RIGHT, DOWN, LEFT, STILLX, STILLY
    }

    private GhostlyReaperSensor.State stateX, stateY;

    public GhostlyReaperSensor(World w) {
        super (w);

        ghostlyReaper = new Sensor(this, new PolygonShape(0.06f,1.105f, 1.275f,0.8f, 1.275f,-1.245f, -1.26f,-1.245f, -1.26f,0.76f));
        addImage(left);
        getWorld().addStepListener(this);
    }

    public boolean up() {
        Body man = GameLevel.getMan();
        float gap = getPosition().y - man.getPosition().y;
        return gap < 0;
    }

    public boolean right() {
        Body man = GameLevel.getMan();
        float gap = getPosition().x - man.getPosition().x;
        return gap < 0;
    }

    public boolean down() {
        Body man = GameLevel.getMan();
        float gap = getPosition().y - man.getPosition().y;
        return gap > 0;
    }

    public boolean left() {
        Body man = GameLevel.getMan();
        float gap = getPosition().x - man.getPosition().x;
        return gap > 0;
    }

    public boolean stillX() {
        Body man = GameLevel.getMan();
        float gapX = getPosition().x - man.getPosition().x;
        return gapX < 1 && gapX > -1;
    }

    public boolean stillY() {
        Body man = GameLevel.getMan();
        float gapY = getPosition().y - man.getPosition().y;
        return gapY < 1 && gapY > -1;
    }

    // update state
    public void preStep(StepEvent e) {
        if (up()) {
            if (stateY != GhostlyReaperSensor.State.UP) {
                stateY = GhostlyReaperSensor.State.UP;
            }
        }

        if (right()) {
            if (stateX != GhostlyReaperSensor.State.RIGHT) {
                stateX = GhostlyReaperSensor.State.RIGHT;
            }
        }

        if (down()) {
            if (stateY != GhostlyReaperSensor.State.DOWN) {
                stateY = GhostlyReaperSensor.State.DOWN;
            }
        }

        if (left()) {
            if (stateX != GhostlyReaperSensor.State.LEFT) {
                stateX = GhostlyReaperSensor.State.LEFT;
            }
        }

        if (stillX()) {
            if (stateX != GhostlyReaperSensor.State.STILLX) {
                stateX = GhostlyReaperSensor.State.STILLX;
            }
        }

        if (stillY()) {
            if (stateY != GhostlyReaperSensor.State.STILLY) {
                stateY = GhostlyReaperSensor.State.STILLY;
            }
        }

        refreshRoll();
    }

    private void refreshRoll() {
        switch (stateY) {
            case UP:
                setLinearVelocity(new Vec2(0, 3));
                break;
            case DOWN:
                setLinearVelocity(new Vec2(0, -3));
                break;
            case STILLY:
                setLinearVelocity(new Vec2(0, 0));
                break;
            default:
        }

        switch (stateX) {
            case RIGHT:
                startWalking(3);
                GameLevel6.getGhostlyReaperSensor().removeAllImages();
                GameLevel6.getGhostlyReaperSensor().addImage(right);
                break;
            case LEFT:
                startWalking(-3);
                GameLevel6.getGhostlyReaperSensor().removeAllImages();
                GameLevel6.getGhostlyReaperSensor().addImage(left);
                break;
            case STILLX:
                startWalking(0);
                break;
            default:
        }
    }

    public void postStep(StepEvent e) {
    }
}
