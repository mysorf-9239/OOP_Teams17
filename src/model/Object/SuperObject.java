package model.Object;

import controller.UtilityTool;
import view.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    UtilityTool utilityTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        //Han che ve frame
        if (worldX > gp.player.worldX - gp.player.screenX - gp.titleSize && worldX < gp.player.worldX + gp.player.screenX + gp.titleSize &&
                worldY > gp.player.worldY - gp.player.screenY - gp.titleSize && worldY < gp.player.worldY + gp.player.screenY + gp.titleSize) {
            g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);
        }

    }


}
