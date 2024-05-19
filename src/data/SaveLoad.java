package data;

import model.Object.*;
import model.entity.Entity;
import model.entity.Player;
import view.GamePanel;

import java.io.*;

public class SaveLoad {

    GamePanel gp;

    public SaveLoad(GamePanel gp) {

        this.gp = gp;
    }

    public Entity getObject(String objName) {

        Entity obj = null;

        switch (objName) {
            case "Door": obj = new Obj_Door(gp); break;
            case "Axe": obj = new Obj_Axe(gp); break;
            case "Key": obj = new Obj_Key(gp); break;
            case "Boots": obj = new Obj_Boots(gp); break;
            case "Spidernet": obj = new Obj_Spidernet(gp); break;
        }

        return obj;
    }

    public void save() {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            //Read the DataStorage object
            DataStorage dataStorage = new DataStorage();

            //Player
            dataStorage.worldX = gp.player.worldX;
            dataStorage.worldY = gp.player.worldY;
            dataStorage.direction = gp.player.direction;
            dataStorage.speed = gp.player.speed;
            dataStorage.life = gp.player.life;
            dataStorage.characterNum = Player.characterNum;
            dataStorage.furthestY = Player.furthestY;
            dataStorage.hasKey = gp.player.hasKey;
            dataStorage.hasAxe = gp.player.hasAxe;

            //Object
            dataStorage.objectLength = new int[gp.maxMap];
            dataStorage.objectName = new String[gp.maxMap][gp.obj[1].length];
            dataStorage.objectX = new int[gp.maxMap][gp.obj[1].length];
            dataStorage.objectY = new int[gp.maxMap][gp.obj[1].length];
            dataStorage.objectCollision = new boolean[gp.maxMap][gp.obj[1].length];
            dataStorage.map = new int[gp.maxMap][40][500];

            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                dataStorage.objectLength[mapNum] = gp.obj[mapNum].length;
            }
            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {

                for (int i = 0; i < dataStorage.objectLength[mapNum]; i++) {

                    if (gp.obj[mapNum][i] == null) {
                        dataStorage.objectName[mapNum][i] = "NA";
                    }else {
                        dataStorage.objectName[mapNum][i] = gp.obj[mapNum][i].name;
                        dataStorage.objectX[mapNum][i] = gp.obj[mapNum][i].worldX;
                        dataStorage.objectY[mapNum][i] = gp.obj[mapNum][i].worldY;
                        dataStorage.objectCollision[mapNum][i] = gp.obj[mapNum][i].collision;
                    }
                }
            }

            //GP
            dataStorage.currentMap = GamePanel.currentMap;
            dataStorage.totalScore = GamePanel.totalScore;

            //Poison
            dataStorage.poisonMistY = gp.poisonMist.PoisonMistY;
            dataStorage.poisonMistSpeed = gp.poisonMist.PoisonMistSpeed;

            //Tiled
            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < 40; i++) {
                    for (int j = (mapNum == 0 ? 500 : 40); j-- > 0;) {
                        dataStorage.map[mapNum][i][j] = GamePanel.map[mapNum][i][j];
                    }
                }
            }

            //Write file .dat
            oos.writeObject(dataStorage);

        } catch (IOException e) {
            System.out.println("Save Exception");
        }

    }

    public void load() {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            // Read the DataStorage object
            DataStorage dataStorage = (DataStorage)ois.readObject();

            // Player
            gp.player.worldX = dataStorage.worldX;
            gp.player.worldY = dataStorage.worldY;
            gp.player.direction = dataStorage.direction;
            gp.player.speed = dataStorage.speed;
            gp.player.life = dataStorage.life;
            Player.characterNum = dataStorage.characterNum;
            Player.furthestY = dataStorage.furthestY;
            gp.player.hasKey = dataStorage.hasKey;
            gp.player.hasAxe = dataStorage.hasAxe;

            // Object
            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < dataStorage.objectLength[mapNum]; i++) {
                    gp.obj[mapNum][i] = null;
                }
            }

            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < dataStorage.objectLength[mapNum]; i++) {
                    if (dataStorage.objectName[mapNum][i].equals("NA")) {
                        gp.obj[mapNum][i] = null;
                    } else {
                        // Create a new object based on its name
                        gp.obj[mapNum][i] = getObject(dataStorage.objectName[mapNum][i]);
                        if (gp.obj[mapNum][i] != null) {
                            gp.obj[mapNum][i].worldX = dataStorage.objectX[mapNum][i];
                            gp.obj[mapNum][i].worldY = dataStorage.objectY[mapNum][i];
                            gp.obj[mapNum][i].collision = dataStorage.objectCollision[mapNum][i];
                        }
                    }
                }
            }

            // GP
            GamePanel.currentMap = dataStorage.currentMap;
            GamePanel.totalScore = dataStorage.totalScore;

            //Poison
            gp.poisonMist.PoisonMistY = dataStorage.poisonMistY;
            gp.poisonMist.PoisonMistSpeed = dataStorage.poisonMistSpeed;

            //Tiled
            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < 40; i++) {
                    for (int j = (mapNum == 0 ? 500 : 40); j-- > 0;) {
                        GamePanel.map[mapNum][i][j] = dataStorage.map[mapNum][i][j];
                    }
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Load Exception");
            e.printStackTrace();
        }
    }

}
