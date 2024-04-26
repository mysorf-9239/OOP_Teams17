package view;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound() {

        soundURL[0] = getClass().getResource("/sounds/BackSound.wav");
        soundURL[1] = getClass().getResource("/sounds/Coin.wav");
        soundURL[2] = getClass().getResource("/sounds/PowerUp.wav");
        soundURL[3] = getClass().getResource("/sounds/Unlock.wav");
        soundURL[4] = getClass().getResource("/sounds/Fanfare.wav");
    }

    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public void play() {

        clip.start();
    }

    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {

        clip.stop();
    }

}
