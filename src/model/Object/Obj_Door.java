package model.Object;

import view.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Door extends SuperObject {

    GamePanel gp;

    public Obj_Door(GamePanel gp) {

        this.gp = gp;
        name = "Door";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Door.png"));
            utilityTool.scaleImage(image, gp.titleSize, gp.titleSize);
        } catch (IOException e) {
            e.getStackTrace();
        }
        collision = true;

    }


}