package touhou.players;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxColider;
import bases.physics.PhysicsBody;
import bases.pools.GameObjectPool;
import javafx.scene.shape.Sphere;
import tklibs.SpriteUtils;
import bases.Constraints;
import bases.FrameCounter;
import bases.renderers.ImageRenderer;
import touhou.inputs.InputManager;
import touhou.players.spheres.PlayerSphere;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by huynq on 8/2/17.
 */
public class Player extends GameObject implements PhysicsBody{
    private static final int SPEED = 5;
    private InputManager inputManager;
    private Constraints constraints;
    private BoxColider boxColider;


    private FrameCounter coolDownCounter;
    private boolean spellLock;

    public Player() {
        super();
        this.boxColider = new BoxColider(10,10);
        this.children.add(boxColider);
        this.spellLock = false;
        BufferedImage image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        this.renderer = new ImageRenderer(image);
        this.coolDownCounter = new FrameCounter(3);
        addSpheres();
    }

    private void addSpheres() {
        PlayerSphere leftSphere = new PlayerSphere();
        leftSphere.getPosition().set(-20, 0);

        PlayerSphere righSphere = new PlayerSphere();
        righSphere.getPosition().set(20, 0);
        righSphere.setReverse(true);


        this.children.add(leftSphere);
        this.children.add(righSphere);
    }

    public void setContraints(Constraints contraints) {
        this.constraints = contraints;
    }


    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (inputManager.upPressed)
            position.addUp(0, -SPEED);
        if (inputManager.downPressed)
            position.addUp(0, SPEED);
        if (inputManager.leftPressed)
            position.addUp(-SPEED, 0);
        if (inputManager.rightPressed)
            position.addUp(SPEED, 0);

        if (constraints != null) {
            constraints.make(position);
        }

        castSpell();
    }

    private void castSpell() {
        if (inputManager.xPressed && !spellLock) {
            PlayerSpell newSpell = GameObjectPool.recycle(PlayerSpell.class);
            newSpell.getPosition().set(this.position.add(0, -30));
            spellLock = true;
            coolDownCounter.reset();
        }

        unlockSpell();
    }

    private void unlockSpell() {
        if (spellLock) {
            if (coolDownCounter.run()) {
                spellLock = false;
            }
        }
    }


    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    @Override
    public BoxColider getBoxColider() {
        return boxColider;
    }
}
