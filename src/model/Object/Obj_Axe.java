package model.Object;

import model.entity.Entity;
import view.GamePanel;


public class Obj_Axe extends Entity {

    public Obj_Axe(GamePanel gp) {

        super(gp);
        name = "Axe";
        down[1] = setup("/objects/Axe");
    }


}
