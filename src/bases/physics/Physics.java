package bases.physics;

import bases.Vector2D;
import touhou.enemies.Enemy;
import touhou.players.Player;

import java.util.Vector;

public class Physics {
    private static Vector<PhysicsBody> bodies = new Vector<>();

    public static <T extends PhysicsBody> T colideWith(BoxColider boxColider, Class <T> classz){
        for(PhysicsBody body : bodies){
            if(body.isActive())
                 if(body.getClass().equals(classz) && body.getBoxColider().intersects(boxColider) )
                     return (T) body;
        }
        return null;
    }


    public static void add(PhysicsBody body) {
        bodies.add(body);
    }

    public static void clearAll() {
        bodies.clear();
    }
}
