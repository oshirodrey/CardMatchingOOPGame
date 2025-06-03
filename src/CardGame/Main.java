package CardGame;

import CardGame.UI.MainFrame;
import CardGame.UI.TitleScreen;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        MainFrame frame = new MainFrame(new Dimension(800, 600));
        frame.setResizable(false);

        TitleScreen ts = new TitleScreen(frame);
        ts.init();
        ts.setVisible(true);
        frame.add(ts);
        frame.setCurrentScreen(ts);
        frame.setVisible(true);
    }
}