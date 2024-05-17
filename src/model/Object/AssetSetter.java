package model.Object;

import view.GamePanel;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {

        this.gp = gp;
    }

    public void setObject() {
        switch (gp.currentMap) {
            case 0:
                Obj_Axe axe0 = new Obj_Axe(gp);
                axe0.worldX = 15*gp.titleSize;
                axe0.worldY = 488*gp.titleSize;
                break;
            case 1:
                Obj_Axe axe1 = new Obj_Axe(gp);
                axe1.worldX = 15*gp.titleSize;
                axe1.worldY = 20*gp.titleSize;
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }


}
