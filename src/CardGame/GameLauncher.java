package CardGame;

import CardGame.UI.MainFrame;
import CardGame.UI.TitleScreen;

import javax.swing.*;
import java.awt.*;

public class GameLauncher {

    public static void start() {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(new Dimension(800, 600));
            frame.setResizable(false);

            TitleScreen titleScreen = new TitleScreen(frame);
            frame.setCurrentScreen(titleScreen);
            frame.add(titleScreen);
            titleScreen.init();

            frame.setVisible(true);
        });
    }
}
