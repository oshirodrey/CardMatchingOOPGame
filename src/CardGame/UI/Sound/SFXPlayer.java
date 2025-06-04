package CardGame.UI.Sound;

import CardGame.InterfaceAdapters.ISoundPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SFXPlayer implements ISoundPlayer {
    private final Map<String, Clip> soundMap = new HashMap<>();
    private String sfxName;
    private File sfxFile;
    private AudioInputStream audioInputStream;
    private Clip clip;
    private boolean muted = false;

    public SFXPlayer() {

        load("cardFlip");
        load("clickButton");
    }

    private void load(String soundID) {

        try {
            String path = "/res/Sound/SFX/" + soundID + ".wav";
            File soundFile = getSoundFile(path);
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
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
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }

    }

    @Override
    public void setMuted(boolean mute) {
        this.muted = mute;
        if (clip != null) {
            if (mute) clip.stop();
            else clip.start();
        }
    }

    private File getSoundFile(String relativePath) {
        try {
            // Where the class is loaded from — either .class dir or .jar
            File baseDir = new File(SFXPlayer.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI()).getParentFile();

            File candidate = new File(baseDir, relativePath);
            if (candidate.exists()) {
                return candidate;
            }

            // Fallback: look relative to working directory (for IntelliJ)
            String workingDir = System.getProperty("user.dir");
            File fallback = new File(workingDir,relativePath);
            if (fallback.exists()) {
                return fallback;
            }

            throw new FileNotFoundException("Sound file not found in either JAR dir or working dir: " + relativePath);

        } catch (Exception e) {
            throw new RuntimeException("Could not resolve sound path", e);
        }
    }
}
