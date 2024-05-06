package controller;

import model.entity.Player;
import view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    GamePanel gp;
    BufferedImage bigImage;
    BufferedImage[] image;
    BufferedImage[] up = new BufferedImage[20];
    BufferedImage[] down = new BufferedImage[20];
    BufferedImage[] left = new BufferedImage[20];
    BufferedImage[] right = new BufferedImage[20];
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

            image = new BufferedImage[numImages];

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


}
