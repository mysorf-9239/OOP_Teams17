package model.Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Boots extends SuperObject {


    public Obj_Boots() {


        name = "Boots";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Boots.png"));

        } catch (IOException e) {
            e.getStackTrace();
        }


    }


}
