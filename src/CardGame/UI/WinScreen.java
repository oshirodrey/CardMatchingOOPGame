package CardGame.UI;

import CardGame.Application.UseCases.SaveScoresUseCase;
import CardGame.Domain.Entities.Score;
import CardGame.Domain.Services.IScoreRepository;
import CardGame.Infrastructure.Persistence.FileScoreRepository;
import CardGame.UI.CustomizedComponents.Screen;
import CardGame.UI.CustomizedComponents.StyleButton;
import CardGame.UI.Helpers.ButtonFactory;

import javax.swing.*;
import java.awt.*;

public class WinScreen extends Screen {
    private Score score;
    private IScoreRepository scoreRepository;
    private SaveScoresUseCase saveScoresUseCase;
    public WinScreen(MainFrame frame,Score score) {
        setParentFrame(frame);
        this.score = score;
        this.scoreRepository = new FileScoreRepository();
        this.saveScoresUseCase = new SaveScoresUseCase(scoreRepository);
    }


    @Override
    public void init() {
        this.getParentFrame().setSize(new Dimension(800, 600));
        this.setBackground(customGreen);
        this.setLayout(new BorderLayout());
        //display score (and corresponding message, icon)
        JPanel scorePanel = new JPanel();
        scorePanel.setBackground(customGreen);
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        JLabel scoreLabel = new JLabel("Moves: "+score.getMoveCount()+" Time: "+ score.getElapsedTime());




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
