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

            gp.obj[0][1] = new Obj_Hole(gp);
            gp.obj[0][1].worldX = 17*gp.titleSize;
            gp.obj[0][1].worldY = 489*gp.titleSize;

            gp.obj[0][2] = new Obj_HP(gp);
            gp.obj[0][2].worldX = 17*gp.titleSize;
            gp.obj[0][2].worldY = 486*gp.titleSize;

            //Map 1
            gp.obj[1][0] = new Obj_Axe(gp);
            gp.obj[1][0].worldX = 16*gp.titleSize;
            gp.obj[1][0].worldY = 20*gp.titleSize;

            gp.obj[1][1] = new Obj_Portal(gp);
            gp.obj[1][1].worldX = 9*gp.titleSize;
            gp.obj[1][1].worldY = 39*gp.titleSize;

            //Map2
            gp.obj[2][0] = new Obj_Portal(gp);
            gp.obj[2][0].worldX = gp.titleSize;
            gp.obj[2][0].worldY = 38*gp.titleSize;

            gp.obj[2][1] = new Obj_Axe(gp);
            gp.obj[2][1].worldX = gp.titleSize;
            gp.obj[2][1].worldY = 22*gp.titleSize;

            gp.obj[2][2] = new Obj_Axe(gp);
            gp.obj[2][2].worldX = 38*gp.titleSize;
            gp.obj[2][2].worldY = 29*gp.titleSize;

            //Map3
            gp.obj[3][0] = new Obj_Portal(gp);
            gp.obj[3][0].worldX = 17*gp.titleSize;
            gp.obj[3][0].worldY = 29*gp.titleSize;

            //Map4
            gp.obj[4][0] = new Obj_Portal(gp);
            gp.obj[4][0].worldX = 18*gp.titleSize;
            gp.obj[4][0].worldY = 30*gp.titleSize;

            //Map5
            gp.obj[5][0] = new Obj_Portal(gp);
            gp.obj[5][0].worldX = 21*gp.titleSize;
            gp.obj[5][0].worldY = 29*gp.titleSize;

            //Map6
            gp.obj[6][0] = new Obj_Portal(gp);
            gp.obj[6][0].worldX = 19*gp.titleSize;
            gp.obj[6][0].worldY = 28*gp.titleSize;

            //Map7
            gp.obj[7][0] = new Obj_Portal(gp);
            gp.obj[7][0].worldX = 21*gp.titleSize;
            gp.obj[7][0].worldY = 29*gp.titleSize;

            //Map8
            gp.obj[8][0] = new Obj_Portal(gp);
            gp.obj[8][0].worldX = 21*gp.titleSize;
            gp.obj[8][0].worldY = 29*gp.titleSize;

            //Map9
            gp.obj[9][0] = new Obj_Portal(gp);
            gp.obj[9][0].worldX = 39*gp.titleSize;
            gp.obj[9][0].worldY = 4*gp.titleSize;

            gp.obj[9][1] = new Obj_Portal(gp);
            gp.obj[9][1].worldX = 0;
            gp.obj[9][1].worldY = 35*gp.titleSize;

            //Map10
            gp.obj[10][0] = new Obj_Portal(gp);
            gp.obj[10][0].worldX = 6*gp.titleSize;
            gp.obj[10][0].worldY = 9*gp.titleSize;

            //Map11
            gp.obj[11][0] = new Obj_Portal(gp);
            gp.obj[11][0].worldX = 18*gp.titleSize;
            gp.obj[11][0].worldY = 37*gp.titleSize;

            //Map12
            gp.obj[12][0] = new Obj_Portal(gp);
            gp.obj[12][0].worldX = 38*gp.titleSize;
            gp.obj[12][0].worldY = 38*gp.titleSize;

            //Map13
            gp.obj[13][0] = new Obj_Portal(gp);
            gp.obj[13][0].worldX = 38*gp.titleSize;
            gp.obj[13][0].worldY = 38*gp.titleSize;

            //Map14
            gp.obj[14][0] = new Obj_Portal(gp);
            gp.obj[14][0].worldX = 38*gp.titleSize;
            gp.obj[14][0].worldY = 38*gp.titleSize;

            //Map15
            gp.obj[15][0] = new Obj_Portal(gp);
            gp.obj[15][0].worldX = 38*gp.titleSize;
            gp.obj[15][0].worldY = 38*gp.titleSize;

            //Map16
            gp.obj[16][0] = new Obj_Portal(gp);
            gp.obj[16][0].worldX = 6*gp.titleSize;
            gp.obj[16][0].worldY = 9*gp.titleSize;

            //Map17
            gp.obj[17][0] = new Obj_Portal(gp);
            gp.obj[17][0].worldX = 6*gp.titleSize;
            gp.obj[17][0].worldY = 9*gp.titleSize;

            //Map18
            gp.obj[18][0] = new Obj_Portal(gp);
            gp.obj[18][0].worldX = 6*gp.titleSize;
            gp.obj[18][0].worldY = 9*gp.titleSize;

            //Map19
            gp.obj[19][0] = new Obj_Portal(gp);
            gp.obj[19][0].worldX = 6*gp.titleSize;
            gp.obj[19][0].worldY = 9*gp.titleSize;

            //Map20
            gp.obj[20][0] = new Obj_Portal(gp);
            gp.obj[20][0].worldX = 6*gp.titleSize;
            gp.obj[20][0].worldY = 9*gp.titleSize;
        }
}
