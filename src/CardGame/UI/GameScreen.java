package CardGame.UI;

import CardGame.Domain.Entities.Card;
import CardGame.Domain.Entities.GameBoard;
import CardGame.Domain.Services.CardFactory;
import CardGame.UI.CustomizedComponents.Screen;
import CardGame.UI.CustomizedComponents.StyleCard;

import javax.swing.*;
import java.awt.*;
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
        this.getParentFrame().setSize(new Dimension(500, 600));
        this.setLayout(new BorderLayout());
        this.setBackground(customGreen);
        this.displayCardDeck = displayCards();

        JPanel cardDeckPanel = new JPanel();
        cardDeckPanel.setLayout(new GridLayout(rows,cols));
        for (StyleCard card : displayCardDeck) {
            cardDeckPanel.add(card);
        }
        JLabel timePassedLabel = new JLabel("Time Passed");
        this.add(cardDeckPanel, BorderLayout.CENTER);
        this.add(timePassedLabel, BorderLayout.SOUTH);
        this.setVisible(true);


    }
}
