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
import java.text.AttributedString;

public class GameRuleScreen extends Screen {
    public GameRuleScreen(MainFrame parentFrame) {
        try {
            bgmPlayer = new BGMPlayer();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init() {
        this.getParentFrame().setSize(new Dimension(800, 600));
        this.setLayout(new BorderLayout());
        this.setBackground(customPink);

        // === The Funny .GIF That I Don't Want To Let Go ===
        ImageIcon dancingImg = ImageCache.loadGIFImage("funni");
        JLabel dancingLabel = new JLabel(dancingImg);

        // === Rule Paragraph ===
        JTextArea ruleTextArea = new JTextArea();
        ruleTextArea.setForeground(Color.WHITE);
        ruleTextArea.setBackground(customPink);
        ruleTextArea.setFont(FontHelper.get("OPTIFuturaAgMite-Four", 25f));
        ruleTextArea.setEditable(false);
        ruleTextArea.setFocusable(false);
        ruleTextArea.setOpaque(true);
        ruleTextArea.setBorder(null);
        ruleTextArea.setLineWrap(true);
        ruleTextArea.setWrapStyleWord(true);

        ruleTextArea.setText("""
         Welcome to the Memory Card Game!

         How to Play:
         1. Click a card to flip it and reveal its symbol.
         2. Match all the pairs using as few moves as possible.

         Leaderboard Rules:
         1. The run with fewer moves ranks higher.
         2. If move counts are the same, the faster time gets the higher rank.
        """);

        // === Buttons ===
        JPanel buttonContainer = new JPanel();
        buttonContainer.setOpaque(false);
        buttonContainer.setBackground(customPink);
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));

        StyleButton startButton = ButtonFactory.createStartGameButton(this).build();
        StyleButton backButton = ButtonFactory.createBackButton(this).build();




        buttonContainer.add(startButton);
        buttonContainer.add(backButton);

        this.add(ruleTextArea, BorderLayout.CENTER);
        this.add(buttonContainer, BorderLayout.SOUTH);
        this.add(dancingLabel, BorderLayout.EAST);

        bgmPlayer.play("CCS_BGM6");



    }
}
