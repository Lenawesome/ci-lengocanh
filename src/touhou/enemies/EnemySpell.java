package touhou.enemies;

import bases.GameObject;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

public class EnemySpell extends GameObject{
    private static final int SPEED = 5;
    public EnemySpell(){
        super();
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png"));
    }

    public void run(){
        position.addUp(0, SPEED);
    }
}
