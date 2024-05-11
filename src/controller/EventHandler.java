package controller;

import view.GamePanel;

public class EventHandler {

    GamePanel gp;
//    EventRect eventRect[][][];
//    int eventRectDefaultX, eventRectDefaultY;

    //Poison damage
    private long poisonDamageTime = System.currentTimeMillis();
    private final long DAMAGE_INTERVAL = 1000;


    public EventHandler(GamePanel gp) {

        this.gp = gp;

//        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
//
//        int col = 0;
//        int row = 0;
//        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
//            eventRect[gp.currentMap][col][row] = new EventRect();
//            eventRect[gp.currentMap][col][row].x = 12;
//            eventRect[gp.currentMap][col][row].y = 12;
//            eventRect[gp.currentMap][col][row].width = 24;
//            eventRect[gp.currentMap][col][row].height = 24;
//            eventRectDefaultX = eventRect[gp.currentMap][col][row].width;
//            eventRectDefaultY = eventRect[gp.currentMap][col][row].height;
//
//            col++;
//            if (col == gp.maxWorldCol) {
//                col = 0;
//                row++;
//            }
//        }

    }

    public void checkEvent() {
        //Event

    }

    public boolean hit(int col, int row) {

        boolean hit = false;

//        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
//        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
//        eventRect[gp.currentMap][col][row].x = col*gp.titleSize + eventRect[gp.currentMap][col][row].x;
//        eventRect[gp.currentMap][col][row].y = row*gp.titleSize + eventRect[gp.currentMap][col][row].y;
//
//        if (gp.player.solidArea.intersects(eventRect[gp.currentMap][col][row]) && eventRect[gp.currentMap][col][row].eventDone == false) { hit = true;}
//
//        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
//        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
//        eventRect[gp.currentMap][col][row].x = eventRect[gp.currentMap][col][row].eventRectDefaultX;
//        eventRect[gp.currentMap][col][row].y = eventRect[gp.currentMap][col][row].eventRectDefaultY;

        return hit;
    }

//    public void damagePit(int col, int row) {
//        gp.player.life -= 1;
//        eventRect[gp.currentMap][col][row].eventDone = true;
//    }
//
//    public void healPit(int col, int row) {
//        gp.player.life += 1;
//        eventRect[gp.currentMap][col][row].eventDone = true;
//    }


    public void poisonMistCheck() {

        if (gp.player.worldY > gp.poisonMist.PoisonMistY) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - poisonDamageTime >= DAMAGE_INTERVAL) {
                gp.player.life -= 1;
                poisonDamageTime = currentTime;
            }
        }
    }

}




























