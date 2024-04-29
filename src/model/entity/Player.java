package model.entity;

import controller.KeyHandler;
import model.tile.TileEndlessManager;
import view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyHandler)
    {
        this.gp = gp;

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
    }

    public void getPlayerImage() {

        try {

//            up[1] = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
//            up[2] = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
//            down[1] = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
//            down[2] = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
//            left[1] = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
//            left[2] = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
//            right[1] = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
//            right[2] = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));

            up[1] = ImageIO.read(getClass().getResourceAsStream("/player/up01.png"));
            up[2] = ImageIO.read(getClass().getResourceAsStream("/player/up02.png"));
            up[3] = ImageIO.read(getClass().getResourceAsStream("/player/up03.png"));
            up[4] = ImageIO.read(getClass().getResourceAsStream("/player/up04.png"));
            up[5] = ImageIO.read(getClass().getResourceAsStream("/player/up05.png"));
            up[6] = ImageIO.read(getClass().getResourceAsStream("/player/up06.png"));
            up[7] = ImageIO.read(getClass().getResourceAsStream("/player/up07.png"));
            up[8] = ImageIO.read(getClass().getResourceAsStream("/player/up08.png"));
            up[9] = ImageIO.read(getClass().getResourceAsStream("/player/up09.png"));
            up[10] = ImageIO.read(getClass().getResourceAsStream("/player/up10.png"));
            up[11] = ImageIO.read(getClass().getResourceAsStream("/player/up11.png"));
            up[12] = ImageIO.read(getClass().getResourceAsStream("/player/up12.png"));
            up[13] = ImageIO.read(getClass().getResourceAsStream("/player/up13.png"));
            up[14] = ImageIO.read(getClass().getResourceAsStream("/player/up14.png"));
            up[15] = ImageIO.read(getClass().getResourceAsStream("/player/up15.png"));
            up[16] = ImageIO.read(getClass().getResourceAsStream("/player/up16.png"));
            up[17] = ImageIO.read(getClass().getResourceAsStream("/player/up17.png"));
            up[18] = ImageIO.read(getClass().getResourceAsStream("/player/up18.png"));

            left[1] = ImageIO.read(getClass().getResourceAsStream("/player/left01.png"));
            left[2] = ImageIO.read(getClass().getResourceAsStream("/player/left02.png"));
            left[3] = ImageIO.read(getClass().getResourceAsStream("/player/left03.png"));
            left[4] = ImageIO.read(getClass().getResourceAsStream("/player/left04.png"));
            left[5] = ImageIO.read(getClass().getResourceAsStream("/player/left05.png"));
            left[6] = ImageIO.read(getClass().getResourceAsStream("/player/left06.png"));
            left[7] = ImageIO.read(getClass().getResourceAsStream("/player/left07.png"));
            left[8] = ImageIO.read(getClass().getResourceAsStream("/player/left08.png"));
            left[9] = ImageIO.read(getClass().getResourceAsStream("/player/left09.png"));
            left[10] = ImageIO.read(getClass().getResourceAsStream("/player/left10.png"));
            left[11] = ImageIO.read(getClass().getResourceAsStream("/player/left11.png"));
            left[12] = ImageIO.read(getClass().getResourceAsStream("/player/left12.png"));
            left[13] = ImageIO.read(getClass().getResourceAsStream("/player/left13.png"));
            left[14] = ImageIO.read(getClass().getResourceAsStream("/player/left14.png"));
            left[15] = ImageIO.read(getClass().getResourceAsStream("/player/left15.png"));
            left[16] = ImageIO.read(getClass().getResourceAsStream("/player/left16.png"));
            left[17] = ImageIO.read(getClass().getResourceAsStream("/player/left17.png"));
            left[18] = ImageIO.read(getClass().getResourceAsStream("/player/left18.png"));


            down[1] = ImageIO.read(getClass().getResourceAsStream("/player/down01.png"));
            down[2] = ImageIO.read(getClass().getResourceAsStream("/player/down02.png"));
            down[3] = ImageIO.read(getClass().getResourceAsStream("/player/down03.png"));
            down[4] = ImageIO.read(getClass().getResourceAsStream("/player/down04.png"));
            down[5] = ImageIO.read(getClass().getResourceAsStream("/player/down05.png"));
            down[6] = ImageIO.read(getClass().getResourceAsStream("/player/down06.png"));
            down[7] = ImageIO.read(getClass().getResourceAsStream("/player/down07.png"));
            down[8] = ImageIO.read(getClass().getResourceAsStream("/player/down08.png"));
            down[9] = ImageIO.read(getClass().getResourceAsStream("/player/down09.png"));
            down[10] = ImageIO.read(getClass().getResourceAsStream("/player/down10.png"));
            down[11] = ImageIO.read(getClass().getResourceAsStream("/player/down11.png"));
            down[12] = ImageIO.read(getClass().getResourceAsStream("/player/down12.png"));
            down[13] = ImageIO.read(getClass().getResourceAsStream("/player/down13.png"));
            down[14] = ImageIO.read(getClass().getResourceAsStream("/player/down14.png"));
            down[15] = ImageIO.read(getClass().getResourceAsStream("/player/down15.png"));
            down[16] = ImageIO.read(getClass().getResourceAsStream("/player/down16.png"));
            down[17] = ImageIO.read(getClass().getResourceAsStream("/player/down17.png"));
            down[18] = ImageIO.read(getClass().getResourceAsStream("/player/down18.png"));

            right[1] = ImageIO.read(getClass().getResourceAsStream("/player/right01.png"));
            right[2] = ImageIO.read(getClass().getResourceAsStream("/player/right02.png"));
            right[3] = ImageIO.read(getClass().getResourceAsStream("/player/right03.png"));
            right[4] = ImageIO.read(getClass().getResourceAsStream("/player/right04.png"));
            right[5] = ImageIO.read(getClass().getResourceAsStream("/player/right05.png"));
            right[6] = ImageIO.read(getClass().getResourceAsStream("/player/right06.png"));
            right[7] = ImageIO.read(getClass().getResourceAsStream("/player/right07.png"));
            right[8] = ImageIO.read(getClass().getResourceAsStream("/player/right08.png"));
            right[9] = ImageIO.read(getClass().getResourceAsStream("/player/right09.png"));
            right[10] = ImageIO.read(getClass().getResourceAsStream("/player/right10.png"));
            right[11] = ImageIO.read(getClass().getResourceAsStream("/player/right11.png"));
            right[12] = ImageIO.read(getClass().getResourceAsStream("/player/right12.png"));
            right[13] = ImageIO.read(getClass().getResourceAsStream("/player/right13.png"));
            right[14] = ImageIO.read(getClass().getResourceAsStream("/player/right14.png"));
            right[15] = ImageIO.read(getClass().getResourceAsStream("/player/right15.png"));
            right[16] = ImageIO.read(getClass().getResourceAsStream("/player/right16.png"));
            right[17] = ImageIO.read(getClass().getResourceAsStream("/player/right17.png"));
            right[18] = ImageIO.read(getClass().getResourceAsStream("/player/right18.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
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

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMess("You got a key");
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.playSE(3);
                        hasKey--;
                        gp.obj[i] = null;
                        gp.ui.showMess("You opened the door");
                    }
                    else {
                        gp.ui.showMess("You need a key to unlock!");
                    }
                    break;
                case "Boots":
                    gp.playSE(2);
                    speed += 2;
                    gp.obj[i] = null;
                    gp.ui.showMess("Speed up");
                    break;
                case "Chest":

                    gp.ui.gameFnished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
            }
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
        g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);


    }
}
