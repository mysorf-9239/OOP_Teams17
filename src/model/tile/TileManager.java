package model.tile;

import controller.tool.ImageLoader;
import controller.tool.UtilityTool;
import view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;


public class TileManager {
    GamePanel gp;

    public Tile[] tile;
    int[][][] map;
    public static ArrayList<String> pathList = new ArrayList<>();

    ImageLoader imageLoader;

    public final int numImage = 100;
    public final int imageWidth = 32;
    public final int imageHeight = 32;

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[150];
        map = new int[gp.maxMap][GamePanel.maxWorldCol][GamePanel.maxWorldRow];

        setMapPath();

        for (int i = 0; i < 21; i++) {
            loadMap(i);
        }
        GamePanel.map = map;

        getTileImage();
    }

    public void getTileImage() {

        setup(0, "/tiles/trunk.png", false);

        imageLoader = new ImageLoader(gp, "/tiles/All.png", numImage, imageWidth, imageHeight);
        imageLoader.TileImageLoader(this);
    }

    public void setup(int index, String imagePath, boolean collision) {

        UtilityTool utilityTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            tile[index].image = utilityTool.scaleImage(tile[index].image, gp.titleSize, gp.titleSize);
            tile[index].collision = collision;
        }
        catch (IOException e) {
            e.getStackTrace();
        }
    }

    public void setMapPath() {

        for (int i = 0; i <= 20; i++) {
            pathList.add(String.format("/map/map%d.txt", i));
        }
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
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < loadMaxCol && row < loadMaxRow) {

                String line = br.readLine();

                while (col < loadMaxCol) {

                    String[] numbers = line.split(" ");

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

    public int checkTile(int playerCol, int playerRow) {

        int tileAddress = -1;
        int tileCol = playerCol;
        int tileRow = playerRow;

        switch (gp.player.direction) {
            case "up":
                tileRow -= 1;
                break;
            case "left":
                tileCol -= 1;
                break;
            case "down":
                tileRow += 1;
                break;
            case "right":
                tileCol += 1;
                break;
        }

        if (tileCol >= 0 && tileCol <= GamePanel.maxWorldCol && tileRow >= 0) {

            tileAddress = switch (map[GamePanel.currentMap][tileCol][tileRow]) {
                case 5, 13, 71, 72, 78, 79, 80, 87, 88, 91, 92, 95, 96 -> tileRow * GamePanel.maxWorldRow + tileCol;
                default -> tileAddress;
            };

        }

        return tileAddress;
    }

    public void cutTree(int Col, int Row) {

        if (map[GamePanel.currentMap][Col][Row] == 5 || map[GamePanel.currentMap][Col][Row] == 13){
            map[GamePanel.currentMap][Col][Row] = 0;
        } else {
            map[GamePanel.currentMap][Col][Row] = 70;
        }
    }


    public void draw(Graphics2D g2) {

        g2.setColor(new Color(59, 143, 202));
        g2.fillRect(0, 0, gp.maxScreenCol*gp.titleSize, gp.maxScreenRow*gp.titleSize);

        int worldCol = 0;
        int worldRow = 0;

        if (0 == GamePanel.currentMap) {
            GamePanel.currentWorldCol = 40;
            GamePanel.currentWorldRow = 500;
        } else if (0 < GamePanel.currentMap) {
            GamePanel.currentWorldCol = 40;
            GamePanel.currentWorldRow = 40;
        }

        while (worldCol < GamePanel.currentWorldCol && worldRow < GamePanel.currentWorldRow) {

            int tileNum = map[GamePanel.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.titleSize;
            int worldY = worldRow * gp.titleSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //Han che ve frame
            if (worldX > gp.player.worldX - gp.player.screenX - gp.titleSize && worldX < gp.player.worldX + gp.player.screenX + gp.titleSize &&
                    worldY > gp.player.worldY - gp.player.screenY - gp.titleSize && worldY < gp.player.worldY + gp.player.screenY + gp.titleSize) {
                if (tile[tileNum] != null) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                }
            }
            worldCol++;

            if (worldCol == GamePanel.currentWorldCol) {
                worldCol = 0;
                worldRow++;
            }

        }
    }
}
