package CardGame.Domain.Entities;

import javax.swing.*;

public class Card {
    private final String cardName;
    private boolean isFaceUp= false;
    private boolean isMatched= false;
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


}
