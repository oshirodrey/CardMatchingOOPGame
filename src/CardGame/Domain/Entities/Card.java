package CardGame.Domain.Entities;
/**
 * Represents a single card in the memory matching game.
 * Each card has a symbol (name), a position on the board (row and column),
 * and flags for whether it is face up or matched.
 */
public class Card {
    private final String cardName;
    private boolean isFaceUp = false;
    private boolean isMatched = false;
    private int col, row;
    /**
     * Constructs a card with the given symbol.
     *
     * @param symbol the visual or logical symbol for the card
     */
    public Card(String symbol) {
        this.cardName = symbol;
    }
    /**
     * Flips the card, reverse its face-up state.
     */
    public void flip() {

        isFaceUp = !isFaceUp;


    }

    //Getters and setters
    public String getCardName() {
        return cardName;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean isMatched) {
        this.isMatched = isMatched;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }


}
