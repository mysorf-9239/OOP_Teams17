package view;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];
    FloatControl floatControl;
    public int volumeScale = 3;
    Float volume;

    public Sound() {

        soundURL[0] = getClass().getResource("/sounds/BackSound.wav");
        soundURL[1] = getClass().getResource("/sounds/Coin.wav");
        soundURL[2] = getClass().getResource("/sounds/PowerUp.wav");
        soundURL[3] = getClass().getResource("/sounds/Unlock.wav");
        soundURL[4] = getClass().getResource("/sounds/Fanfare.wav");
        soundURL[5] = getClass().getResource("/sounds/PressKey.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            if (floatControl != null) {
                checkVolume();
            } else {
                System.err.println("Float control is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {

        clip.start();
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {

        if (clip != null) {
            clip.stop();
        }
    }

    public void checkVolume() {

        switch (volumeScale) {
            case 0: volume = -80.0f; break;
            case 1: volume = -20f; break;
            case 2: volume = -12f; break;
            case 3: volume = -5f; break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
            default: volume = 0f; break;
        }
        floatControl.setValue(volume);
    }

}
