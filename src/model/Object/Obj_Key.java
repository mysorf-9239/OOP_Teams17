package model.Object;

import view.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Key extends SuperObject {

    GamePanel gp;

    public Obj_Key(GamePanel gp) {

        this.gp = gp;
        name = "Key";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Key.png"));
            utilityTool.scaleImage(image, gp.titleSize, gp.titleSize);
        } catch (IOException e) {
            e.getStackTrace();
        }


    }


}
