package model.Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Chest extends SuperObject {


    public Obj_Chest() {


        name = "Chest";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Chess.png"));

        } catch (IOException e) {
            e.getStackTrace();
        }


    }


}