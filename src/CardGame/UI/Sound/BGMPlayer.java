package CardGame.UI.Sound;

import CardGame.InterfaceAdapters.ISoundPlayer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;

public class BGMPlayer implements ISoundPlayer {
    private final Random rand;
    private String songName;
    private File songFile;
    private AudioInputStream audioInputStream;
    private Clip clip;
    private boolean muted = false;

    public BGMPlayer() throws LineUnavailableException {
        rand = new Random();
        rand.setSeed(System.currentTimeMillis());
    }

    public void playRandom(String soundID) {
        if (muted) return;
        stop();

        int index = rand.nextInt(6) + 1;
        songName = soundID + index;
        play(songName);
    }

    private void setVolume(Clip clip, float dB) {
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gain.setValue(dB);
        }
    }

    @Override
    public void play(String soundID) {
        stop();



        try {
            String path = "/res/Sound/BGM/" + soundID + ".wav";
            File soundFile = getSoundFile(path);
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            setVolume(clip, -10.0f);//smaller than SFX
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            if (!muted) clip.start();
        } catch (Exception e) {
            throw new RuntimeException("Unable to locate JAR directory", e);
        }
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
