package model.Object;

import model.entity.Entity;
import view.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Boots extends Entity {

    GamePanel gp;

    public Obj_Boots(GamePanel gp) {

        super(gp);
        name = "Boots";
        down[1] = setup("/objects/Boots");

    }


}
