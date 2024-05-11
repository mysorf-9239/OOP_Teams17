package model.tile;

import controller.ImageLoader;
import controller.UtilityTool;
import view.GamePanel;
import controller.Config;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class TileManager {
    GamePanel gp;

    public Tile[] tile;
    int[][][] map;
    public static ArrayList<String> pathList = new ArrayList<>();
    public static int pathNum;
    Config config = new Config(gp);

    ImageLoader imageLoader;

    public final int numImage = 88;
    public final int imageWidth = 32;
    public final int imageHeight = 32;


    public TileManager(GamePanel gp)
    {
        System.out.println("Khai bao TileManager");

        this.gp = gp;

        tile = new Tile[100];
        map = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        setMapPath();

        loadMap(0);
        loadMap(1);
        loadMap(2);
        loadMap(3);
        loadMap(4);
        loadMap(5);
        GamePanel.map = map;

        getTileImage(tile);
    }

    public void getTileImage(Tile[] tile) {

        setup(0, "/tiles/earth.png", false);

        imageLoader = new ImageLoader(gp, "/tiles/All.png", numImage, imageWidth, imageHeight);
        imageLoader.TileImageLoader(this);
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

        pathList.add("/map/map0.txt");
        pathList.add("/map/map1.txt");
        pathList.add("/map/map2.txt");
        pathList.add("/map/map3.txt");
        pathList.add("/map/map4.txt");
        pathList.add("/map/map5.txt");
    }

    public void loadMap(int mapNum) {

        int loadMaxCol = 0, loadMaxRow = 0;
        if (mapNum == 0) {
            loadMaxCol = 40;
            loadMaxRow = 500;
        } else if (mapNum > 0) {
            loadMaxCol = 40;
            loadMaxRow = 40;
        }

        try {
            InputStream is = getClass().getResourceAsStream(pathList.get(mapNum));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < loadMaxCol && row < loadMaxRow) {

                String line = br.readLine();

                while (col < loadMaxCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    map[mapNum][col][row] = num;
                    col++;
                }
                if (col == loadMaxCol) {
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


    public void draw(Graphics2D g2) {

        g2.setColor(new Color(59, 143, 202));
        g2.fillRect(0, 0, gp.maxScreenCol*gp.titleSize, gp.maxScreenRow*gp.titleSize);

        int worldCol = 0;
        int worldRow = 0;

        if (gp.currentMap == 0) {
            gp.currentWorldCol = 40;
            gp.currentWorldRow = 500;
        } else if (gp.currentMap > 0) {
            gp.currentWorldCol = 40;
            gp.currentWorldRow = 40;
        }

        while (worldCol < gp.currentWorldCol && worldRow < gp.currentWorldRow) {

            int tileNum = map[gp.currentMap][worldCol][worldRow];

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

            if (worldCol == gp.currentWorldCol) {
                worldCol = 0;
                worldRow++;
            }

        }
    }
}
