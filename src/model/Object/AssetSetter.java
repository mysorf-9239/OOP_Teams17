package model.Object;

import view.GamePanel;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {

        this.gp = gp;
    }

    public void setObject() {
            //Map 0
            gp.obj[0][0] = new Obj_Axe(gp);
            gp.obj[0][0].worldX = 15*gp.titleSize;
            gp.obj[0][0].worldY = 488*gp.titleSize;

            gp.obj[0][1] = new Obj_Spidernet(gp);
            gp.obj[0][1].worldX = 17*gp.titleSize;
            gp.obj[0][1].worldY = 489*gp.titleSize;

            gp.obj[0][2] = new Obj_Spidernet(gp);
            gp.obj[0][2].worldX = 17*gp.titleSize;
            gp.obj[0][2].worldY = 485*gp.titleSize;

            //Map 1
            gp.obj[1][0] = new Obj_Axe(gp);
            gp.obj[1][0].worldX = 16*gp.titleSize;
            gp.obj[1][0].worldY = 20*gp.titleSize;

            gp.obj[1][1] = new Obj_Door(gp);
            gp.obj[1][1].worldX = 16*gp.titleSize;
            gp.obj[1][1].worldY = 29*gp.titleSize;

            //Map2
            gp.obj[2][0] = new Obj_Door(gp);
            gp.obj[2][0].worldX = 15*gp.titleSize;
            gp.obj[2][0].worldY = 29*gp.titleSize;

            //Map3
            gp.obj[3][0] = new Obj_Door(gp);
            gp.obj[3][0].worldX = 17*gp.titleSize;
            gp.obj[3][0].worldY = 29*gp.titleSize;
        }
}
