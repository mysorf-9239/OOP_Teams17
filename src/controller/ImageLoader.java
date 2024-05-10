package controller;

import model.entity.Player;
import model.tile.Tile;
import model.tile.TileEndlessManager;
import view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    GamePanel gp;
    BufferedImage bigImage;
    String imagePath;
    int numImages;
    int imageWidth;
    int imageHeight;

    public ImageLoader (GamePanel gp, String imagePath, int numImages, int imageWidth, int imageHeight) {

        this.gp = gp;
        this.imagePath = imagePath;
        this.numImages = numImages;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public void PlayerImageLoader(Player player, int startX, int startY) {

        UtilityTool utilityTool = new UtilityTool();

        try {
            BufferedImage bigImage = ImageIO.read(getClass().getResourceAsStream(imagePath));

            int index = 0;
            int x, y;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    x = startX + j*imageWidth;
                    y = startY + i*imageHeight;
                    // Cắt ảnh con từ ảnh gốc
                    BufferedImage subImage = bigImage.getSubimage(x, y, imageWidth, imageHeight);
                    subImage = utilityTool.scaleImage(subImage, gp.titleSize, gp.titleSize);

                    if (index >= 0 && index < 3) {
                        player.down[index] = subImage;
                        index++;
                    }
                    else if (index >= 3 && index < 6) {
                        player.left[index-3] = subImage;
                        index++;
                    }
                    else if (index >= 6 && index < 9) {
                        player.right[index-6] = subImage;
                        index++;
                    }
                    else if (index >= 9 && index < 12) {
                        player.up[index-9] = subImage;
                        index++;
                    }
                    // Kiểm tra nếu đã cắt đủ số ảnh
                    if (index >= numImages) {
                        break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void TileImageLoader(TileEndlessManager tileEndlessManagers) {

        UtilityTool utilityTool = new UtilityTool();
        int index = 1;

        try {
            BufferedImage bigImage = ImageIO.read(getClass().getResourceAsStream(imagePath));

            for (int y = 0; y < bigImage.getHeight(); y += imageHeight) {
                for (int x = 0; x < bigImage.getWidth(); x += imageWidth) {
                    // Cắt ảnh con từ ảnh gốc
                    tileEndlessManagers.tile[index] = new Tile();
                    BufferedImage subImage = bigImage.getSubimage(x, y, imageWidth, imageHeight);
                    subImage = utilityTool.scaleImage(subImage, gp.titleSize, gp.titleSize);
                    tileEndlessManagers.tile[index].image = subImage;
                    tileEndlessManagers.tile[index].collision = false;

                    switch(index) {
                        case 5:
                        case 13:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                        case 29:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                            tileEndlessManagers.tile[index].collision = true;
                            break;
                    }
                    index++;

                    if (index >= numImages) {
                        break;
                    }
                }
            }
        }
        catch (IOException e) {
            e.getStackTrace();
        }
    }


}
