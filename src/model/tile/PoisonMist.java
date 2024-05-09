package model.tile;

import view.GamePanel;

import java.awt.*;

public class PoisonMist {

    GamePanel gp;

    public int PoisonMistY;
    public int PoisonMistSpeed;
    private int defaultPoisonMistY;
    private int defaultPoisonMistSpeed;

    private static long poisonSpeedTime = System.currentTimeMillis();
    private static final long SPEED_INTERVAL = 10000;


    public PoisonMist(GamePanel gp) {

        this.gp = gp;

        setDefaultPoisonMist();

    }

    public void setDefaultPoisonMist() {

        defaultPoisonMistY = gp.maxWorldRow*gp.titleSize + gp.titleSize;
        defaultPoisonMistSpeed = 1;
        PoisonMistY = defaultPoisonMistY;
        PoisonMistSpeed = defaultPoisonMistSpeed;

    }
    public void changeSpeed(){
        long currentTime = System.currentTimeMillis();

        if (PoisonMistSpeed < 10 && currentTime - poisonSpeedTime >= SPEED_INTERVAL) {
            PoisonMistSpeed += 1;
            poisonSpeedTime = currentTime;
        }
    }
    public void update() {

        PoisonMistY -= PoisonMistSpeed;
        changeSpeed();
    }

    public void draw(Graphics2D g2) {

        int drawX = 0;
        int drawY = gp.screenHeight/2 - (gp.player.worldY - PoisonMistY);
        int PoisonMistWidth = gp.maxScreenCol*gp.titleSize;
        int PoisonMistHeight = defaultPoisonMistY - PoisonMistY;

        g2.setColor(Color.BLACK);
        g2.setColor(new Color(120, 120, 200, 150));

        g2.fillRect(drawX, drawY, PoisonMistWidth, PoisonMistHeight);
        g2.setFont(g2.getFont().deriveFont(26F));
        g2.setColor(Color.white);
        g2.drawString("drawY: " + drawY, 50, 600);
    }
}
