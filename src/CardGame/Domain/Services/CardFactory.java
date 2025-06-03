package CardGame.Domain.Services;

import CardGame.Domain.Entities.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {

    public static ArrayList<Card> createShuffledCardPairs(List<String> names, int rows, int cols) {
        ArrayList<Card> cards = new ArrayList<>();

        //Each pair appears ONCE
        int pairCount = names.size();

        // Create pairs of cards with same symbol
        for (int i = 0; i < pairCount; i++) {
            String symbol = names.get(i);
            cards.add(new Card(symbol)); // pair 1
            cards.add(new Card(symbol)); // pair 2
        }

        // Shuffle the list
        Collections.shuffle(cards);

        //Add card's location (row and col)
        int index = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                cards.get(index).setRow(r);
                cards.get(index).setCol(c);
                index++;
            }
        }

        return cards;
    }

}
