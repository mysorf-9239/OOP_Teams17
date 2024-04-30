package model.entity;

import view.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GamePanel gp;

    public int worldX, worldY;
    public int speed;

    private static final int NUM_FRAMES = 20;

    public BufferedImage[] up = new BufferedImage[NUM_FRAMES];
    public BufferedImage[] down = new BufferedImage[NUM_FRAMES];
    public BufferedImage[] left = new BufferedImage[NUM_FRAMES];
    public BufferedImage[] right = new BufferedImage[NUM_FRAMES];

    public String direction;

    public int spriteConuter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collidisionOn = false;

    //Character status
    public int maxLife;
    public int life;

    public Entity (GamePanel gp) {

        this.gp = gp;
    }
}
