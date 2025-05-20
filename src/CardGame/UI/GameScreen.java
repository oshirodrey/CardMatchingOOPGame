package CardGame.UI;

import CardGame.Domain.Entities.Card;
import CardGame.Domain.Entities.GameBoard;
import CardGame.Domain.Services.CardFactory;
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

public class GameScreen extends Screen {
    private GameBoard gameBoard;
    List<String> cardNameList = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h","i","j"));
    List<Card> shuffledCardDeck;
    List<StyleCard> displayCardDeck;
    private final int rows= 4;
    private final int cols= 5;
    public GameScreen() {
      shuffledCardDeck = CardFactory.createShuffledCardPairs(cardNameList);
      gameBoard = new GameBoard(rows,cols, shuffledCardDeck);
    }

    public ArrayList<StyleCard> displayCards() {
        ArrayList<StyleCard> cards = new ArrayList<>();
      for (Card card : shuffledCardDeck) {
          StyleCard styleCard = new StyleCard(card);
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


        // Timer that ticks every 1000 milliseconds (1 second)
        Timer timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timePassedLabel.setText("      Time Passed: " + gameBoard.getElapsedTime() + " seconds");
            }});
        timer.start();
        StyleButton backButton = new StyleButton("Back to Main Menu");
        backButton.addActionListener(e -> {
            timer.stop();
            this.getParentFrame().replaceCurrentScreenWith(new TitleScreen(this.getParentFrame())); });

        runInfoPanel.add(moveCountLabel);
        runInfoPanel.add(timePassedLabel);


        this.add(backButton, BorderLayout.NORTH);
        this.add(cardDeckPanel, BorderLayout.CENTER);
        this.add(runInfoPanel, BorderLayout.SOUTH);
        this.setVisible(true);


    }
}
