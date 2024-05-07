package controller;

import view.GamePanel;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][];
    int eventRectDefaultX, eventRectDefaultY;

    //Poison damage
    private long poisonDamageTime = System.currentTimeMillis();
    private final long DAMAGE_INTERVAL = 1000;


    public EventHandler(GamePanel gp) {

        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 12;
            eventRect[col][row].y = 12;
            eventRect[col][row].width = 24;
            eventRect[col][row].height = 24;
            eventRectDefaultX = eventRect[col][row].width;
            eventRectDefaultY = eventRect[col][row].height;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }

    }

    public void checkEvent() {
        //Event

    }

    public boolean hit(int col, int row) {

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col*gp.titleSize + eventRect[col][row].x;
        eventRect[col][row].y = row*gp.titleSize + eventRect[col][row].y;

        if (gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) { hit = true;}

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    public void damagePit(int col, int row) {
        gp.player.life -= 1;
        eventRect[col][row].eventDone = true;
    }

    public void healPit(int col, int row) {
        gp.player.life += 1;
        eventRect[col][row].eventDone = true;
    }


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




























