package CardGame.UI;

import CardGame.Application.UseCases.SaveScoresUseCase;
import CardGame.Domain.Entities.Score;
import CardGame.Domain.Services.IScoreRepository;
import CardGame.Infrastructure.Persistence.FileScoreRepository;
import CardGame.UI.CustomizedComponents.Screen;
import CardGame.UI.CustomizedComponents.StyleButton;
import CardGame.UI.Helpers.ButtonFactory;
import CardGame.Domain.Utils.ScoreUtils;


import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

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
        this.setBackground(customPink);
        this.setLayout(new BorderLayout());
        //display score (and corresponding message, icon)
        JPanel scorePanel = new JPanel();
        scorePanel.setBackground(customPink);
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));

        JLabel iconLabel = new JLabel(winingIcon());
        JLabel winingTextLabel = winningLabel();
        JLabel playerScoreLabel = new JLabel("Your score: Moves: "+score.getMoveCount()+" Time: "+ score.getElapsedTime());

        JLabel bestScoreLabel = bestScoreLabel();



        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        scorePanel.add(iconLabel);
        scorePanel.add(winingTextLabel);
        scorePanel.add(playerScoreLabel);
        scorePanel.add(bestScoreLabel);






        //button container
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));
        buttonContainer.setBackground(customPink);
        StyleButton leaderBoardButton = ButtonFactory.createLeaderBoardButton("Leader Board", this);
        StyleButton backButton = ButtonFactory.createBackButton("Back to Main Menu", this);
        buttonContainer.add(leaderBoardButton);
        buttonContainer.add(backButton);


        //add components to main panel
        this.add(buttonContainer, BorderLayout.SOUTH);
        this.add(scorePanel, BorderLayout.CENTER);
        this.setVisible(true);

        //save this run score
        saveScoresUseCase.execute(score);


    }

    private boolean isBestScore(Score score) {
        List<Score> scoreList = scoreRepository.loadScores();

        if (scoreList.isEmpty()) {
            return true; // No previous scores
        }

        // Find the current best score in the list
        Score bestScore = Collections.min(scoreList, ScoreUtils.comparator());

        // Return true if the new score is better than the current best
        return ScoreUtils.comparator().compare(score, bestScore) < 0;
    }
    private JLabel winningLabel(){
        JLabel winningLabel = new JLabel();
        winningLabel.setFont(new Font("Arial", Font.BOLD, 30));
        String winning_Text;
        if (isBestScore(this.score)) {
            winning_Text = "CONGRATULATION ON YOUR NEW BEST SCORE!!!";
        }
        else {
            winning_Text = "YOU DID WELL, BETTER LUCK NEXT TIME!";
        }
        winningLabel.setText(winning_Text);
        winningLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        return winningLabel;
    }
    private ImageIcon winingIcon() {
        String filePath;
        if (isBestScore(this.score)) {
            filePath = "/Game/best_score.png";
        } else {
            filePath = "/Game/normal_score.png";
        }
        Image iconImg = new ImageIcon(this.getClass().getResource(filePath)).getImage();
        ImageIcon winingIcon = new ImageIcon(iconImg.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        return winingIcon;
    }
    private JLabel bestScoreLabel(){
        String text;
        if (isBestScore(this.score)) {
            text = "Your score is currently the best!";
        }
        else {
            text= "Current best score: Move: "+scoreRepository.loadScores().get(0).getMoveCount()+" Time: "+ scoreRepository.loadScores().get(0).getElapsedTime();
        }
        JLabel bestScoreLabel = new JLabel(text);
        bestScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return bestScoreLabel;

    }

}
