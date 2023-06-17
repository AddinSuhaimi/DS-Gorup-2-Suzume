/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maingame;

import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author 123456
 */
public class Sound {
   File[] soundFiles = new File[4];
    Clip[] clips = new Clip[4];

    public Sound() {
        soundFiles[0] = new File("BGM.wav");
        soundFiles[1] = new File("battle.wav");
        soundFiles[2] = new File("win.wav");
        soundFiles[3] = new File("gameover.wav");

        try {
            for (int i = 0; i < soundFiles.length; i++) {
                AudioInputStream ais = AudioSystem.getAudioInputStream(soundFiles[i]);
                clips[i] = AudioSystem.getClip();
                clips[i].open(ais);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setFile(int i){
       if (i >= 0 && i < clips.length) {
            Clip clip = clips[i];
            if (clip != null) {
                clip.stop();
                clip.setFramePosition(0);
            }
        }
    }
    public void play() {
        if (clips[0] != null) {
            clips[0].loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void loop() {
        for (Clip clip : clips) {
            if (clip != null) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }
    }

    public void stop() {
        for (Clip clip : clips) {
            if (clip != null) {
                clip.stop();
            }
        }
    }

    public void playSE(int i) {
        if (i > 0 && i < clips.length && clips[i] != null) {
            Clip clip = clips[i];
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            clip.start();
        }
    }
}
