package bases.pools;

import bases.GameObject;
import bases.Vector2D;
import touhou.players.Player;
import touhou.players.PlayerSpell;

import java.util.Vector;

public class GameObjectPool {
    private static Vector<GameObject> pool = new Vector();

    public static <T extends GameObject> T recycle(Class<T> classz){
        for(GameObject gameObject : pool){
            if(gameObject.getClass().equals(classz)){
                if(!gameObject.isActive()){
                    gameObject.reset();
                    return (T) gameObject;
                }
            }
        }
        T newGameObject = null;
        try {
            newGameObject = classz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        GameObject.add(newGameObject);
        pool.add(newGameObject);
        return newGameObject;
    }

    public static void clearAll() {
        pool.clear();
    }
}
