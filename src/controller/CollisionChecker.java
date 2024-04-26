package controller;

import model.entity.Entity;
import view.GamePanel;

public class CollisionChecker {

    GamePanel gp;

    public  CollisionChecker (GamePanel gp) {

        this.gp = gp;

    }

    public void CheckTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.titleSize;
        int entityRightCol = entityRightWorldX/gp.titleSize;
        int entityTopRow = entityTopWorldY/gp.titleSize;
        int entityBottomRow = entityBottomWorldY/gp.titleSize;

        int tileNumber1, tileNumber2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY- entity.speed)/gp.titleSize;
                tileNumber1 = GamePanel.map[entityLeftCol][entityTopRow];
                tileNumber2 = GamePanel.map[entityRightCol][entityTopRow];
                if (gp.tileManager.tile[tileNumber1].collision == true || gp.tileManager.tile[tileNumber2].collision == true) {
                    entity.collidisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.titleSize;
                tileNumber1 = GamePanel.map[entityLeftCol][entityBottomRow];
                tileNumber2 = GamePanel.map[entityRightCol][entityBottomRow];
                if (gp.tileManager.tile[tileNumber1].collision == true || gp.tileManager.tile[tileNumber2].collision == true) {
                    entity.collidisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX- entity.speed)/gp.titleSize;
                tileNumber1 = GamePanel.map[entityLeftCol][entityTopRow];
                tileNumber2 = GamePanel.map[entityLeftCol][entityBottomRow];
                if (gp.tileManager.tile[tileNumber1].collision == true || gp.tileManager.tile[tileNumber2].collision == true) {
                    entity.collidisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.titleSize;
                tileNumber1 = GamePanel.map[entityRightCol][entityTopRow];
                tileNumber2 = GamePanel.map[entityRightCol][entityBottomRow];
                if (gp.tileManager.tile[tileNumber1].collision == true || gp.tileManager.tile[tileNumber2].collision == true) {
                    entity.collidisionOn = true;
                }
                break;

        }

    }

    public int CheckObject(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {

            if (gp.obj[i] != null)
            {//entity solid
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //obj solid
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
                                entity.collidisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
                                entity.collidisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
                                entity.collidisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
                                entity.collidisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;

            }

        }

        return index;
    }


}
