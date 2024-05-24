package model.Object;

import model.entity.Entity;
import view.GamePanel;


public class Obj_HP extends Entity {

    public Obj_HP(GamePanel gp) {

        super(gp);
        name = "HP";
        down[1] = setup("/objects/HP");
    }
}
