package CardGame.UI;

import CardGame.UI.CustomizedComponents.Screen;
import CardGame.UI.CustomizedComponents.StyleButton;

import javax.swing.*;
import java.awt.*;

public class TitleScreen extends Screen {

    public TitleScreen(MainFrame frame) {

        setParentFrame(frame);


    }

    @Override
    public void init() {
        this.getParentFrame().setSize(800,600);
        this.setLayout(new BorderLayout());
        this.setBackground(customGreen);

        Image gameImg = new ImageIcon(getClass().getResource("/Game/gameIcon.png")).getImage();
        ImageIcon gameIcon = new ImageIcon(gameImg.getScaledInstance(200,200,java.awt.Image.SCALE_SMOOTH));

        JLabel gameIconLabel = new JLabel(gameIcon);
        JLabel gameTitle = new JLabel("Matching Card");
        gameTitle.setFont(new Font("Serif", Font.BOLD, 70));
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);


        JPanel buttonContainer = new JPanel();
        buttonContainer.setOpaque(false);
        buttonContainer.setBackground(customGreen);
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));

        StyleButton startButton = new StyleButton("Start Game");
        StyleButton gameRuleButton = new StyleButton("Rules");
        StyleButton leaderBoardButton = new StyleButton("Leaderboard");
        StyleButton exitButton = new StyleButton("Exit Game");

        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameRuleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderBoardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton.addActionListener(e -> this.getParentFrame().replaceCurrentScreenWith(new GameScreen()));
        gameRuleButton.addActionListener(e -> this.getParentFrame().replaceCurrentScreenWith(new GameRuleScreen(getParentFrame())));
        leaderBoardButton.addActionListener(e-> this.getParentFrame().replaceCurrentScreenWith(new LeaderBoardScreen()));
        exitButton.addActionListener(e -> System.exit(0));


        buttonContainer.add(startButton);
        buttonContainer.add(gameRuleButton);
        buttonContainer.add(leaderBoardButton);
        buttonContainer.add(exitButton);

        this.add(gameIconLabel, BorderLayout.NORTH);
        this.add(gameTitle, BorderLayout.CENTER);
        this.add(buttonContainer, BorderLayout.SOUTH);
    }
}
