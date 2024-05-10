package controller;

import model.tile.TileEndlessManager;
import view.GamePanel;

import java.awt.image.BufferedImage;
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
            bufferedWriter.write(String.valueOf(TileEndlessManager.pathNum));
            bufferedWriter.newLine();

            //Character


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
            TileEndlessManager.pathNum = Integer.parseInt(s);

            //Character




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
            TileEndlessManager.pathNum = Integer.parseInt(s);

            //Close Config
            bufferedReader.close();

        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
