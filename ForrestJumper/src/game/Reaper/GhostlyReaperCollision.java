package game.Reaper;

import city.cs.engine.Body;
import city.cs.engine.Sensor;
import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import game.Bomb.Bomb;
import game.Game;
import game.GameView;
import game.Heart.Lives;
import org.jbox2d.common.Vec2;

public class GhostlyReaperCollision implements SensorListener {
    private final Sensor ghostlyReaper;
    private final Body man;
    private static Vec2 respawn = new Vec2(-25, -15.5f);

    public static void setRespawn(Vec2 respawn) {
        GhostlyReaperCollision.respawn = respawn;
    }

    public GhostlyReaperCollision(Sensor ghostlyReaper, Body man) {
        this.ghostlyReaper = ghostlyReaper;
        this.man = man;
    }

    @Override
    public void beginContact(SensorEvent sensorBegin) {
        if ((sensorBegin.getSensor().equals(ghostlyReaper) || sensorBegin.getSensor().equals(Bomb.getExplosion())) && sensorBegin.getContactBody().equals(man)) {
            man.setPosition(respawn);
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
    }

    @Override
    public void endContact(SensorEvent sensorEnd) {
    }
}
