package CardGame.Domain.Services;

import CardGame.Domain.Entities.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {

    public static ArrayList<Card> createShuffledCardPairs(List<String> names) {
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

        return cards;
    }
}
