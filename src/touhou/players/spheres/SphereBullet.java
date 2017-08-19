package touhou.players.spheres;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxColider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.Animation;
import javafx.scene.shape.Sphere;
import tklibs.SpriteUtils;
import touhou.enemies.Enemy;

public class SphereBullet extends GameObject implements PhysicsBody{
    private BoxColider boxColider;

    public SphereBullet(){
        super();
        renderer = new Animation(
                10,
                false,
                SpriteUtils.loadImage("assets/images/sphere-bullets/0.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/1.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/2.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/3.png")
        );
        boxColider = new BoxColider(20,20);
        this.children.add(boxColider);
    }

    @Override
    public void run(Vector2D parenPosition){
        super.run(parenPosition);
        position.addUp(0,-5);
        hitEnemy();
        deactiveIfNeeded();
    }

    private void deactiveIfNeeded() {
        if(this.screenPosition.y < 0 )
            this.isActive = false;
    }

    private void hitEnemy() {
        Enemy enemy = Physics.colideWith(this.boxColider, Enemy.class);
        if(enemy != null){
            enemy.setActive(false);
            this.isActive = false;
        }
    }

    @Override
    public BoxColider getBoxColider() {
        return this.boxColider ;
    }
}
