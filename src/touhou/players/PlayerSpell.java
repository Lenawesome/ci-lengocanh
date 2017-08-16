package touhou.players;

import bases.GameObject;
import bases.physics.BoxColider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import touhou.enemies.Enemy;

import java.awt.*;

/**
 * Created by huynq on 8/2/17.
 */
public class PlayerSpell extends GameObject implements PhysicsBody {
    private BoxColider boxColider;

    public PlayerSpell() {
        super();
        this.renderer = new ImageRenderer(SpriteUtils.loadImage(
                "assets/images/player-spells/a/0.png"
        ));
        boxColider = new BoxColider(20, 20);
        this.children.add(boxColider);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.addUp(0, -10);
        hitEnemy();
    }

    @Override
    public BoxColider getBoxColider() {
        return this.boxColider;
    }

    private void hitEnemy(){
        Enemy enemy = Physics.colideWithEnemy(this.boxColider);
            if(enemy != null){
                enemy.setActive(false);
                this.isActive = false;
            }
      }
}
