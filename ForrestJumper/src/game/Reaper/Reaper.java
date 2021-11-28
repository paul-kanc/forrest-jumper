package game.Reaper;

import city.cs.engine.*;

public class Reaper extends Walker {

    public Reaper(World w) {
        super (w);

        Shape bodyShape = new PolygonShape(0.06f,1.105f, 1.275f,0.8f, 1.275f,-1.245f, -1.26f,-1.245f, -1.26f,0.76f);
        SolidFixture bodyFixture = new SolidFixture(this, bodyShape, 999);
        addImage(new BodyImage("data/reaper.png", 2.5f));
    }
}