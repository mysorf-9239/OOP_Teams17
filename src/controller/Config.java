package controller;

import model.entity.Player;
import view.GamePanel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {

    private static final Logger LOGGER = Logger.getLogger(Config.class.getName());
    private static final String CONFIG_FILE = "config.txt";
    private static final String TEMP_CONFIG_FILE = "config.tmp";
    public static volatile boolean hasSeenIntro = false;

    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
        loadConfig();
    }

    public synchronized void saveConfig() {
        Path tempFilePath = Path.of(TEMP_CONFIG_FILE);
        Path configFilePath = Path.of(CONFIG_FILE);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(tempFilePath)) {
            // Intro
            bufferedWriter.write(Boolean.toString(hasSeenIntro));
            bufferedWriter.newLine();

            // Music
            bufferedWriter.write(String.valueOf(gp.music.volumeScale));
            bufferedWriter.newLine();

            // SE
            bufferedWriter.write(String.valueOf(gp.se.volumeScale));
            bufferedWriter.newLine();

            // Mode
            bufferedWriter.write(String.valueOf(gp.gameMode));
            bufferedWriter.newLine();

            // Map
            bufferedWriter.write(String.valueOf(GamePanel.currentMap));
            bufferedWriter.newLine();

            // Character
            bufferedWriter.write(String.valueOf(Player.characterNum));
            bufferedWriter.newLine();

            // High Map
            bufferedWriter.write(String.valueOf(GamePanel.highestMap));
            bufferedWriter.newLine();

            int disScore = (GamePanel.maxWorldRow - 11 - Player.furthestY / gp.titleSize) * GamePanel.DISTANCE_REWARD;
            int currentScore = GamePanel.totalScore + disScore;

            bufferedWriter.write(String.valueOf(Math.max(currentScore, GamePanel.highestScore)));
            bufferedWriter.newLine();

            // Ensure the config file is deleted if it exists, then move the temp file to the config file
            Files.deleteIfExists(configFilePath);
            Files.move(tempFilePath, configFilePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error saving config", e);
        }
    }

    public void loadConfig() {
        File configFile = new File(CONFIG_FILE);
        if (!configFile.exists()) {
            LOGGER.log(Level.WARNING, "Config file not found, using default settings");
            return;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(configFile))) {
            hasSeenIntro = readBoolean(bufferedReader);
            gp.music.volumeScale = readInt(bufferedReader);
            gp.se.volumeScale = readInt(bufferedReader);
            gp.gameMode = readInt(bufferedReader);
            GamePanel.currentMap = readInt(bufferedReader);
            Player.characterNum = readInt(bufferedReader);
            GamePanel.highestMap = readInt(bufferedReader);
            GamePanel.highestScore = readInt(bufferedReader);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading config", e);
        }
    }

    public void getCharacter() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CONFIG_FILE))) {
            skipLines(bufferedReader, 4);
            Player.characterNum = readInt(bufferedReader);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error getting character", e);
        }
    }

    private int readInt(BufferedReader bufferedReader) throws IOException {
        String line = bufferedReader.readLine();
        return line != null ? Integer.parseInt(line) : 0;
    }

    private boolean readBoolean(BufferedReader bufferedReader) throws IOException {
        String line = bufferedReader.readLine();
        return line != null && Boolean.parseBoolean(line);
    }

    private void skipLines(BufferedReader bufferedReader, int numberOfLines) throws IOException {
        for (int i = 0; i < numberOfLines; i++) {
            bufferedReader.readLine();
        }
    }

    public boolean hasSeenIntro() {
        return hasSeenIntro;
    }

    public synchronized void setHasSeenIntro(boolean hasSeenIntro) {
        Config.hasSeenIntro = hasSeenIntro;
        saveConfig();
    }
}
