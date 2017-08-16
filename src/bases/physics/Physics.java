package bases.physics;

import bases.Vector2D;
import touhou.enemies.Enemy;
import touhou.players.Player;

import java.util.Vector;

public class Physics {
    private static Vector<PhysicsBody> bodies = new Vector<>();

    public static Enemy colideWithEnemy(BoxColider boxColider){
        for(PhysicsBody body : bodies){
            if(body.isActive())
                 if(body instanceof Enemy && body.getBoxColider().intersects(boxColider) )
                     return (Enemy) body;
        }
        return null;
    }


    public static Player colideWithPlayer(BoxColider boxColider){
        for(PhysicsBody body : bodies){
            if(body.isActive())
                if(body instanceof Player && body.getBoxColider().intersects(boxColider))
                    return (Player) body;
        }
        return null;
    }

    public static void add(PhysicsBody body) {
        bodies.add(body);
    }
}
