package model.Object;

import model.entity.Entity;
import view.GamePanel;


public class Obj_Key extends Entity {

    GamePanel gp;

    public Obj_Key(GamePanel gp) {
        super(gp);

        name = "Key";
        down[1] = setup("/objects/key");

    }


}
