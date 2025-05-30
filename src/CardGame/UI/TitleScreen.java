package CardGame.UI;

import CardGame.UI.CustomizedComponents.Screen;
import CardGame.UI.CustomizedComponents.StyleButton;
import CardGame.UI.Helpers.ButtonFactory;
import CardGame.UI.Helpers.FontHelper;
import CardGame.UI.Helpers.ImageCache;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class TitleScreen extends Screen {

    public TitleScreen(MainFrame frame) {

        setParentFrame(frame);


    }

    @Override
    public void init() {
        this.getParentFrame().setSize(800,600);
        this.setLayout(new BorderLayout());
        this.setBackground(customPink);

        Image gameImg = new ImageIcon(getClass().getResource("/Game/gameIcon.png")).getImage();
        ImageIcon gameIcon = new ImageIcon(gameImg.getScaledInstance(200,200,java.awt.Image.SCALE_SMOOTH));

        JLabel gameIconLabel = new JLabel(gameIcon);
        JLabel gameTitle = new JLabel("Matching Card");
        gameTitle.setFont(FontHelper.get("Althea-Bold",70f));
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);


        JPanel buttonContainer = new JPanel();
        buttonContainer.setOpaque(false);
        buttonContainer.setBackground(customPink);
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));

        //create buttons
        StyleButton startButton = ButtonFactory.createStartGameButton(" Start Game ",this);
        startButton.setEnabled(false);//temporarily disable this button for background loading
        StyleButton gameRuleButton = ButtonFactory.createRuleButton(" Rule ", this);
        StyleButton leaderBoardButton = ButtonFactory.createLeaderBoardButton(" Leaderboard ",this);
        StyleButton exitButton = ButtonFactory.createExitButton(" Exit Game ",this);

        buttonContainer.add(startButton);
        buttonContainer.add(gameRuleButton);
        buttonContainer.add(leaderBoardButton);
        buttonContainer.add(exitButton);

        this.add(gameIconLabel, BorderLayout.NORTH);
        this.add(gameTitle, BorderLayout.CENTER);
        this.add(buttonContainer, BorderLayout.SOUTH);
        preloadImagesInBackground(startButton);
    }
    private void preloadImagesInBackground(JButton startButton) {
        SwingWorker<Void, Void> loader = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                List<String> cardNames = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
                ImageCache.preloadAllImages(cardNames);
                return null;
            }

            @Override
            protected void done() {
                startButton.setEnabled(true); // enable when done
            }
        };
        loader.execute();
    }
}
