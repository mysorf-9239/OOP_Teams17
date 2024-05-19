package data;

import model.entity.Entity;

import java.io.Serializable;

public class DataStorage implements Serializable {

    //Player's properties
    int worldX, worldY;
    String direction;
    int speed;
    int life;
    int characterNum;
    int furthestY;
    int hasKey;
    int hasAxe;

    //Game Panel
    int currentMap;
    int totalScore;

    //Object
    int[] objectLength;
    String[][] objectName;
    int[][] objectX;
    int[][] objectY;
    boolean[][] objectCollision;

    //PoisonMist
    int poisonMistY;
    int poisonMistSpeed;

    //Tiled
    int[][][] map;


}
