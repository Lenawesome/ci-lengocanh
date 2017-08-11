package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import java.util.Vector;

import java.awt.*;

/**
 * Created by huynq on 8/9/17.
 */
public class Enemy extends GameObject {
    private static final float SPEED = 2;
    private FrameCounter frameCounter;

    public Enemy() {
        super();
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png"));
        frameCounter = new FrameCounter(30);
    }

    // Controller
    public void run() {
        super.run();
        fly();
        shoot();
    }

    private void shoot() {
        if(frameCounter.run()) {
            frameCounter.reset();
            EnemySpell newSpell = new EnemySpell();
            newSpell.getPosition().set(this.position.add(0, 20));
            GameObject.add(newSpell);
        }
    }

    private void fly() {
        position.addUp(0, SPEED);
    }
}
