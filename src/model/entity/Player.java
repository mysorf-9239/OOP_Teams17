package model.entity;

import controller.KeyHandler;
import view.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

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

        up[1] = setup("/player/up01");
        up[2] = setup("/player/up02");
        up[3] = setup("/player/up03");
        up[4] = setup("/player/up04");
        up[5] = setup("/player/up05");
        up[6] = setup("/player/up06");
        up[7] = setup("/player/up07");
        up[8] = setup("/player/up08");
        up[9] = setup("/player/up09");
        up[10] = setup("/player/up10");
        up[11] = setup("/player/up11");
        up[12] = setup("/player/up12");
        up[13] = setup("/player/up13");
        up[14] = setup("/player/up14");
        up[15] = setup("/player/up15");
        up[16] = setup("/player/up16");
        up[17] = setup("/player/up17");
        up[18] = setup("/player/up18");

        left[1] = setup("/player/left01");
        left[2] = setup("/player/left02");
        left[3] = setup("/player/left03");
        left[4] = setup("/player/left04");
        left[5] = setup("/player/left05");
        left[6] = setup("/player/left06");
        left[7] = setup("/player/left07");
        left[8] = setup("/player/left08");
        left[9] = setup("/player/left09");
        left[10] = setup("/player/left10");
        left[11] = setup("/player/left11");
        left[12] = setup("/player/left12");
        left[13] = setup("/player/left13");
        left[14] = setup("/player/left14");
        left[15] = setup("/player/left15");
        left[16] = setup("/player/left16");
        left[17] = setup("/player/left17");
        left[18] = setup("/player/left18");


        down[1] = setup("/player/down01");
        down[2] = setup("/player/down02");
        down[3] = setup("/player/down03");
        down[4] = setup("/player/down04");
        down[5] = setup("/player/down05");
        down[6] = setup("/player/down06");
        down[7] = setup("/player/down07");
        down[8] = setup("/player/down08");
        down[9] = setup("/player/down09");
        down[10] = setup("/player/down10");
        down[11] = setup("/player/down11");
        down[12] = setup("/player/down12");
        down[13] = setup("/player/down13");
        down[14] = setup("/player/down14");
        down[15] = setup("/player/down15");
        down[16] = setup("/player/down16");
        down[17] = setup("/player/down17");
        down[18] = setup("/player/down18");

        right[1] = setup("/player/right01");
        right[2] = setup("/player/right02");
        right[3] = setup("/player/right03");
        right[4] = setup("/player/right04");
        right[5] = setup("/player/right05");
        right[6] = setup("/player/right06");
        right[7] = setup("/player/right07");
        right[8] = setup("/player/right08");
        right[9] = setup("/player/right09");
        right[10] = setup("/player/right10");
        right[11] = setup("/player/right11");
        right[12] = setup("/player/right12");
        right[13] = setup("/player/right13");
        right[14] = setup("/player/right14");
        right[15] = setup("/player/right15");
        right[16] = setup("/player/right16");
        right[17] = setup("/player/right17");
        right[18] = setup("/player/right18");

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
                        image = up[7];
                        break;
                    case 2:
                        image = up[8];
                        break;
                    case 3:
                        image = up[9];
                        break;
                    case 4:
                        image = up[10];
                        break;
                    case 5:
                        image = up[11];
                        break;
                    case 6:
                        image = up[12];
                        break;
                    case 7:
                        image = up[13];
                        break;
                    case 8:
                        image = up[14];
                        break;
                    case 9:
                        image = up[15];
                        break;
                    case 10:
                        image = up[16];
                        break;
                    case 11:
                        image = up[17];
                        break;
                    case 12:
                        image = up[18];
                        break;
                }
                break;
            case "down":
                switch (spriteNum) {

                    case 1:
                        image = down[7];
                        break;
                    case 2:
                        image = down[8];
                        break;
                    case 3:
                        image = down[9];
                        break;
                    case 4:
                        image = down[10];
                        break;
                    case 5:
                        image = down[11];
                        break;
                    case 6:
                        image = down[12];
                        break;
                    case 7:
                        image = down[13];
                        break;
                    case 8:
                        image = down[14];
                        break;
                    case 9:
                        image = down[15];
                        break;
                    case 10:
                        image = down[16];
                        break;
                    case 11:
                        image = down[17];
                        break;
                    case 12:
                        image = down[18];
                        break;
                }
                break;
            case "left":
                switch (spriteNum) {

                    case 1:
                        image = left[7];
                        break;
                    case 2:
                        image = left[8];
                        break;
                    case 3:
                        image = left[9];
                        break;
                    case 4:
                        image = left[10];
                        break;
                    case 5:
                        image = left[11];
                        break;
                    case 6:
                        image = left[12];
                        break;
                    case 7:
                        image = left[13];
                        break;
                    case 8:
                        image = left[14];
                        break;
                    case 9:
                        image = left[15];
                        break;
                    case 10:
                        image = left[16];
                        break;
                    case 11:
                        image = left[17];
                        break;
                    case 12:
                        image = left[18];
                        break;
                }
                break;
            case "right":
                switch (spriteNum) {

                    case 1:
                        image = right[7];
                        break;
                    case 2:
                        image = right[8];
                        break;
                    case 3:
                        image = right[9];
                        break;
                    case 4:
                        image = right[10];
                        break;
                    case 5:
                        image = right[11];
                        break;
                    case 6:
                        image = right[12];
                        break;
                    case 7:
                        image = right[13];
                        break;
                    case 8:
                        image = right[14];
                        break;
                    case 9:
                        image = right[15];
                        break;
                    case 10:
                        image = right[16];
                        break;
                    case 11:
                        image = right[17];
                        break;
                    case 12:
                        image = right[18];
                        break;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);

    }
}
