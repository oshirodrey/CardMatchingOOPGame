package CardGame;

import CardGame.Domain.Entities.Score;
import CardGame.InterfaceAdapters.Controller.GameController;
import CardGame.UI.MainFrame;
import CardGame.UI.TitleScreen;
import CardGame.UI.WinScreen;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        MainFrame frame = new MainFrame(new Dimension(800, 600));
        frame.setResizable(false);

        TitleScreen ts = new TitleScreen(frame);
        ts.init();
        ts.setVisible(true);
//        Score score = new Score(7,5.0);
//        WinScreen win = new WinScreen(frame,score);
//        win.init();
//        win.setVisible(true);
        frame.add(ts);
        frame.setCurrentScreen(ts);
        frame.setVisible(true);
    }
}