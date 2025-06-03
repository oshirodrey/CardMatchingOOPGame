package CardGame.UI;

import CardGame.UI.CustomizedComponents.OutlinedLabel;
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
        this.getParentFrame().setSize(800, 600);

        // GIF background
        ImageIcon background = ImageCache.loadGIFImage("sakuraBG1");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

        // Create panel for content (with layout)
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false); // Let background show through
        contentPanel.setBounds(0, 0, 800, 570);

        // Title label
        Font f = FontHelper.get("GarbataTrial-ExtraboldItalic", 60f);
        OutlinedLabel titleLine1 = new OutlinedLabel("Sakura", f,
                new Color(217, 69, 107),new Color(255, 248, 220));
        titleLine1.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font f1 = FontHelper.get("GarbataTrial-ExtraboldItalic", 40f);
        OutlinedLabel titleLine2 = new OutlinedLabel("Card Matching", f1,
                new Color(217, 69, 107),new Color(255, 248, 220));
        titleLine2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false); // Keep background transparent
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

    // Add vertical spacing manually
        titlePanel.add(Box.createVerticalStrut(-10)); // space above icon
        titlePanel.add(titleLine1);
        titlePanel.add(Box.createVerticalStrut(-80)); // reduce spacing between 2 titles
        titlePanel.add(titleLine2);
        titlePanel.add(Box.createVerticalGlue());

        // Buttons
        JPanel buttonContainer = new JPanel();
        buttonContainer.setOpaque(false);
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));

        Color initColor = new Color(255, 248, 220);
        Color hoverColor = new Color(255,200,221);

        StyleButton startButton = ButtonFactory.createStartGameButton(this).initAndHoverColor(initColor, hoverColor).build();
        startButton.setEnabled(false);
        StyleButton gameRuleButton = ButtonFactory.createRuleButton(this).initAndHoverColor(initColor, hoverColor).build();
        StyleButton leaderBoardButton = ButtonFactory.createLeaderBoardButton(this).initAndHoverColor(initColor, hoverColor).build();
        StyleButton exitButton = ButtonFactory.createExitButton().initAndHoverColor(initColor, hoverColor).build();

        buttonContainer.add(startButton);
        buttonContainer.add(gameRuleButton);
        buttonContainer.add(leaderBoardButton);
        buttonContainer.add(exitButton);

        contentPanel.add(titlePanel, BorderLayout.CENTER);
        contentPanel.add(buttonContainer, BorderLayout.SOUTH);

        // Use JLayeredPane to layer background + content
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(contentPanel, JLayeredPane.PALETTE_LAYER); // on top

        // Add to this screen
        this.setLayout(new BorderLayout());
        this.add(layeredPane, BorderLayout.CENTER);

        bgmPlayer.play("CCS_BGM7");
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
