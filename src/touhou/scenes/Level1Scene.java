package touhou.scenes;

import bases.Constraints;
import bases.GameObject;
import touhou.enemies.EnemySpawner;
import touhou.inputs.InputManager;
import touhou.players.Player;
import touhou.settings.Settings;



public class Level1Scene {
    Player player = new Player();
    EnemySpawner enemySpawner = new EnemySpawner();
    Settings settings = Settings.instance;
    Background background = new Background();

    public void init(){
        addBackground();
        addPlayer();
        enemySpawn();
    }



    private void addPlayer() {
        player.setInputManager(InputManager.instance);
        player.setContraints(new Constraints(
                settings.getWindowInsets().top,
                settings.getGamePlayHeight(),
                settings.getWindowInsets().left,
                settings.getGamePlayWidth())
               );
        player.getPosition().set(settings.getGamePlayWidth() / 2, settings.getGamePlayHeight() * 3 / 4);

        GameObject.add(player);
    }

    private void addBackground() {
        GameObject.add(background);
        enemySpawner.setBackground(background);
    }
    private void enemySpawn() {
        GameObject.add(enemySpawner);
    }
}
