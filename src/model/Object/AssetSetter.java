package model.Object;

import model.entity.Monster;
import view.GamePanel;

import java.awt.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {

        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new Obj_Door(gp);
        gp.obj[0].worldX = 0;
        gp.obj[0].worldY = 0;
    }

    public void setMonster() {

        if (GamePanel.currentMap == 1){
            gp.monster[0] = new Monster(gp);
            gp.monster[0].direction = "left";
            gp.monster[0].worldX = gp.titleSize*22;
            gp.monster[0].worldY = gp.titleSize*29;

            gp.monster[1] = new Monster(gp);
            gp.monster[1].direction = "right";
            gp.monster[1].worldX = gp.titleSize*24;
            gp.monster[1].worldY = gp.titleSize*29;
        }
    }


}
