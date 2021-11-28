package game.Crate;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Reaper.Reaper;

public class ReaperDestroyCrate implements CollisionListener {

    private final Reaper reaper;

    public ReaperDestroyCrate(Reaper reaper) {
        this.reaper = reaper;
    }

    @Override
    public void collide(CollisionEvent e) {

        if (e.getOtherBody() == reaper) {
            e.getReportingBody().destroy();
        }
    }
}