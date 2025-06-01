package CardGame.UI.Sound;

import CardGame.InterfaceAdapters.ISoundPlayer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.InputStream;
import java.util.Random;

public class BGMPlayer implements ISoundPlayer {
    private String songName;
    private File songFile;
    private AudioInputStream audioInputStream;
    private Clip clip;
    private Random rand ;
    private boolean muted = false;
    public BGMPlayer() throws LineUnavailableException {
        rand = new Random();
        rand.setSeed(System.currentTimeMillis());
    }
    public void playRandom(String soundID) {
        if(muted) return;
        stop();

        int index = rand.nextInt(6)+1;
        songName = soundID+ index;
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

        String path = "/Sound/BGM/"+soundID+".wav";
        try {
            InputStream is = getClass().getResourceAsStream(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(is);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            setVolume(clip, -10.0f);//smaller than SFX
            clip.loop(Clip.LOOP_CONTINUOUSLY);
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
