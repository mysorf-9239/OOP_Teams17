package model.tile;

import controller.UtilityTool;
import view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class TileEndlessManager {
    GamePanel gp;
    public Tile[] tile;
    int[][] map;
    public static String path;

    public TileEndlessManager(GamePanel gp)
    {
        this.gp = gp;
        //this.path = path;

        tile = new Tile[50];
        this.map = new int[gp.maxWorldCol][gp.maxWorldRow];

        loadMap(path);
        GamePanel.map = this.map;
        getTileImage();

    }

    public void getTileImage() {


        setup(0, "/tiles/earth.png", false);
        setup(1, "/tiles/grass/grass01.png", false);
        setup(2, "/tiles/grass/grass00.png", false);
        setup(3, "/tiles/grass/grass01.png", false);
        setup(4, "/tiles/grass/grass00.png", false);
        setup(5, "/tiles/other/tree.png", true);
        setup(6, "/tiles/water/water10.png", false);
        setup(7, "/tiles/water/water11.png", false);
        setup(8, "/tiles/other/floor01.png", false);
        setup(9, "/tiles/grass/grass01.png", false);
        setup(10, "/tiles/grass/grass00.png", false);
        setup(11, "/tiles/grass/grass01.png", false);
        setup(12, "/tiles/grass/grass00.png", false);
        setup(13, "/tiles/other/tree.png", true);
        setup(14, "/tiles/water/water12.png", false);
        setup(15, "/tiles/water/water13.png", false);
        setup(16, "/tiles/other/wall.png", true);
        setup(17, "/tiles/water/water02.png", true);
        setup(18, "/tiles/water/water03.png", true);
        setup(19, "/tiles/water/water04.png", true);
        setup(20, "/tiles/water/water10.png", true);
        setup(21, "/tiles/water/water11.png", true);
        setup(22, "/tiles/road/road01.png", false);
        setup(23, "/tiles/road/road02.png", false);
        setup(24, "/tiles/road/road03.png", false);
        setup(25, "/tiles/water/water05.png", true);
        setup(26, "/tiles/water/water00.png", true);
        setup(27, "/tiles/water/water06.png", true);
        setup(28, "/tiles/water/water12.png", true);
        setup(29, "/tiles/water/water13.png", true);
        setup(30, "/tiles/road/road04.png", false);
        setup(31, "/tiles/road/road00.png", false);
        setup(32, "/tiles/road/road05.png", false);
        setup(33, "/tiles/water/water07.png", true);
        setup(34, "/tiles/water/water08.png", true);
        setup(35, "/tiles/water/water09.png", true);
        setup(36, "/tiles/water/water01.png", true);
        setup(37, "/tiles/other/hut.png", true);
        setup(38, "/tiles/road/road06.png", false);
        setup(39, "/tiles/road/road07.png", false);
        setup(40, "/tiles/road/road08.png", false);

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
