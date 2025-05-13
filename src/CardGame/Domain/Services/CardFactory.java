package CardGame.Domain.Services;

import CardGame.Domain.Entities.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {

    public static List<Card> createShuffledCardPairs(int pairCount, List<String> symbols) {
        List<Card> cards = new ArrayList<>();

        // Make sure you have enough symbols
        if (symbols.size() < pairCount) {
            throw new IllegalArgumentException("Not enough symbols for the requested pairs");
        }

        // Create pairs of cards with same symbol
        for (int i = 0; i < pairCount; i++) {
            String symbol = symbols.get(i);
            cards.add(new Card(symbol)); // pair 1
            cards.add(new Card(symbol)); // pair 2
        }

        // Shuffle the list
        Collections.shuffle(cards);

        return cards;
    }
}
