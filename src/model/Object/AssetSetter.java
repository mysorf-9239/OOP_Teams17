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
                gp.obj[0] = new Obj_Axe(gp);
                gp.obj[0].worldX = 15*gp.titleSize;
                gp.obj[0].worldY = 488*gp.titleSize;
                break;
            case 1:
                gp.obj[0] = new Obj_Axe(gp);
                gp.obj[0].worldX = 16*gp.titleSize;
                gp.obj[0].worldY = 20*gp.titleSize;

                gp.obj[1] = new Obj_Door(gp);
                gp.obj[1].worldX = 16*gp.titleSize;
                gp.obj[1].worldY = 29*gp.titleSize;
                break;
            case 2:
                gp.obj[1] = new Obj_Door(gp);
                gp.obj[1].worldX = 15*gp.titleSize;
                gp.obj[1].worldY = 29*gp.titleSize;
                break;
            case 3:
                gp.obj[1] = new Obj_Door(gp);
                gp.obj[1].worldX = 17*gp.titleSize;
                gp.obj[1].worldY = 29*gp.titleSize;
                break;
            default:
                gp.deletedObject();
        }
    }


}
