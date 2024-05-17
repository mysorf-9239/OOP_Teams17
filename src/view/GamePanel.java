package view;

import controller.CollisionChecker;
import controller.Config;
import controller.EventHandler;
import controller.KeyHandler;
import model.Object.AssetSetter;
import model.entity.Entity;
import model.entity.Player;
import model.tile.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    //Screen setting
    final int originalTitleSize = 16;
    final int scale = 3;
    public final int titleSize = originalTitleSize * scale;

    //Change size of frame there
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 20;
    public final int screenWidth = titleSize * maxScreenCol;           //48*20 = 960px
    public final int screenHeight = titleSize * maxScreenRow;          //48*20 = 960px
    public static int[][][] map;

    //World setting
    public final int maxMap = 50;
    public static int currentMap = 0;
    public static final int maxWorldCol = 40;
    public static final int maxWorldRow = 500;
    public static int currentWorldCol = maxWorldCol;
    public static int currentWorldRow = maxWorldRow;

    //Player is moved
    //public static boolean isMove = false;

    //FPS
    int FPS = 60;

    //System
    public TileManager tileManager = new TileManager(this);
    public KeyHandler keyHandler = new KeyHandler(this);
    public EventHandler eventHandler = new EventHandler(this);
    public Sound music = new Sound();
    public Sound se = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public Config config = new Config(this);
    Thread gameThread;


    //Entity and Object
    public Player player = new Player(this, keyHandler);
    public Entity[] obj = new Entity[10];
    public ArrayList<Entity> entitiesList = new ArrayList<>();
    public PoisonMist poisonMist = new PoisonMist(this);

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int optionState = 2;
    public final int gameOverState = 3;
    public final int winState = 4;

    //Mode
    public int gameMode;
    public final int endlessMode = 0;
    public final int overcomeMode = 1;

    //Score
    public static int totalScore = 0;
    public static final int TIME_REWARD = 1;
    public static final int DISTANCE_REWARD = 10;


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupObject() {
        assetSetter.setObject();
    }

    public void deletedObject() {
        for (int i = 0; i < obj.length; i++) {
            obj[i] = null;
        }
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
            if (gameMode == 0) {
                poisonMist.update();
            }
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        long drawStart = System.nanoTime();
        if (keyHandler.showDebugText == true) {
            drawStart = System.nanoTime();
        }

        //TITLE SCREEN
        if (gameState == titleState) { ui.draw(g2);}
        //Other
        else {
            //Tile Map
            tileManager.draw(g2);

            //Add Entity (Player, object)
            entitiesList.add(player);
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) { entitiesList.add(obj[i]);}
            }

            //Sort
            Collections.sort(entitiesList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            //Draw
            for (int i = 0; i < entitiesList.size(); i++) { entitiesList.get(i).draw(g2);}

            //Empty entitiesList
            entitiesList.clear();

            if (gameMode == 0) {
                //Lava
                poisonMist.draw(g2);
                if (gameState == playState) {
                    //Score up
                    totalScore += TIME_REWARD;
                }
            } else if (gameMode != 0) {
                poisonMist.stop();
            }


            //UI
            ui.draw(g2);

            //Debug
            if (keyHandler.showDebugText == true) {
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;

                g2.setFont(new Font("Arial", Font.PLAIN, 20));
                g2.setColor(Color.white);
                int x = 10;
                int y = 400;
                int lineHeight = 20;

                g2.drawString("WorldX: " + player.worldX, x, y); y += lineHeight;
                g2.drawString("WorldY: " + player.worldY, x, y); y += lineHeight;
                g2.drawString("Col: " + (player.worldX+player.solidArea.x)/titleSize, x, y); y += lineHeight;
                g2.drawString("Row: " + (player.worldY+player.solidArea.y)/titleSize, x, y); y += lineHeight;
                g2.drawString("DrawTime: " + passed, x, y); y += lineHeight;
                g2.drawString("PoisonMistY: " + poisonMist.PoisonMistY, x, y); y += lineHeight;
                g2.drawString("PoisonSpeed: " + poisonMist.PoisonMistSpeed, x, y); y += lineHeight;
                g2.drawString("FurthestY: " + player.furthestY, x, y);
            }

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

    public void newGame() {

        //Player
        player.setDefaultPositions();
        player.furthestY = player.worldY;
        totalScore = 0;
        player.hasAxe = 1;

        //Tile
        tileManager.loadMap(currentMap);

        //Object
        setupObject();

        //PoisonMist
        poisonMist.setDefaultPoisonMist();
    }

    public void loadGame() {

        //Player
        player.setDefaultPositions();
        player.furthestY = player.worldY;
        totalScore = 0;
        player.hasAxe = 1;

        //Tile
        tileManager.loadMap(currentMap);

        //Object
        setupObject();

        //PoisonMist
        poisonMist.setDefaultPoisonMist();
    }
}
