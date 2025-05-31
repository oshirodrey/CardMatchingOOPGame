package CardGame.UI.Sound;

import CardGame.InterfaceAdapters.ISoundPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.InputStream;
import java.util.Random;

public class SFXPlayer implements ISoundPlayer {
    private String sfxName;
    private File sfxFile;
    private AudioInputStream audioInputStream;
    private Clip clip;
    private boolean muted = false;
    @Override
    public void play(String soundID) {
        stop();

        String path = "/Sound/SFX/"+soundID+".wav";
        try {
            InputStream is = getClass().getResourceAsStream(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(is);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            if (!muted) clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void stop() {
        if(clip != null && clip.isRunning()){
            clip.stop();
            clip.close();
        }

    }

    @Override
    public void setMuted(boolean mute) {
        this.muted = mute;
        if (clip != null) {
            if (mute) clip.stop(); else clip.start();
        }
    }
}
