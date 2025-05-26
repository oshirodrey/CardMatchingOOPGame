package CardGame.UI.Helpers;

import CardGame.UI.CustomizedComponents.StyleCard;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class ImageCache {
    private static final Map<String, ImageIcon> cache = new HashMap<>();
    private static final int cardWidth = StyleCard.getCardWidth();
    private static final int cardHeight = StyleCard.getCardHeight();

    public static void preloadAllImages(List<String> cardNames) {
        for (String name : cardNames) {
            loadCardImage(name); // will cache it
        }
        loadCardImage("back");
        loadCardImage("backHover");
    }

    public static ImageIcon loadCardImage(String cardName) {
        if (!cache.containsKey(cardName)) {
            Image img = new ImageIcon(ImageCache.class.getResource("/Cards/" + cardName + ".png"))
                    .getImage()
                    .getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
            cache.put(cardName, new ImageIcon(img));
        }
        return cache.get(cardName);
    }
}