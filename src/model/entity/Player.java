package model.entity;

import controller.ImageLoader;
import controller.KeyHandler;
import view.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    KeyHandler keyHandler;
    ImageLoader imageLoader;

    public final int screenX;
    public final int screenY;

    public final int numImage = 72;
    public final int imageWidth = 128;
    public final int imageHeight = 128;

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
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        //Define the start screen's andres
        worldX = gp.titleSize * 20;
        worldY = gp.titleSize * (gp.maxWorldRow - 11);
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

        imageLoader = new ImageLoader(gp, "/player/playerSprites.png", numImage, imageWidth, imageHeight);
        imageLoader.PlayerImageLoader(this);

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
            if (spriteCounter - spriteDistance > 1) {
                if (spriteNum < 12) {
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
                        image = up[6];
                        break;
                    case 2:
                        image = up[7];
                        break;
                    case 3:
                        image = up[8];
                        break;
                    case 4:
                        image = up[9];
                        break;
                    case 5:
                        image = up[10];
                        break;
                    case 6:
                        image = up[11];
                        break;
                    case 7:
                        image = up[12];
                        break;
                    case 8:
                        image = up[13];
                        break;
                    case 9:
                        image = up[14];
                        break;
                    case 10:
                        image = up[15];
                        break;
                    case 11:
                        image = up[16];
                        break;
                    case 12:
                        image = up[17];
                        break;
                }
                break;
            case "down":
                switch (spriteNum) {

                    case 1:
                        image = down[6];
                        break;
                    case 2:
                        image = down[7];
                        break;
                    case 3:
                        image = down[8];
                        break;
                    case 4:
                        image = down[9];
                        break;
                    case 5:
                        image = down[10];
                        break;
                    case 6:
                        image = down[11];
                        break;
                    case 7:
                        image = down[12];
                        break;
                    case 8:
                        image = down[13];
                        break;
                    case 9:
                        image = down[14];
                        break;
                    case 10:
                        image = down[15];
                        break;
                    case 11:
                        image = down[16];
                        break;
                    case 12:
                        image = down[17];
                        break;
                }
                break;
            case "left":
                switch (spriteNum) {

                    case 1:
                        image = left[6];
                        break;
                    case 2:
                        image = left[7];
                        break;
                    case 3:
                        image = left[8];
                        break;
                    case 4:
                        image = left[9];
                        break;
                    case 5:
                        image = left[10];
                        break;
                    case 6:
                        image = left[11];
                        break;
                    case 7:
                        image = left[12];
                        break;
                    case 8:
                        image = left[13];
                        break;
                    case 9:
                        image = left[14];
                        break;
                    case 10:
                        image = left[15];
                        break;
                    case 11:
                        image = left[16];
                        break;
                    case 12:
                        image = left[17];
                        break;
                }
                break;
            case "right":
                switch (spriteNum) {

                    case 1:
                        image = right[6];
                        break;
                    case 2:
                        image = right[7];
                        break;
                    case 3:
                        image = right[8];
                        break;
                    case 4:
                        image = right[9];
                        break;
                    case 5:
                        image = right[10];
                        break;
                    case 6:
                        image = right[11];
                        break;
                    case 7:
                        image = right[12];
                        break;
                    case 8:
                        image = right[13];
                        break;
                    case 9:
                        image = right[14];
                        break;
                    case 10:
                        image = right[15];
                        break;
                    case 11:
                        image = right[16];
                        break;
                    case 12:
                        image = right[17];
                        break;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);

    }
}
