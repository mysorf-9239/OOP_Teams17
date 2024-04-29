package model.Object;

import view.GamePanel;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {

        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new Obj_Key();
        gp.obj[0].worldX = gp.titleSize * 29;
        gp.obj[0].worldY = gp.titleSize * 21;

        gp.obj[1] = new Obj_Key();
        gp.obj[1].worldX = gp.titleSize * 10;
        gp.obj[1].worldY = gp.titleSize * 15;

        gp.obj[2] = new Obj_Door();
        gp.obj[2].worldX = gp.titleSize * 30;
        gp.obj[2].worldY = gp.titleSize * 30;

        gp.obj[3] = new Obj_Chest();
        gp.obj[3].worldX = gp.titleSize * 30;
        gp.obj[3].worldY = gp.titleSize * 31;

        gp.obj[4] = new Obj_Boots();
        gp.obj[4].worldX = gp.titleSize * 15;
        gp.obj[4].worldY = gp.titleSize * 25;

    }


}
