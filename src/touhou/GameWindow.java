package touhou;

import bases.GameObject;
import touhou.inputs.InputManager;
import touhou.scenes.Level1Scene;
import touhou.settings.Settings;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

//https://github.com/qhuydtvt/ci1-huynq

/**
 * Created by huynq on 7/29/17.
 */
public class GameWindow extends Frame {

    private long lastTimeUpdate;
    private long currentTime;
    private Graphics2D windowGraphics;

    private BufferedImage blackBackground;

    private BufferedImage backbufferImage;
    private Graphics2D backbufferGraphics;
    private Level1Scene level1Scene;


    InputManager inputManager = InputManager.instance;

    public GameWindow() {
        pack();
        setupGameLoop();
        setupWindow();
        setupLevel();
    }

    private void setupLevel() {
        level1Scene = new Level1Scene();
        level1Scene.init();
    }







    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setSize(1024, 768);

        this.setTitle("Touhou - Remade by QHuyDTVT");
        this.setVisible(true);

        this.backbufferImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.backbufferGraphics = (Graphics2D) this.backbufferImage.getGraphics();

        this.blackBackground = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);


        this.windowGraphics = (Graphics2D) this.getGraphics();
        Graphics2D backgroundGraphics = (Graphics2D) this.blackBackground.getGraphics();
        backgroundGraphics.setColor(Color.BLACK);
        backgroundGraphics.fillRect(0,0,this.getWidth(),this.getHeight());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyReleased(e);
            }
        });
        Settings.instance.setWindowInsets(this.getInsets());
    }

    public void loop() {
        while(true) {
            if (lastTimeUpdate == -1) {
                lastTimeUpdate = System.nanoTime();
            }
            currentTime = System.nanoTime();
            if (currentTime - lastTimeUpdate > 17000000) {
                run();
                render();
                lastTimeUpdate = currentTime;
            }
        }
    }

    private void run() {
        GameObject.runAll();
    }


    private void render() {
//        backbufferGraphics.setColor(Color.black);
        backbufferGraphics.drawImage(blackBackground,0,0,null);
//        backbufferGraphics.fillRect(0, 0, 1024, 768);

        GameObject.renderAll(backbufferGraphics);
        getGraphics().drawImage(backbufferImage,0,0,null);
    }
}
