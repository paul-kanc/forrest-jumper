package game.Crate;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Heart.Heart;

public class HeartDestroyCrate implements CollisionListener {

    private final Heart heart;

    public HeartDestroyCrate(Heart heart) {
        this.heart = heart;
    }

    @Override
    public void collide(CollisionEvent destroy) {

        if (destroy.getOtherBody() == heart) {
            destroy.getReportingBody().destroy();
        }
    }
}