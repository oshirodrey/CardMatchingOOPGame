package CardGame.Domain.Entities;

public class Card {
    private final String symbol;
    private boolean isFaceUp= false;
    private boolean isMatched= false;
    public Card(String symbol) {
        this.symbol = symbol;
    }
    public void flip() {
        if(isMatched){
            isFaceUp= !isFaceUp;
        }

    }
    //Getters and setters
    public String getSymbol() {return symbol;}
    public boolean isFaceUp() {return isFaceUp;}
    public boolean isMatched() {return isMatched;}
    public void setMatched(boolean isMatched) {this.isMatched = isMatched;}

}
