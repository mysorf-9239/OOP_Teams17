package model.Object;

import model.entity.Entity;
import view.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Chest extends Entity {

    GamePanel gp;

    public Obj_Chest(GamePanel gp) {

        super(gp);
        name = "Chest";
        down[1] = setup("/objects/Chess.png");

    }


}