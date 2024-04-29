package model.Object;

import view.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Boots extends SuperObject {

    GamePanel gp;

    public Obj_Boots(GamePanel gp) {

        this.gp = gp;
        name = "Boots";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Boots.png"));
            utilityTool.scaleImage(image, gp.titleSize, gp.titleSize);
        } catch (IOException e) {
            e.getStackTrace();
        }


    }


}
