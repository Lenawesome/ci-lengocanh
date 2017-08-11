package touhou.players;

import bases.GameObject;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;

import java.awt.*;

/**
 * Created by huynq on 8/2/17.
 */
public class PlayerSpell extends GameObject {
    public PlayerSpell() {
        super();
        this.renderer = new ImageRenderer(SpriteUtils.loadImage(
                "assets/images/player-spells/a/0.png"
        ));
    }


    public void run() {
        position.addUp(0, -10);
    }
}
