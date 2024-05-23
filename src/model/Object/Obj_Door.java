package model.Object;

import com.sun.source.tree.UsesTree;
import model.entity.Entity;
import view.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Door extends Entity {

    GamePanel gp;

    public Obj_Door(GamePanel gp) {
        super(gp);
        name = "Door";
        down[1] = setup("/objects/portal");
        collision = true;
    }


}