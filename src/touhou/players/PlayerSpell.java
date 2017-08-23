package touhou.players;

import bases.FrameCounter;
import bases.GameObject;
import bases.physics.BoxColider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import touhou.enemies.Enemy;

/**
 * Created by huynq on 8/2/17.
 */
public class PlayerSpell extends GameObject implements PhysicsBody {
    private BoxColider boxColider;
    private int damage = 1;

    public PlayerSpell() {
        super();
        renderer = new Animation(
                10,
                false,
                false,
                SpriteUtils.loadImage("assets/images/player-spells/a/0.png"),
                SpriteUtils.loadImage("assets/images/player-spells/a/1.png"),
                SpriteUtils.loadImage("assets/images/player-spells/a/2.png"),
                SpriteUtils.loadImage("assets/images/player-spells/a/3.png")
        );
        boxColider = new BoxColider(20, 20);
        this.children.add(boxColider);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.addUp(0, -10);
        hitEnemy();
        deativeIfNeeded();
    }

    private void deativeIfNeeded() {
        if(this.screenPosition.y < 0)
            this.isActive = false;
    }

    @Override
    public BoxColider getBoxColider() {
        return this.boxColider;
    }

    private void hitEnemy(){
//        Enemy enemy = Physics.colideWith(this.boxColider);
        Enemy enemy = Physics.colideWith(this.boxColider, Enemy.class);
            if(enemy != null){
                enemy.getHit(damage);
                this.isActive = false;
            }
      }

}
