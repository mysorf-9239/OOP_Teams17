package model.tile;

import view.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Map extends TileManager {

    GamePanel gp;
    BufferedImage[] worldMap;

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
            Graphics2D g2 = worldMap[i].createGraphics();

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
        int width = gp.screenHeight / 2;
        int height = gp.screenHeight / 2;
        int x = gp.screenWidth / 2 - width / 2;
        int y;

        int sy1 = 0;
        if (GamePanel.currentMap == 0) {

            int playerWorldY = gp.player.worldY;

            int sx1 = 0;
            sy1 = Math.max(playerWorldY - gp.screenHeight, 0);
            int sx2 = 1920;
            int sy2 = Math.min(sy1 + gp.screenHeight * 2, worldMap[GamePanel.currentMap].getHeight());

            if (sy2 == worldMap[GamePanel.currentMap].getHeight()) {
                sy1 = sy2 - gp.screenHeight * 2;
            }

            y = gp.screenHeight / 4;

            g2.drawImage(worldMap[GamePanel.currentMap], x, y, x + width, y + height, sx1, sy1, sx2, sy2, null);

        } else {
            y = gp.screenHeight / 4;
            g2.drawImage(worldMap[GamePanel.currentMap], x, y, width, height, null);
        }

        // Player
        double scale = (double) worldMap[GamePanel.currentMap].getWidth() / width;
        int playerX = (int) (x + gp.player.worldX / scale);
        int playerY = (int) (y + (gp.player.worldY - sy1) / scale);
        int playerSize = (int) (gp.titleSize / scale);
        g2.drawImage(gp.player.down[1], playerX, playerY, playerSize, playerSize, null);



        for (int i = 0; i < gp.maxObject; i++) {
            if (gp.obj[GamePanel.currentMap][i] != null) {
                int objX = (int) (x + gp.obj[GamePanel.currentMap][i].worldX / scale);
                int objY = (int) (y + (gp.obj[GamePanel.currentMap][i].worldY - sy1) / scale);
                int objSize = (int) (gp.titleSize / scale);
                g2.drawImage(gp.obj[GamePanel.currentMap][i].down[1], objX, objY, objSize, objSize, null);
            }
        }

        // Title and hint
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        g2.setColor(Color.white);
        String text = "Overview";
        int textX = gp.ui.getXforCenterText(text) - gp.titleSize;
        int textY = gp.screenHeight / 5;
        g2.drawString(text, textX, textY);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        g2.setColor(new Color(52, 35, 122, 250));
        text = "Press M to close";
        textX = gp.ui.getXforCenterText(text) - gp.titleSize / 3;
        textY = gp.screenHeight * 4 / 5;
        g2.drawString(text, textX, textY);
    }

}
