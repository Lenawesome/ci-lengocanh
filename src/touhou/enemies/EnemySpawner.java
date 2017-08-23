package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.pools.GameObjectPool;
import touhou.scenes.Background;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 * Created by huynq on 8/9/17.
 */
public class EnemySpawner extends GameObject{
    private FrameCounter spawnCounter;
    private Random random;
    private Background background;

    public EnemySpawner() {
        spawnCounter = new FrameCounter(70);
        random = new Random();
    }

    @Override
    public void run(Vector2D parenPosition) {
        super.run(parenPosition);
        if(!background.isStopped()){
            spawnBlue();
        }
        else{
            spawnBlack();
        }
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public void spawnBlue() {
        if(spawnCounter.run()) {
                spawnCounter.reset();
                Enemy enemy = GameObjectPool.recycle(Enemy.class);
                enemy.getPosition().set(random.nextInt(384),0);
        }
    }
    public void spawnBlack(){
        Enemy enemy = new Enemy(0);
        enemy.getPosition().set(256,60);
        GameObject.add(enemy);
    }
}
