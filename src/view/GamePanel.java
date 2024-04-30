package view;

import controller.CollisionChecker;
import controller.EventHandler;
import controller.KeyHandler;
import model.Object.AssetSetter;
import model.Object.SuperObject;
import model.entity.Player;
import model.tile.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //Screen setting
    final int originalTitleSize = 16;
    final int scale = 3;
    public final int titleSize = originalTitleSize * scale;
    //16*3 = 48px
    //Change size of frame there
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 20;
    public final int screenWidth = titleSize * maxScreenCol;           //48*20 = 960px
    public final int screenHeight = titleSize * maxScreenRow;          //48*20 = 960px
    public static int[][] map;

    //World setting
    public final int maxWorldCol = 40;
    public final int maxWorldRow = 500;
    public final int worldWidth = titleSize * maxWorldCol;
    public final int worldHeight = titleSize * maxScreenRow;

    //Player is moved
    //public static boolean isMove = false;

    //FPS
    int FPS = 60;

    //System
    public TileEndlessManager tileManager = new TileEndlessManager(this);  //"/map/Maptest.txt"
    public KeyHandler keyHandler = new KeyHandler(this);
    public EventHandler eventHandler = new EventHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //Entity and Object
    public Player player = new Player(this, keyHandler);
    public SuperObject[] obj = new SuperObject[10];


    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupObject() {
        assetSetter.setObject();

        playMusic(0);
        stopMusic();
        gameState = titleState;
    }

    public void StartGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {

        if (gameState == playState) {
            player.update();
            tileManager.updateMap();
        }
        if (gameState == pauseState) {

        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //TITLE SCREEN
        if (gameState == titleState) {
            ui.draw(g2);
        }
        //Other
        else {
            tileManager.draw(g2);
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {

                    obj[i].draw(g2, this);
                }
            }
            player.draw(g2);
            ui.draw(g2);
        }

        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {

        music.stop();
    }

    public void playSE(int i) {

        se.setFile(i);
        se.play();
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame windown = new JFrame();
                windown.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                windown.setResizable(false);
                windown.setTitle("Fugitive");

                TileEndlessManager.path = "/map/Maptest.txt";

                GamePanel gamePanel = new GamePanel();
                windown.add(gamePanel);

                windown.pack();

                windown.setLocationRelativeTo(null);
                windown.setVisible(true);

                gamePanel.setupObject();
                gamePanel.StartGameThread();
            }
        });
    }
}
