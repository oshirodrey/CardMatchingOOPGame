package CardGame.UI.CustomizedComponents;

import CardGame.UI.MainFrame;

import javax.swing.*;
import java.awt.*;

public abstract class Screen extends JPanel {
    private final  int SCREEN_WIDTH ;
    private final  int SCREEN_HEIGHT ;

    private MainFrame parentFrame;


    protected Color customGreen = new Color(55,121,86);

    public Screen(int screenWidth, int screenHeight) {
        SCREEN_WIDTH = screenWidth;
        SCREEN_HEIGHT = screenHeight;
    }


    public MainFrame getParentFrame() {
        return this.parentFrame;
    }
    public void setParentFrame(MainFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    public abstract void init();



}
