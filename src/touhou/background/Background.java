package touhou.background;

import bases.GameObject;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.Vector;

public class Background extends GameObject{

    public Background(){
        position = new Vector2D();
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/background/0.png"));
    }

    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        if(position.y < 3109 / 2)
            position.addUp(0, 3);
    }
}
