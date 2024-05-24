package model.Object;

import model.entity.Entity;
import view.GamePanel;

public class Obj_Spidernet extends Entity {

    public Obj_Spidernet(GamePanel gp) {
        super(gp);

        name = "Spidernet";
        down[1] = setup("/objects/spidernet");
    }

}
