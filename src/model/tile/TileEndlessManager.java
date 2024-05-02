package model.tile;

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
    private int[][] defaultMap;
    private int defualtLavaY;
    private int defualtLavaSpeed;

    public Tile[] tile;
    int[][] map;
    public static String path;
    int lavaY;
    int lavaSpeed;

    public TileEndlessManager(GamePanel gp)
    {
        this.gp = gp;

        tile = new Tile[100];
        defaultMap = new int[gp.maxWorldCol][gp.maxWorldRow];

        loadMap(path);
        map = defaultMap;
        GamePanel.map = map;

        defualtLavaY = (gp.maxWorldRow - 1) * gp.titleSize;
        defualtLavaSpeed = 1;
        lavaY = defualtLavaY;
        lavaSpeed = defualtLavaSpeed;

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
        setup(41, "/tiles/lava/lava10.png", true);
        setup(42, "/tiles/lava/lava01.png", false);
        setup(43, "/tiles/lava/lava00.png", false);
        setup(44, "/tiles/lava/lava02.png", false);
        setup(45, "/tiles/lava/lava03.png", true);

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

                    defaultMap[col][row] = num;
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

    public void updateMap() {

        lavaY -= lavaSpeed;

        int lavaRow = lavaY/gp.titleSize;
        int z = gp.maxWorldRow - lavaRow;
        if (z < 10) {
            for (int i = 0; i < gp.maxWorldCol; i++) {
                map[i][lavaRow] = 41;
            }
            if (z > 1) {
                for (int i = 0; i < gp.maxWorldCol; i++) {
                    map[i][lavaRow + 1] = 45;
                }
            }

        }
        else {
            if (map[0][lavaRow] != 42) {

                for (int i = 0; i < 10; i++) {
                    map[i][lavaRow] = 41;
                    map[i][lavaRow+1] = 45;
                }
                for (int i = 10; i < 30; i++) {
                    map[i][lavaRow] = 42;
                    map[i][lavaRow+1] = 45;
                }
                for (int i = 30; i < gp.maxWorldCol; i++) {
                    map[i][lavaRow] = 41;
                    map[i][lavaRow+1] = 45;
                }
            }
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
