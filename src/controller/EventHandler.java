package controller;

import view.GamePanel;

public class EventHandler {

    GamePanel gp;

    //Poison damage
    private long poisonDamageTime = System.currentTimeMillis();


    public EventHandler(GamePanel gp) {

        this.gp = gp;
    }

    public void checkEvent() {
        //Event

    }

    public void poisonMistCheck() {

        if (GamePanel.currentMap == 0) {
            if (gp.player.worldY > gp.poisonMist.PoisonMistY) {
                long currentTime = System.currentTimeMillis();
                long DAMAGE_INTERVAL = 1000;
                if (currentTime - poisonDamageTime >= DAMAGE_INTERVAL) {
                    gp.player.life -= 1;
                    poisonDamageTime = currentTime;
                }
            }
        }
    }
}




























