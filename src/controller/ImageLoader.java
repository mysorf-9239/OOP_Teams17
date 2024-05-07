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

    public void PlayerImageLoader(Player player) {

        UtilityTool utilityTool = new UtilityTool();

        try {
            BufferedImage bigImage = ImageIO.read(getClass().getResourceAsStream(imagePath));

            int index = 0;
            int up = 0;
            int down = 0;
            int left = 0;
            int right = 0;
            for (int y = 0; y < bigImage.getHeight(); y += imageHeight) {
                for (int x = 0; x < bigImage.getWidth(); x += imageWidth) {
                    // Cắt ảnh con từ ảnh gốc
                    BufferedImage subImage = bigImage.getSubimage(x, y, imageWidth, imageHeight);
                    subImage = utilityTool.scaleImage(subImage, gp.titleSize, gp.titleSize);

                    if ((index >= 0 && index < 6) || (index >= 24 && index < 36)) {
                        player.right[right] = subImage;
                        index++;
                        right++;
                    }
                    else if ((index >= 6 && index < 12) || (index >= 36 && index < 48)) {
                        player.up[up] = subImage;
                        index++;
                        up++;
                    }
                    else if ((index >= 12 && index < 18) || (index >= 48 && index < 60)) {
                        player.left[left] = subImage;
                        index++;
                        left++;
                    }
                    else if ((index >= 18 && index < 24) || (index >= 60 && index < 72)) {
                        player.down[down] = subImage;
                        index++;
                        down++;
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
