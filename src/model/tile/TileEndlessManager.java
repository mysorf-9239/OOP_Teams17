package model.tile;

import controller.ImageLoader;
import controller.UtilityTool;
import view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class TileEndlessManager {
    GamePanel gp;

    public Tile[] tile;
    int[][] map;
    public static String path;

    ImageLoader imageLoader;

    public final int numImage = 88;
    public final int imageWidth = 32;
    public final int imageHeight = 32;

    public TileEndlessManager(GamePanel gp)
    {
        this.gp = gp;

        tile = new Tile[100];
        map = new int[gp.maxWorldCol][gp.maxWorldRow];

        loadMap(path);
        GamePanel.map = map;

        getTileImage(tile);
    }

    public void getTileImage(Tile[] tile) {

        setup(0, "/tiles/earth.png", false);

        imageLoader = new ImageLoader(gp, "/tiles/All.png", numImage, imageWidth, imageHeight);
        imageLoader.TileImageLoader(this);

        setup(1, "/tiles/grass10.png", false);
        setup(2, "/tiles/grass11.png", false);
        setup(5, "/tiles/grass12.png", true);
    }

    public void setup(int index, String imagePath, boolean collision) {

        UtilityTool utilityTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            tile[index].image = utilityTool.scaleImage(tile[index].image, gp.titleSize, gp.titleSize);
            tile[index].collision = collision;
        }
        catch (IOException e) {
            e.getStackTrace();
        }
    }

    public void setMapPath() {
    }

    public void loadMap(String filePath) {

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while (col < gp.maxWorldCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    map[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }

            br.close();
        }
        catch(IOException e) {
            e.getStackTrace();
        }
    }


    public void draw(Graphics2D g2)
    {

        int worldCol = 0;
        int worldRow = 0;


        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = map[worldCol][worldRow];

            int worldX = worldCol * gp.titleSize;
            int worldY = worldRow * gp.titleSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //Han che ve frame
            if (worldX > gp.player.worldX - gp.player.screenX - gp.titleSize && worldX < gp.player.worldX + gp.player.screenX + gp.titleSize &&
                    worldY > gp.player.worldY - gp.player.screenY - gp.titleSize && worldY < gp.player.worldY + gp.player.screenY + gp.titleSize) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }

        }
    }
}
