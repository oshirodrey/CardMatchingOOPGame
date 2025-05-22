package CardGame.UI;

import CardGame.Domain.Entities.Score;
import CardGame.UI.CustomizedComponents.Screen;
import CardGame.UI.CustomizedComponents.StyleButton;
import CardGame.UI.Helpers.ButtonFactory;

import javax.swing.*;
import java.awt.*;

public class WinScreen extends Screen {
    private Score score;
    public WinScreen(MainFrame frame) {
        setParentFrame(frame);
    }


    @Override
    public void init() {
        this.getParentFrame().setSize(new Dimension(800, 600));
        this.setBackground(customGreen);
        this.setLayout(new BorderLayout());



        //button container
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));
        buttonContainer.setBackground(customGreen);
        StyleButton leaderBoardButton = ButtonFactory.createLeaderBoardButton("Leader Board", this);
        StyleButton backButton = ButtonFactory.createBackButton("Back to Main Menu", this);
        buttonContainer.add(leaderBoardButton);
        buttonContainer.add(backButton);

        this.add(buttonContainer, BorderLayout.SOUTH);
        this.setVisible(true);

        //add components to main panel

    }
}
