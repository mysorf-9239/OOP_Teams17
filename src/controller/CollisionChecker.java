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
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height - entity.solidArea.y/4;

        int entityLeftCol = entityLeftWorldX/gp.titleSize;
        int entityRightCol = entityRightWorldX/gp.titleSize;
        int entityTopRow = entityTopWorldY/gp.titleSize;
        int entityBottomRow = entityBottomWorldY/gp.titleSize;

        int tileNumber1, tileNumber2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY- entity.speed)/gp.titleSize;
                tileNumber1 = GamePanel.map[GamePanel.currentMap][entityLeftCol][entityTopRow];
                tileNumber2 = GamePanel.map[GamePanel.currentMap][entityRightCol][entityTopRow];
                if (gp.tileManager.tile[tileNumber1].collision || gp.tileManager.tile[tileNumber2].collision) {
                    entity.collidisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.titleSize;
                tileNumber1 = GamePanel.map[GamePanel.currentMap][entityLeftCol][entityBottomRow];
                tileNumber2 = GamePanel.map[GamePanel.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileManager.tile[tileNumber1].collision || gp.tileManager.tile[tileNumber2].collision) {
                    entity.collidisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX- entity.speed)/gp.titleSize;
                tileNumber1 = GamePanel.map[GamePanel.currentMap][entityLeftCol][entityTopRow];
                tileNumber2 = GamePanel.map[GamePanel.currentMap][entityLeftCol][entityBottomRow];
                if (gp.tileManager.tile[tileNumber1].collision || gp.tileManager.tile[tileNumber2].collision) {
                    entity.collidisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.titleSize;
                tileNumber1 = GamePanel.map[GamePanel.currentMap][entityRightCol][entityTopRow];
                tileNumber2 = GamePanel.map[GamePanel.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileManager.tile[tileNumber1].collision || gp.tileManager.tile[tileNumber2].collision) {
                    entity.collidisionOn = true;
                }
                break;

        }

    }

    public int CheckObject(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gp.obj[GamePanel.currentMap].length; i++) {

            if (gp.obj[GamePanel.currentMap][i] != null)
            {//entity solid
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //obj solid
                gp.obj[GamePanel.currentMap][i].solidArea.x = gp.obj[GamePanel.currentMap][i].worldX + gp.obj[GamePanel.currentMap][i].solidArea.x;
                gp.obj[GamePanel.currentMap][i].solidArea.y = gp.obj[GamePanel.currentMap][i].worldY + gp.obj[GamePanel.currentMap][i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[GamePanel.currentMap][i].solidArea)) {
                            if (gp.obj[GamePanel.currentMap][i].collision) {
                                entity.collidisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[GamePanel.currentMap][i].solidArea)) {
                            if (gp.obj[GamePanel.currentMap][i].collision) {
                                entity.collidisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[GamePanel.currentMap][i].solidArea)) {
                            if (gp.obj[GamePanel.currentMap][i].collision) {
                                entity.collidisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[GamePanel.currentMap][i].solidArea)) {
                            if (gp.obj[GamePanel.currentMap][i].collision) {
                                entity.collidisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[GamePanel.currentMap][i].solidArea.x = gp.obj[GamePanel.currentMap][i].solidAreaDefaultX;
                gp.obj[GamePanel.currentMap][i].solidArea.y = gp.obj[GamePanel.currentMap][i].solidAreaDefaultY;

            }
        }

        return index;
    }

}
