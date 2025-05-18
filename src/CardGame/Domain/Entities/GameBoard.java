package CardGame.Domain.Entities;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private List<List<Card>> grid;
    private int moveCount;
    private long startTime;
    private ArrayList<Card> flippedCards;

    public GameBoard(int rows, int cols,List<Card> cards) {
        initializeGrid(rows, cols, cards);
        this.moveCount = 0;
        this.startTime = System.currentTimeMillis();
        this.flippedCards = new ArrayList<>();
    }
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
    public void flipCard(int row, int col) {
        // Do not allow flipping more than 2 cards
        if (flippedCards.size() >= 2) return;

        Card card = grid.get(row).get(col);

        // Guard: prevent interaction with already matched or face-up cards
        if (card.isMatched() || card.isFaceUp()) return;

        card.flip();
        flippedCards.add(card);
    }
    public boolean checkForMatch(){
        if(flippedCards.size() == 2){
            Card c1 = flippedCards.get(0);
            Card c2 = flippedCards.get(1);
            boolean isMatch = c1.getCardName().equals(c2.getCardName());
            if(isMatch){
                c1.setMatched(true);
                c2.setMatched(true);
            }
            else {
                c1.flip();
                c2.flip();//flip back
            }
            clearFlippedCards();
            moveCount++;
            return isMatch;
        }
        return false;
    }
    public boolean isAllCardsMatched(){
        for(List<Card> cards : grid){
            for(Card card : cards){
                if(!card.isMatched()){
                    return false;
                }
            }
        }
        return true;
    }

    public long getElapsedTime() {
        return System.currentTimeMillis() - startTime;
    }

    public Score getScoreSnapshot() {
        return new Score(moveCount, getElapsedTime());
    }


    //Getters and setters and helpers
    public List<List<Card>> getGrid() {return grid;}
    public void getCard(int row, int col) {grid.get(row).get(col);}
    public int getMoveCount() {return moveCount;}
    public ArrayList<Card> getFlippedCards() {return flippedCards;}
    private void clearFlippedCards() {flippedCards.clear();}

}
