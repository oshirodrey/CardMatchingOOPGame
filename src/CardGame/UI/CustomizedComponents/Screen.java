package CardGame.UI.CustomizedComponents;

import CardGame.UI.MainFrame;
import CardGame.UI.Sound.BGMPlayer;
import CardGame.UI.Sound.SFXPlayer;

import javax.swing.*;
import java.awt.*;

public abstract class Screen extends JPanel {

    protected BGMPlayer bgmPlayer;
    protected SFXPlayer sfxPlayer;
    protected Color customPink = new Color(255, 200, 221);
    protected Color customPink2 = new Color(249, 194, 226);
    protected Color customBlue = new Color(189, 224, 254);
    private MainFrame parentFrame;

    public Screen() {

    }


    public MainFrame getParentFrame() {
        return this.parentFrame;
    }

    public void setParentFrame(MainFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    /**
     * Initializes the screen layout.
     */
    public abstract void init();

    public void onExit() {
        if (bgmPlayer != null) {
            bgmPlayer.stop();
        }
        if (sfxPlayer != null) {
            sfxPlayer.stop();
        }
    }


}
