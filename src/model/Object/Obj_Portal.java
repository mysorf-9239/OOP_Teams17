package model.Object;

import model.entity.Entity;
import view.GamePanel;

public class Obj_Portal extends Entity {

    GamePanel gp;

    public Obj_Portal(GamePanel gp) {
        super(gp);
        name = "Portal";
        down[1] = setup("/objects/portal");
        collision = true;
    }


}