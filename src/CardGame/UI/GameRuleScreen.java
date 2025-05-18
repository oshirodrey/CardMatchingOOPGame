package CardGame.UI;

import CardGame.UI.CustomizedComponents.Screen;
import CardGame.UI.CustomizedComponents.StyleButton;

import javax.swing.*;
import java.awt.*;

public class GameRuleScreen extends Screen {
    public GameRuleScreen(MainFrame parentFrame) {

    }

    @Override
    public void init() {
        this.getParentFrame().setSize(new Dimension(800, 600));
        this.setLayout(new BorderLayout());
        this.setBackground(customGreen);

        JLabel ruleLabel = new JLabel(
                "<html>" +
                        "<div style='width:300px; font-size:14px;'>" +
                        "<strong>Welcome to the Memory Card Game!</strong><br><br>" +

                        "<u><strong>How to Play:</strong></u><br>" +
                        "1. Click a card to flip it and reveal its symbol.<br>" +
                        "2. Match all the pairs using as few moves as possible.<br><br>" +

                        "<u><strong>Leaderboard Rules:</strong></u><br>" +
                        "1. The run with fewer moves rank higher.<br>" +
                        "2. If move counts are the same, the faster time gets the higher rank.<br>" +
                        "</div>" +
                        "</html>"
        );


        JPanel buttonContainer = new JPanel();
        buttonContainer.setOpaque(false);
        buttonContainer.setBackground(customGreen);
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));

        StyleButton startButton = new StyleButton("Start Game");
        StyleButton backButton = new StyleButton("Back to Main Menu");


        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        startButton.addActionListener(e -> this.getParentFrame().replaceCurrentScreenWith(new GameScreen()));
        backButton.addActionListener(e -> this.getParentFrame().replaceCurrentScreenWith(new TitleScreen(this.getParentFrame())));



        buttonContainer.add(startButton);
        buttonContainer.add(backButton);

        this.add(ruleLabel, BorderLayout.CENTER);
        this.add(buttonContainer, BorderLayout.SOUTH);



    }
}
