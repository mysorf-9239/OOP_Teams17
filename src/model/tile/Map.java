package model.tile;

import view.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Map extends TileManager {

    GamePanel gp;
    BufferedImage worldMap[];

    public Map(GamePanel gp) {
        super(gp);
        this.gp = gp;
        this.map = gp.tileManager.map;
        createWorldMap();
    }

    public void createWorldMap() {

        worldMap = new BufferedImage[gp.maxMap];

        int maxCol = 40;
        int maxRow;

        for (int i = 0; i < gp.maxMap; i++) {

            if (i == 0) {
                maxRow = 500;
            } else {
                maxRow = 40;
            }

            int worldMapWidth = gp.titleSize*maxCol;
            int worldMapHeight = gp.titleSize*maxRow;

            worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = (Graphics2D)worldMap[i].createGraphics();

            int worldCol = 0;
            int worldRow = 0;

            while (worldCol < maxCol && worldRow < maxRow) {

                int tileNum = map[i][worldCol][worldRow];

                int worldX = worldCol * gp.titleSize;
                int worldY = worldRow * gp.titleSize;
                g2.drawImage(tile[tileNum].image, worldX, worldY, null);
                worldCol++;

                if (worldCol == maxCol) {
                    worldCol = 0;
                    worldRow++;
                }
            }
            g2.dispose();
        }
    }

    public void drawFullMapScreen(Graphics2D g2) {

        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Draw Map
        int width = gp.screenHeight/2;
        int height = gp.screenHeight/2;
        int x = gp.screenWidth / 2 - width / 2;
        int y;

        if (GamePanel.currentMap == 0) {

            int playerWorldX = gp.player.worldX;
            int playerWorldY = gp.player.worldY;

            int sx1 = 0;
            int sy1 = Math.max(playerWorldY - gp.screenHeight, 0);
            int sx2 = 1920;
            int sy2 = Math.min(sy1 + gp.screenHeight*2, worldMap[GamePanel.currentMap].getHeight());

            if (sy2 == worldMap[GamePanel.currentMap].getHeight()) {
                sy1 = sy2 - gp.screenHeight*2;
            }

            y = gp.screenHeight/4;

            g2.drawImage(worldMap[GamePanel.currentMap], x, y, x + width, y + height, sx1, sy1, sx2, sy2, null);

        } else {
            y = gp.screenHeight/4;
            g2.drawImage(worldMap[GamePanel.currentMap], x, y, width, height, null);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        g2.setColor(new Color(52, 35, 122, 250));
        String text = "Press M to return";
        int textX = gp.titleSize*15/2;
        int textY = gp.screenHeight*4/5;
        g2.drawString(text, textX, textY);
    }
}
