package touhou.enemies;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxColider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.players.Player;

public class EnemySpell extends GameObject implements PhysicsBody{
    private static final int SPEED = 5;
    private BoxColider boxColider;
    public EnemySpell(){
        super();
        this.boxColider = new BoxColider(20,20);
        this.children.add(boxColider);
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png"));
    }

    public void run(Vector2D parentPosition){
        super.run(parentPosition);
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
        return boxColider;
    }
}
