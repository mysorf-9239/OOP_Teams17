package model.Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Door extends SuperObject {

    public Obj_Door() {

        name = "Door";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Door.png"));

        } catch (IOException e) {
            e.getStackTrace();
        }
        collision = true;

    }


}