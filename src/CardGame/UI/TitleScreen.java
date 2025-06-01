package CardGame.UI;

import CardGame.UI.CustomizedComponents.Screen;
import CardGame.UI.CustomizedComponents.StyleButton;
import CardGame.UI.Helpers.ButtonFactory;
import CardGame.UI.Helpers.FontHelper;
import CardGame.UI.Helpers.ImageCache;
import CardGame.UI.Sound.BGMPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class TitleScreen extends Screen {
    public TitleScreen(MainFrame frame) {

        setParentFrame(frame);
        try {
            bgmPlayer = new BGMPlayer();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void init() {
        this.getParentFrame().setSize(800,600);
        this.setLayout(new BorderLayout());

        Image gameImg = new ImageIcon(getClass().getResource("/Game/gameIcon.png")).getImage();
        ImageIcon gameIcon = new ImageIcon(gameImg.getScaledInstance(200,200,java.awt.Image.SCALE_SMOOTH));

        JLabel gameIconLabel = new JLabel(gameIcon);
        JLabel gameTitle = new JLabel("Matching Card");
        gameTitle.setFont(FontHelper.get("Althea-Bold",70f));
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);


        JPanel buttonContainer = new JPanel();
        buttonContainer.setOpaque(false);
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));

        //custom button color
        Color initButtonColor = new Color(255,175,204);
        Color hoverButtonColor = new Color(253, 143, 144);
        //create buttons
        StyleButton startButton = ButtonFactory.createStartGameButton(this).initAndHoverColor(initButtonColor,hoverButtonColor).build();
        startButton.setEnabled(false);//temporarily disable this button for background loading
        StyleButton gameRuleButton = ButtonFactory.createRuleButton( this).initAndHoverColor(initButtonColor,hoverButtonColor).build();
        StyleButton leaderBoardButton = ButtonFactory.createLeaderBoardButton(this).initAndHoverColor(initButtonColor,hoverButtonColor).build();
        StyleButton exitButton = ButtonFactory.createExitButton().initAndHoverColor(initButtonColor,hoverButtonColor).build();

        buttonContainer.add(startButton);
        buttonContainer.add(gameRuleButton);
        buttonContainer.add(leaderBoardButton);
        buttonContainer.add(exitButton);

        this.add(gameIconLabel, BorderLayout.NORTH);
        this.add(gameTitle, BorderLayout.CENTER);
        this.add(buttonContainer, BorderLayout.SOUTH);

        bgmPlayer.play("CCS_BGM6");
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
    @Override
    protected void paintComponent(Graphics g) { // for gradient background and animation
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(
                getWidth()/2, 0, new Color( 123, 173, 255), // Top
                getWidth()/2, getHeight(), new Color( 222, 239, 255)); // Bottom
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

}
