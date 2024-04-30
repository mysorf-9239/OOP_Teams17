package model.Object;

import view.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Heart extends SuperObject {

    GamePanel gp;

    public Obj_Heart(GamePanel gp) {

        this.gp = gp;

        name = "Key";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
            image = utilityTool.scaleImage(image, gp.titleSize, gp.titleSize);
            image2 = utilityTool.scaleImage(image, gp.titleSize, gp.titleSize);
            image3 = utilityTool.scaleImage(image, gp.titleSize, gp.titleSize);
        } catch (IOException e) {
            e.getStackTrace();
        }


    }
}
