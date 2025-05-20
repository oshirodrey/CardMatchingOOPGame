package CardGame.Domain.Entities;

import javax.swing.*;

public class Card {
    private final String cardName;
    private boolean isFaceUp= false;
    private boolean isMatched= false;
    private int col, row;
    public Card(String symbol) {
        this.cardName = symbol;
    }
    public void flip() {

            isFaceUp= !isFaceUp;


    }
    //Getters and setters
    public String getCardName() {return cardName;}
    public boolean isFaceUp() {return isFaceUp;}
    public boolean isMatched() {return isMatched;}
    public void setMatched(boolean isMatched) {this.isMatched = isMatched;}

    public int getCol() {return col;}
    public void setCol(int col) {this.col = col;}
    public int getRow() {return row;}
    public void setRow(int row) {this.row = row;}


}
