package model.Object;

import view.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Chest extends SuperObject {

    GamePanel gp;

    public Obj_Chest(GamePanel gp) {

        this.gp = gp;
        name = "Chest";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Chess.png"));
            utilityTool.scaleImage(image, gp.titleSize, gp.titleSize);
        } catch (IOException e) {
            e.getStackTrace();
        }


    }


}