package view;

import model.Object.Obj_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial_40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFnished = false;
    double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");


    public UI (GamePanel gp) {

        this.gp = gp;

        arial_40 = new Font("Arial", Font.ITALIC, 40);

        Obj_Key key = new Obj_Key(gp);
        keyImage = key.image;
    }

    public void showMess(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if (gameFnished == true) {

            g2.setColor(Color.red);
            g2.setFont(arial_40);

            String text;
            int textLength;
            int x, y;

            text = "You WIN";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - gp.titleSize/3;
            g2.drawString(text, x, y);
        }
        else {
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.titleSize / 2, gp.titleSize / 2, gp.titleSize, gp.titleSize, null);
            g2.drawString("x: " + gp.player.hasKey, 74, 65);

            playTime+= (double) 1/60;
            //g2.drawString("Time: " + decimalFormat.format(playTime), gp.titleSize*16, 65);

            if (messageOn == true) {

                g2.drawString(message, 100, 100);

                messageCounter++;
                if (messageCounter > 100) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }

}
