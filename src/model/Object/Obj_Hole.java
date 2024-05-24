package model.Object;

import model.entity.Entity;
import view.GamePanel;


public class Obj_Hole extends Entity {

    public Obj_Hole(GamePanel gp) {

        super(gp);
        name = "Hole";
        down[1] = setup("/objects/hole");
    }


}
