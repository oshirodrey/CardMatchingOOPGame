package CardGame.UI.Sound;

import CardGame.InterfaceAdapters.ISoundPlayer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SFXPlayer implements ISoundPlayer {
    private String sfxName;
    private File sfxFile;
    private AudioInputStream audioInputStream;
    private Clip clip;
    private boolean muted = false;
    private final Map<String, Clip> soundMap = new HashMap<>();
    public SFXPlayer() {

        load("cardFlip");
        load("clickButton");
    }

    private void load(String soundID) {
        try {
            String path = "/Sound/SFX/" + soundID + ".wav";
            InputStream is = getClass().getResourceAsStream(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(is);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            setVolume(clip, -1.0f);//slightly below full
            soundMap.put(soundID, clip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setVolume(Clip clip, float dB) {
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gain.setValue(dB);
        }
    }
    @Override
    public void play(String soundID) {
        if (muted) return;

        Clip clip = soundMap.get(soundID);

            if (clip.isRunning()) clip.stop(); // rewind if needed
            clip.setFramePosition(0);
            clip.start();

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
