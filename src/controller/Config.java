package controller;

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

            //Close Config
            bufferedReader.close();

        } catch (IOException e) {
            e.getStackTrace();
        }

    }
}
