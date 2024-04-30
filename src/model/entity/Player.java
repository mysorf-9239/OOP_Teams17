package model.entity;

import controller.KeyHandler;
import controller.UtilityTool;
import model.tile.TileEndlessManager;
import view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        //Define the start screen's andres
        worldX = gp.titleSize * 18;
        worldY = gp.titleSize * (gp.maxWorldRow - 12);
        speed = gp.titleSize/5;
        direction = "up";

        //Player life
        maxLife= 6;
        life = maxLife;
    }

    public void getPlayerImage() {

        up[1] = setup("up01");
        up[2] = setup("up02");
        up[3] = setup("up03");
        up[4] = setup("up04");
        up[5] = setup("up05");
        up[6] = setup("up06");
        up[7] = setup("up07");
        up[8] = setup("up08");
        up[9] = setup("up09");
        up[10] = setup("up10");
        up[11] = setup("up11");
        up[12] = setup("up12");
        up[13] = setup("up13");
        up[14] = setup("up14");
        up[15] = setup("up15");
        up[16] = setup("up16");
        up[17] = setup("up17");
        up[18] = setup("up18");

        left[1] = setup("left01");
        left[2] = setup("left02");
        left[3] = setup("left03");
        left[4] = setup("left04");
        left[5] = setup("left05");
        left[6] = setup("left06");
        left[7] = setup("left07");
        left[8] = setup("left08");
        left[9] = setup("left09");
        left[10] = setup("left10");
        left[11] = setup("left11");
        left[12] = setup("left12");
        left[13] = setup("left13");
        left[14] = setup("left14");
        left[15] = setup("left15");
        left[16] = setup("left16");
        left[17] = setup("left17");
        left[18] = setup("left18");


        down[1] = setup("down01");
        down[2] = setup("down02");
        down[3] = setup("down03");
        down[4] = setup("down04");
        down[5] = setup("down05");
        down[6] = setup("down06");
        down[7] = setup("down07");
        down[8] = setup("down08");
        down[9] = setup("down09");
        down[10] = setup("down10");
        down[11] = setup("down11");
        down[12] = setup("down12");
        down[13] = setup("down13");
        down[14] = setup("down14");
        down[15] = setup("down15");
        down[16] = setup("down16");
        down[17] = setup("down17");
        down[18] = setup("down18");

        right[1] = setup("right01");
        right[2] = setup("right02");
        right[3] = setup("right03");
        right[4] = setup("right04");
        right[5] = setup("right05");
        right[6] = setup("right06");
        right[7] = setup("right07");
        right[8] = setup("right08");
        right[9] = setup("right09");
        right[10] = setup("right10");
        right[11] = setup("right11");
        right[12] = setup("right12");
        right[13] = setup("right13");
        right[14] = setup("right14");
        right[15] = setup("right15");
        right[16] = setup("right16");
        right[17] = setup("right17");
        right[18] = setup("right18");

    }

    public BufferedImage setup(String imageName) {

        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
            image = utilityTool.scaleImage(image, gp.titleSize, gp.titleSize);
        }
        catch (IOException e) {
            e.getStackTrace();
        }

        return image;
    }

    public void update() {

        //Move
        if(keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed == true)
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
            }
            else {
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
            spriteConuter++;
            int spriteDistance = 0;
            if (spriteConuter - spriteDistance > 1) {
                if (spriteNum < 12) {
                    spriteNum++;
                    spriteDistance += 1;
                }
                else {
                    spriteNum = 1;
                    spriteDistance = 0;
                }
                spriteConuter = 0;
            }
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
//                    }
//                    else {
//                        gp.ui.showMess("You need a key to unlock!");
//                    }
//                    break;
//                case "Boots":
//                    gp.playSE(2);
//                    speed += 2;
//                    gp.obj[i] = null;
//                    gp.ui.showMess("Speed up");
//                    break;
//                case "Chest":
//
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
//            default:
//                switch (direction) {
//                    case "up":
//                        switch (spriteNum) {
//                            case 1:
//                            case 7:
//                                image = up[1];
//                                break;
//                            case 2:
//                            case 8:
//                                image = up[2];
//                                break;
//                            case 3:
//                            case 9:
//                                image = up[3];
//                                break;
//                            case 4:
//                            case 10:
//                                image = up[4];
//                                break;
//                            case 5:
//                            case 11:
//                                image = up[5];
//                                break;
//                            case 6:
//                            case 12:
//                                image = up[6];
//                                break;
//                        }
//                        break;
//                }
        }
        g2.drawImage(image, screenX, screenY, null);


    }
}
