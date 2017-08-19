package touhou.players.spheres;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxColider;
import bases.physics.PhysicsBody;
import bases.pools.GameObjectPool;
import bases.renderers.Animation;
import tklibs.SpriteUtils;

public class PlayerSphere extends GameObject implements PhysicsBody {
    Animation animation;
    FrameCounter coolDown;
    public PlayerSphere(){
        super();
        this.animation = new Animation(
                7,
                false,
                SpriteUtils.loadImage("assets/images/sphere/0.png"),
                SpriteUtils.loadImage("assets/images/sphere/1.png"),
                SpriteUtils.loadImage("assets/images/sphere/2.png"),
                SpriteUtils.loadImage("assets/images/sphere/3.png")
                );

        this.renderer = animation;
        coolDown = new FrameCounter(30);
    }

    public void setReverse(boolean reverse){
        this.animation.setReverse(reverse);
    }

    @Override
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        shoot();
    }

    private void shoot() {
        if(coolDown.run()) {
            SphereBullet newBullet = GameObjectPool.recycle(SphereBullet.class);
            newBullet.getPosition().set(this.screenPosition.add(0, 10));
            coolDown.reset();
        }
    }

    @Override
    public BoxColider getBoxColider() {
        return null;
    }
}
