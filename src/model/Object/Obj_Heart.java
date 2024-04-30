package model.Object;

import model.entity.Entity;
import view.GamePanel;

public class Obj_Heart extends Entity {

    GamePanel gp;

    public Obj_Heart(GamePanel gp) {
        super(gp);

        name = "Key";

        image = setup("/objects/heart_full");
        image2 = setup("/objects/heart_half");
        image3 = setup("/objects/heart_blank");

    }
}
