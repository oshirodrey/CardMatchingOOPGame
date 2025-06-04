package CardGame.Domain.Entities;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents the entire game board as a 2D grid of cards.
 * Manages flipping, matching, move count, and elapsed time tracking.
 */
public class GameBoard {
    private final long startTime;
    private final ArrayList<Card> flippedCards;
    private List<List<Card>> grid;
    private int moveCount;

    public GameBoard(int rows, int cols, List<Card> cards) {
        initializeGrid(rows, cols, cards);
        this.moveCount = 0;
        this.startTime = System.currentTimeMillis();
        this.flippedCards = new ArrayList<>();
    }
    /**
     * Initializes a game board with the given dimensions and card list.
     *
     * @param rows  number of rows
     * @param cols  number of columns
     * @param cards list of cards to place on the board
     */
    private void initializeGrid(int rows, int cols, List<Card> cards) {
        if (cards.size() < rows * cols) {
            throw new IllegalArgumentException("Not enough cards to initialize the grid.");
        }
        grid = new ArrayList<>();
        int index = 0;
        for (int r = 0; r < rows; r++) {
            List<Card> row = new ArrayList<>();
            for (int c = 0; c < cols; c++) {
                row.add(cards.get(index++));
            }
            grid.add(row);
        }
    }
    /**
     * Attempts to flip a card at the given grid location.
     *
     * @param row the row of the card
     * @param col the column of the card
     */
    public void flipCard(int row, int col) {
        // Do not allow flipping more than 2 cards
        if (flippedCards.size() >= 2) return;

        Card card = grid.get(row).get(col);

        // Guard: prevent interaction with already matched or face-up cards
        if (card.isMatched() || card.isFaceUp()) return;

        card.flip();
        flippedCards.add(card);
    }
    /**
     * Checks if the two currently flipped cards match.
     * @return true if they match, false otherwise.
     */
    public boolean checkForMatch() {
        if (flippedCards.size() == 2) {
            Card c1 = flippedCards.get(0);
            Card c2 = flippedCards.get(1);
            return c1.getCardName().equals(c2.getCardName());
        }
        return false;
    }
    /**
     * Processes the flipped cards. Marks them as matched or flips them back.
     * Increments the move count.
     */
    public void when2CardsFlipped() {
        if (flippedCards.size() == 2) {
            Card c1 = flippedCards.get(0);
            Card c2 = flippedCards.get(1);
            if (checkForMatch()) {
                c1.setMatched(true);
                c2.setMatched(true);
            } else {
                c1.flip();
                c2.flip();//flip back
            }
            moveCount++;
            clearFlippedCards();

        }
    }

    /**
     * Checks if all cards have been matched.
     *
     * @return true if the game is won
     */
    public boolean isAllCardsMatched() {
        for (List<Card> cards : grid) {
            for (Card card : cards) {
                if (!card.isMatched()) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Returns the time in seconds since the game started.
     *
     * @return elapsed time in seconds
     */
    public double getElapsedTime() {
        return (System.currentTimeMillis() - startTime) / 1000.0;
    }
    /**
     * Creates a snapshot of the current score.
     *
     * @return a Score object
     */
    public Score getScoreSnapshot() {
        return new Score(moveCount, getElapsedTime());
    }


    //Getters and setters and helpers
    public List<List<Card>> getGrid() {
        return grid;
    }

    public void getCard(int row, int col) {
        grid.get(row).get(col);
    }

    public int getMoveCount() {
        return moveCount;
    }

    public ArrayList<Card> getFlippedCards() {
        return flippedCards;
    }

    private void clearFlippedCards() {
        flippedCards.clear();
    }

    public int getFlippedCardsSize() {
        return flippedCards.size();
    }

}
