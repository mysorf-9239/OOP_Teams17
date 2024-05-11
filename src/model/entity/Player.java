package model.entity;

import controller.Config;
import controller.ImageLoader;
import controller.KeyHandler;
import view.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    KeyHandler keyHandler;
    ImageLoader imageLoader;
    Config config = new Config(gp);

    public final int screenX;
    public final int screenY;

    public static int characterNum;

    public final int numImage = 12;
    public final int imageWidth = 32;
    public final int imageHeight = 32;
    public static int imageStartX;
    public static int imageStartY;

    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyHandler)
    {
        super(gp);

        //Main default andres
        screenX = gp.screenWidth/2;
        screenY = gp.screenHeight/2;

        solidArea = new Rectangle();
        solidArea.x = 12;
        solidArea.y = 21;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 24;
        solidArea.height = 27;

        this.keyHandler = keyHandler;
        config.getCharacter();
    }

    public void setDefaultValues() {
        //Define the start screen's andres
        if (gp.currentMap == 0) {
            worldX = gp.titleSize * 20;
            worldY = gp.titleSize * 489;
        } else if (gp.currentMap > 0) {
            worldX = gp.titleSize * 20;
            worldY = gp.titleSize * 29;
        }

        speed = gp.titleSize/5;
        direction = "up";

        //Player life
        maxLife= 6;
        life = maxLife;
    }

    public void setDefaultPositions() {

        worldX = gp.titleSize * 20;
        worldY = gp.titleSize * (gp.maxWorldRow - 11);
        speed = gp.titleSize/5;
        direction = "up";
        life = maxLife;

        keyHandler.upPressed = false;
        keyHandler.downPressed = false;
        keyHandler.leftPressed = false;
        keyHandler.rightPressed = false;
        keyHandler.movingKeyPressed = false;
    }

    public void getPlayerImage() {

        switch (characterNum) {
            case 0:
                imageStartX = 0;
                imageStartY = 0;
                break;
            case 1:
                imageStartX = 3*imageWidth;
                imageStartY = 0;
                break;
            case 2:
                imageStartX = 6*imageWidth;
                imageStartY = 0;
                break;
            case 3:
                imageStartX = 0;
                imageStartY = 4*imageHeight;
                break;
            case 4:
                imageStartX = 3*imageWidth;
                imageStartY = 4*imageHeight;
                break;
            case 5:
                imageStartX = 6*imageWidth;
                imageStartY = 4*imageHeight;
                break;
            case 6:
                imageStartX = 0;
                imageStartY = 8*imageHeight;
                break;
        }

        imageLoader = new ImageLoader(gp, "/player/manyCharacter01.png", numImage, imageWidth, imageHeight);
        imageLoader.PlayerImageLoader(this, imageStartX, imageStartY);
    }

    public void update() {

        gp.eventHandler.poisonMistCheck();

        //Move
        if(keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed == true )
        {
            //GamePanel.isMove = true;
            if (keyHandler.upPressed == true) {
                direction = "up";
            }
            if (keyHandler.downPressed == true) {
                direction = "down";
            }
            if (keyHandler.leftPressed == true) {
                direction = "left";
            }
            if (keyHandler.rightPressed == true) {
                direction = "right";
            }

            //CHECK TILE COLLISION
            collidisionOn = false;
            gp.collisionChecker.CheckTile(this);

            //CHECK OBJ COLLISION
            int objIndex = gp.collisionChecker.CheckObject(this, true);
            pickUpObject(objIndex);

            //CHECK EVENT
            gp.eventHandler.checkEvent();

            //IF COLLISION IS FALSE -> MOVE
            if (collidisionOn == false) {

                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            } else {
                switch (direction) {
                    case "up":
                        KeyHandler.upPressed = false;
                        break;
                    case "down":
                        KeyHandler.downPressed = false;
                        break;
                    case "left":
                        KeyHandler.leftPressed = false;
                        break;
                    case "right":
                        KeyHandler.rightPressed = false;
                        break;
                }
                KeyHandler.movingKeyPressed = false;
            }
            spriteCounter++;
            int spriteDistance = 0;
            if (spriteCounter - spriteDistance > 4) {
                if (spriteNum < 3) {
                    spriteNum++;
                    spriteDistance += 1;
                } else {
                    spriteNum = 1;
                    spriteDistance = 0;
                }
                spriteCounter = 0;
            }
        }

        if (life > maxLife) {
            life = maxLife;
        }
        if (life <= 0) {
            gp.gameState = gp.gameOverState;
            gp.ui.commanNum = -1;
            gp.stopMusic();
            //Play end game music (index = i)
            //gp.playSE(i);
        }
    }

    public void pickUpObject(int i) {

        if (i != 999) {
//            String objectName = gp.obj[i].name;
//
//            switch (objectName) {
//                case "Key":
//                    gp.playSE(1);
//                    hasKey++;
//                    gp.obj[i] = null;
//                    gp.ui.showMess("You got a key");
//                    break;
//                case "Door":
//                    if (hasKey > 0) {
//                        gp.playSE(3);
//                        hasKey--;
//                        gp.obj[i] = null;
//                        gp.ui.showMess("You opened the door");
//                    } else {
//                        gp.ui.showMess("You need a key to unlock!");
//                    }
//                    break;
//                case "Boots":
//                    gp.playSE(2);
//                    speed += 2;
//                    gp.obj[i] = null;
//                    gp.ui.showMess("Speed up");
//                    break;
//                case "Chest"://
//                    gp.ui.gameFnished = true;
//                    gp.stopMusic();
//                    gp.playSE(4);
//                    break;
            }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                switch (spriteNum) {
                    case 1:
                        image = up[0];
                        break;
                    case 2:
                        image = up[1];
                        break;
                    case 3:
                        image = up[2];
                        break;
                }
                break;
            case "down":
                switch (spriteNum) {

                    case 1:
                        image = down[0];
                        break;
                    case 2:
                        image = down[1];
                        break;
                    case 3:
                        image = down[2];
                        break;
                }
                break;
            case "left":
                switch (spriteNum) {

                    case 1:
                        image = left[0];
                        break;
                    case 2:
                        image = left[1];
                        break;
                    case 3:
                        image = left[2];
                        break;
                }
                break;
            case "right":
                switch (spriteNum) {

                    case 1:
                        image = right[0];
                        break;
                    case 2:
                        image = right[1];
                        break;
                    case 3:
                        image = right[2];
                        break;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);

    }
}
