package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.physics.BoxColider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import touhou.players.Player;

import javax.swing.*;
import java.util.Vector;

import java.awt.*;

/**
 * Created by huynq on 8/9/17.
 */
public class Enemy extends GameObject implements PhysicsBody {
    private static final float SPEED = 2;
    private FrameCounter frameCounter;
    private BoxColider boxColider;

    public Enemy() {
        super();
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png"));
        frameCounter = new FrameCounter(30);
        boxColider = new BoxColider(20, 20);
        this.children.add(boxColider);
    }

    // Controller
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
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
        hitPlayer();
    }

    private void hitPlayer() {
        Player player = Physics.colideWithPlayer(this.boxColider);
        if(player != null) {
            player.setActive(false);
            this.isActive = false;
        }
    }

    @Override
    public BoxColider getBoxColider() {
        return this.boxColider;
    }
}
