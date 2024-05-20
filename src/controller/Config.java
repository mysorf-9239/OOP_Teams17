package controller;

import model.entity.Player;
import model.tile.TileManager;
import view.GamePanel;

import java.io.*;

public class Config {

    GamePanel gp;

    public Config (GamePanel gp) {

        this.gp = gp;
    }

    public void saveConfig() {

        try {
            //Load config.txt
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("config.txt"));

            //Music
            bufferedWriter.write(String.valueOf(gp.music.volumeScale));
            bufferedWriter.newLine();

            //SE
            bufferedWriter.write(String.valueOf(gp.se.volumeScale));
            bufferedWriter.newLine();

            //Mode
            bufferedWriter.write(String.valueOf(gp.gameMode));
            bufferedWriter.newLine();

            //Map
            bufferedWriter.write(String.valueOf(GamePanel.currentMap));
            bufferedWriter.newLine();

            //Character
            bufferedWriter.write(String.valueOf(Player.characterNum));
            bufferedWriter.newLine();

            //Highest Map
            bufferedWriter.write(String.valueOf(GamePanel.highestMap));
            bufferedWriter.newLine();

            //Highest Score

            int disScore = (GamePanel.maxWorldRow - 11 - Player.furthestY /gp.titleSize)* GamePanel.DISTANCE_REWARD;
            int Score = GamePanel.totalScore + disScore;

            if (Score > GamePanel.highestScore) {
                bufferedWriter.write(String.valueOf(Score));
                bufferedWriter.newLine();
            } else {
                bufferedWriter.write(String.valueOf(GamePanel.highestScore));
                bufferedWriter.newLine();
            }

            //Close config.txt
            bufferedWriter.close();


        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public void loadConfig() {

        try {
            //Load config.txt
            BufferedReader bufferedReader = new BufferedReader(new FileReader("config.txt"));

            String s = bufferedReader.readLine();

            //Music
            gp.music.volumeScale = Integer.parseInt(s);

            //SE
            s = bufferedReader.readLine();
            gp.se.volumeScale = Integer.parseInt(s);

            //Mode
            s = bufferedReader.readLine();
            gp.gameMode = Integer.parseInt(s);

            //Map
            s = bufferedReader.readLine();
            GamePanel.currentMap = Integer.parseInt(s);

            //Character
            s = bufferedReader.readLine();
            Player.characterNum = Integer.parseInt(s);

            //Highest Map
            s = bufferedReader.readLine();
            GamePanel.highestMap = Integer.parseInt(s);

            //Highest Score
            s = bufferedReader.readLine();
            GamePanel.highestScore = Integer.parseInt(s);

            //Close Config
            bufferedReader.close();

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public void getMap() {

        try {
            //Load config.txt
            BufferedReader bufferedReader = new BufferedReader(new FileReader("config.txt"));

            String s = bufferedReader.readLine();
            s = bufferedReader.readLine();
            s = bufferedReader.readLine();

            //Map
            s = bufferedReader.readLine();
            gp.currentMap = Integer.parseInt(s);

            //Close Config
            bufferedReader.close();

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public void getCharacter() {
        try {
            //Load config.txt
            BufferedReader bufferedReader = new BufferedReader(new FileReader("config.txt"));

            String s = bufferedReader.readLine();
            s = bufferedReader.readLine();
            s = bufferedReader.readLine();
            s = bufferedReader.readLine();

            //Map
            s = bufferedReader.readLine();
            Player.characterNum = Integer.parseInt(s);

            //Close Config
            bufferedReader.close();

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public void getHighestMap() {

        try {
            //Load config.txt
            BufferedReader bufferedReader = new BufferedReader(new FileReader("config.txt"));

            String s = bufferedReader.readLine();
            s = bufferedReader.readLine();
            s = bufferedReader.readLine();
            s = bufferedReader.readLine();
            s = bufferedReader.readLine();

            //Map
            s = bufferedReader.readLine();
            gp.highestMap = Integer.parseInt(s);

            //Close Config
            bufferedReader.close();

        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
