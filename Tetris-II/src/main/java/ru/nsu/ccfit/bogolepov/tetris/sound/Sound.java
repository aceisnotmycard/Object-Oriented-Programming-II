package ru.nsu.ccfit.bogolepov.tetris.sound;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Created by aceisnotmycard on 5/2/15.
 */
public class Sound {
    AudioStream music;
    AudioPlayer myBackgroundPlayer = AudioPlayer.player;
    ContinuousAudioDataStream myLoop = null;

    public Sound(String filename) {
        try {
            music = new AudioStream(getClass().getResourceAsStream(filename));
        } catch (IOException e) {
            music = null;
        }
    }

    public void play() {
        if (music != null) {
            try {
                AudioData data = music.getData();
                myLoop = new ContinuousAudioDataStream(data);
                myBackgroundPlayer.start(myLoop);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
