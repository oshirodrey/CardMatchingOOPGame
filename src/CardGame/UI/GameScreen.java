package CardGame.UI;

import CardGame.Application.UseCases.CheckForMatchUseCase;
import CardGame.Application.UseCases.FlipCardUseCase;
import CardGame.Domain.Entities.Card;
import CardGame.Domain.Entities.GameBoard;
import CardGame.Domain.Services.CardFactory;
import CardGame.UI.CustomizedComponents.CardClickListener;
import CardGame.UI.CustomizedComponents.Screen;
import CardGame.UI.CustomizedComponents.StyleButton;
import CardGame.UI.CustomizedComponents.StyleCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameScreen extends Screen implements CardClickListener {
    private GameBoard gameBoard;
    List<String> cardNameList = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h","i","j"));
    List<Card> shuffledCardDeck;
    List<StyleCard> displayCardDeck;
    private FlipCardUseCase flipCardUseCase;
    private CheckForMatchUseCase checkForMatchUseCase;
    private final int rows= 4;
    private final int cols= 5;
    private Timer gameTimer;
    public GameScreen() {
      shuffledCardDeck = CardFactory.createShuffledCardPairs(cardNameList,rows,cols);
      gameBoard = new GameBoard(rows,cols, shuffledCardDeck);
      flipCardUseCase = new FlipCardUseCase(gameBoard);
      checkForMatchUseCase = new CheckForMatchUseCase(gameBoard);
    }

    public ArrayList<StyleCard> displayCards() {
        ArrayList<StyleCard> cards = new ArrayList<>();
      for (Card card : shuffledCardDeck) {
          StyleCard styleCard = new StyleCard(card,this);
          cards.add(styleCard);
      }
      return cards;
    }




    @Override
    public void init() {
        this.getParentFrame().setSize(new Dimension(600, 700));
        this.setLayout(new BorderLayout());
        this.setBackground(customGreen);
        this.displayCardDeck = displayCards();

        JPanel cardDeckPanel = new JPanel();
        cardDeckPanel.setBackground(customGreen);
        cardDeckPanel.setLayout(new GridLayout(rows,cols));
        for (StyleCard card : displayCardDeck) {
            cardDeckPanel.add(card);
        }

        //RUN'S INFORMATION STORING
        JPanel runInfoPanel = new JPanel();
        runInfoPanel.setBackground(customGreen);
        runInfoPanel.setLayout(new BoxLayout(runInfoPanel,BoxLayout.X_AXIS));


        JLabel moveCountLabel = new JLabel("Move: 0");
        JLabel timePassedLabel = new JLabel("     Time Passed: 0 second" );


        // Timer that ticks every 100 milliseconds
        this.gameTimer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveCountLabel.setText("Move: "+gameBoard.getMoveCount());
                timePassedLabel.setText("     Time Passed: " + gameBoard.getElapsedTime() + " seconds");
                if(gameBoard.isAllCardsMatched()){
                    gameTimer.stop();
                    SwingUtilities.invokeLater(() -> {
                        getParentFrame().replaceCurrentScreenWith(
                                new WinScreen(getParentFrame(), gameBoard.getScoreSnapshot())
                        );
                    });
                }
            }});
        gameTimer.start();
        StyleButton backButton = new StyleButton("Back to Main Menu");
        backButton.addActionListener(e -> {
            gameTimer.stop();
            this.getParentFrame().replaceCurrentScreenWith(new TitleScreen(this.getParentFrame())); });

        runInfoPanel.add(moveCountLabel);
        runInfoPanel.add(timePassedLabel);


        this.add(backButton, BorderLayout.NORTH);
        this.add(cardDeckPanel, BorderLayout.CENTER);
        this.add(runInfoPanel, BorderLayout.SOUTH);
        this.setVisible(true);


    }

    @Override
    public void onCardClicked(StyleCard clickedCard) {
        if (gameBoard.getFlippedCardsSize() < 2) {
            //get clicked card location by access the core card entity
            flipCardUseCase.execute(clickedCard.getCardEntity().getRow(), clickedCard.getCardEntity().getCol());
            clickedCard.showCardFont();

            if (gameBoard.getFlippedCardsSize() == 2) {

                Timer timer = new Timer(1000, e -> {
                    checkForMatchUseCase.execute();
                    updateCardIcons(); // Loop through styleCards to update icons
                });
                timer.setRepeats(false);
                timer.start();

            }
        }
    }
    public void updateCardIcons() {
        for (StyleCard sc : this.displayCardDeck) {
            sc.updateCardIcons(); // flips back if not face up
        }
    }

}
