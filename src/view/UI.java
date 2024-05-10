package view;

import model.Object.Obj_Heart;
import model.entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    Color poison = new Color(52, 35, 122, 250);
    BufferedImage backgorundImage;

    //Obj image
    BufferedImage heart_full, heart_half, heart_blank;

    //Message
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public int commanNum = 0;

    //Other
    public boolean gameFnished = false;
    double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    String currentDialogue = "";

    public int titleScreenState = 0;
    public int subState = 0;



    public UI (GamePanel gp) {

        this.gp = gp;


        //Define font
        try {
            InputStream inputStream = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } catch (FontFormatException e) {
            e.getStackTrace();
        }  catch (IOException e) {
        e.getStackTrace();
        }

        //Create hub object
        Entity heart = new Obj_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }

    public void showMess(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white);
        //Title state
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        //Play State
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }
        //Option screen
        if (gp.gameState == gp.optionState) {
            drawOptionScreen();
        }
        //Game over screen
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
    }

    public void drawPlayerLife() {

        int x = gp.titleSize/2;
        int y = gp.titleSize/2;

        int i = 0;

        //Draw max blank heart
        while (i < gp.player.maxLife/2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.titleSize;
        }

        //Reset x, y
        x = gp.titleSize/2;
        y = gp.titleSize/2;
        int life = gp.player.life;

        //Draw current life
        if (life > 0 && life <= 1) {
            g2.drawImage(heart_half, x, y, null);
        }
        else if (life > 1 && life <= 2) {
            g2.drawImage(heart_full, x, y, null);
        }
        else if (life > 2 && life <= 3) {
            g2.drawImage(heart_full, x, y, null);
            g2.drawImage(heart_half, x + gp.titleSize, y, null);
        }
        else if (life > 3 && life <= 4) {
            g2.drawImage(heart_full, x, y, null);
            g2.drawImage(heart_full, x + gp.titleSize, y, null);
        }
        else if (life > 4 && life <= 5) {
            g2.drawImage(heart_full, x, y, null);
            g2.drawImage(heart_full, x + gp.titleSize, y, null);
            g2.drawImage(heart_half, x + gp.titleSize*2, y, null);
        }
        else if (life > 5) {
            g2.drawImage(heart_full, x, y, null);
            g2.drawImage(heart_full, x + gp.titleSize, y, null);
            g2.drawImage(heart_full, x + gp.titleSize*2, y, null);
        }
    }

    public void drawTitleScreen() {
        try {
            backgorundImage = ImageIO.read(getClass().getResourceAsStream("/Background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Background
        g2.drawImage(backgorundImage, 0, 0, gp.screenWidth, gp.screenHeight, null);

        if (titleScreenState == 0) {
            //Title name
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Fugitive";
            int x = getXforCenterText(text);
            int y = gp.titleSize*5;

            //Shadow
            g2.setColor(Color.black);
            g2.drawString(text, x+3, y+3);

            //Main Color
            g2.setColor(poison);
            g2.drawString(text, x, y);

            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 42F));
            g2.setColor(Color.white);

            text = "New Game";
            x = getXforCenterText(text) + gp.titleSize*4;
            y += gp.titleSize * 7;
            g2.drawString(text, x, y);
            if (commanNum == 0) {
                drawColection(text, x, y);
            }

            text = "Load Game";
            x = getXforCenterText(text) + gp.titleSize*4;
            y += gp.titleSize + 10;
            int width = gp.titleSize;
            int height = text.length() + 2;
            g2.drawString(text, x, y);
            if (commanNum == 1) {
                drawColection(text, x, y);
            }

            text = "Setting";
            x = getXforCenterText(text) + gp.titleSize*4;
            y += gp.titleSize + 10;
            g2.drawString(text, x, y);
            if (commanNum == 2) {
                drawColection(text, x, y);
            }

            text = "Quit";
            x = getXforCenterText(text) + gp.titleSize*4;
            y += gp.titleSize + 10;
            width = text.length()*13;
            height = 3;
            g2.drawString(text, x, y);
            if (commanNum == 3) {
                drawColection(text, x, y);
            }

            //Create
            g2.setFont(g2.getFont().deriveFont(12F));
            g2.setColor(Color.darkGray);
            text = "Create by Teams17";
            x = 3;
            y = gp.screenHeight - gp.titleSize/6;
            g2.drawString(text, x, y);

            //Version
            text = "version: 1.0.0";
            x = gp.screenWidth - gp.titleSize*2;
            y = gp.screenHeight - gp.titleSize/6;
            g2.drawString(text, x, y);

        }
        else if (titleScreenState == 1) {

            //Title name
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Setting";
            int x = getXforCenterText(text);
            int y = gp.titleSize*5;

            //Shadow
            g2.setColor(Color.black);
            g2.drawString(text, x+3, y+3);
            //Main
            g2.setColor(poison);
            g2.drawString(text, x, y);


            //Menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 42F));
            g2.setColor(Color.white);

            text = "Mode";
            x = getXforCenterText(text)  + gp.titleSize*4;
            y += gp.titleSize*7 ;
            g2.drawString(text, x, y);
            if (commanNum == 0) {
                drawColection(text, x, y);
                //Triange1
                int[] xPoints = {x + gp.titleSize*3, x + gp.titleSize*5/2, x + gp.titleSize*3};
                int[] yPoints = {y-gp.titleSize*3/5, y-gp.titleSize*3/10, y};
                g2.setColor(Color.white);
                g2.fillPolygon(xPoints, yPoints, 3);
                //Triange2
                xPoints[0] = x + gp.titleSize*6;
                xPoints[1] = x + gp.titleSize*13/2;
                xPoints[2] = x + gp.titleSize*6;
                g2.setColor(Color.white);
                g2.fillPolygon(xPoints, yPoints, 3);

                //Mode name
                g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 36F));
                if (gp.gameMode == gp.endlessMode) {
                    g2.drawString("Endless", x + gp.titleSize*3+27, y-3);
                } else if (gp.gameMode == gp.overcomeMode) {
                    g2.drawString("Overcome", x + gp.titleSize*3+12, y-3);
                }
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 42F));
                g2.setColor(Color.white);
            }

            text = "Music";
            x = getXforCenterText(text)  + gp.titleSize*4;
            y += gp.titleSize + 10;
            g2.drawString(text, x, y);
            if (commanNum == 1) {
                drawColection(text, x, y);
                //Music bar
                g2.setStroke(new BasicStroke(3));
                g2.drawRect(x + gp.titleSize*3+17, y-gp.titleSize*3/5, 120, 24);
                int volumeWidth = 24 * gp.music.volumeScale;
                g2.fillRect(x + gp.titleSize*3+17, y-gp.titleSize*3/5, volumeWidth, 24);
            }
            text = "SE";
            x = getXforCenterText(text)  + gp.titleSize*4;
            y += gp.titleSize + 10;
            g2.drawString(text, x, y);
            if (commanNum == 2) {
                drawColection(text, x, y);
                //SE bar
                g2.setStroke(new BasicStroke(3));
                g2.drawRect(x + gp.titleSize*9/4+20, y-gp.titleSize*3/5, 120, 24);
                int volumeWidth = 24 * gp.se.volumeScale;
                g2.fillRect(x + gp.titleSize*9/4+20, y-gp.titleSize*3/5, volumeWidth, 24);
            }
            text = "Back";
            x = getXforCenterText(text)  + gp.titleSize*4;
            y += gp.titleSize + 10;
            g2.drawString(text, x, y);
            if (commanNum == 3) {
                drawColection(text, x, y);
            }

            gp.config.saveConfig();
        }
    }


    //Option state
    private void drawOptionScreen() {

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int frameX = gp.titleSize*6;
        int frameY = gp.titleSize*3;
        int frameWidth = gp.titleSize*8;
        int frameHeight = gp.titleSize*12;
        drawSubWindown(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0:
                option_tops(frameX,frameY);
                break;
            case 1:
                option_EndGame_Confirmation(frameX, frameY);
                break;
        }
    }

    public void option_tops(int frameX, int frameY) {

        int textX;
        int textY;

        g2.setFont(g2.getFont().deriveFont(Font.ITALIC));
        //Title
        String text = "Options";
        textX = getXforCenterText(text);
        textY = frameY + gp.titleSize;
        g2.drawString(text, textX, textY);


        //Music
        text = "Music";
        textX = frameX + gp.titleSize;
        textY += gp.titleSize*2;
        g2.drawString(text, textX, textY);
        if (commanNum == 0) {
            drawColection(text, textX, textY);
        }

        //SE
        text = "SE";
        textX = frameX + gp.titleSize;
        textY += gp.titleSize*2;
        g2.drawString(text, textX, textY);
        if (commanNum == 1) {
            drawColection(text, textX, textY);
        }

        //EndGame
        text = "EndGame";
        textX = frameX + gp.titleSize;
        textY += gp.titleSize*2;
        g2.drawString(text, textX, textY);
        if (commanNum == 2) {
            drawColection(text, textX, textY);
        }

        //Back
        text = "Back";
        textX = frameX + gp.titleSize;
        textY += gp.titleSize*4;
        g2.drawString(text, textX, textY);
        if (commanNum == 3) {
            drawColection(text, textX, textY);
        }

        //Music bar
        textX = frameX + gp.titleSize*4 + 5;
        textY = frameY + gp.titleSize*2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 120, 24);
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        //SE bar
        textY += gp.titleSize*2;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        gp.config.saveConfig();
    }

    public void option_EndGame_Confirmation(int frameX, int frameY) {

        int textX = frameX + gp.titleSize + 40;
        int textY = frameY = gp.titleSize*6;

        currentDialogue = "Quit the game and \nreturn to the title screen";

        g2.setFont(g2.getFont().deriveFont(Font.ITALIC));
        for (String line: currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textX -= 40;
            textY += gp.titleSize;
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD));
        //Yes
        String text = "Yes";
        textX = getXforCenterText(text);
        textY += gp.titleSize * 4;
        g2.drawString(text, textX, textY);
        if (commanNum == 0) {
            drawColection(text, textX, textY);
        }

        //No
        text = "No";
        textX = getXforCenterText(text);
        textY += gp.titleSize * 2;
        g2.drawString(text, textX, textY);
        if (commanNum == 1) {
            drawColection(text, textX, textY);
        }
    }

    public void drawGameOverScreen() {

        //Over
        g2.setColor(new Color(0, 0, 0, 125));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x, y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));

        text = "Game Over";
        //Shadow
        g2.setColor(Color.black);
        x = getXforCenterText(text);
        y = gp.titleSize*6;
        g2.drawString(text, x, y);
        //Main
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        //Retry
        g2.setFont(g2.getFont().deriveFont(50F));
        g2.setColor(Color.white);
        text = "Retry";
        x = getXforCenterText(text);
        y += gp.titleSize*6;
        g2.drawString(text, x, y);
        if (commanNum == 0) {
            drawColection(text, x, y);
        }

        //Return title screen
        text = "Quit";
        x = getXforCenterText(text);
        y += gp.titleSize*2;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commanNum == 1) {
            drawColection(text, x, y);
        }
    }



    //Auxiliary Method
    public int getXforCenterText(String text) {

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;

        return x;
    }

    private void drawSubWindown(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width - 10, height - 10, 25, 25);
    }

    public void drawColection(String text, int x, int y) {

        if (gp.gameState == gp.titleState) {
            //Border
            g2.setColor(new Color(0, 0, 0, 52));
            g2.fillRect(x - 5, y - gp.titleSize + 5, gp.screenWidth/2, gp.titleSize);
        }

        //Shadow
        g2.setColor(Color.black);
        g2.drawString(text, x, y);

        //Main Color
        g2.setColor(poison);
        if (gp.gameState == gp.optionState) { g2.setColor(Color.red);}
        g2.drawString(text, x-2, y-1);
        g2.setColor(Color.white);
    }

}
