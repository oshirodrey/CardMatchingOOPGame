package CardGame.UI.Helpers;

import CardGame.UI.CustomizedComponents.StyleCard;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides cached access to images (cards, UI assets, GIFs).
 * Avoids redundant loading of image resources using an internal cache.
 */
public class ImageCache {
    private static final Map<String, ImageIcon> cache = new HashMap<>();
    private static final int cardWidth = StyleCard.getCardWidth();
    private static final int cardHeight = StyleCard.getCardHeight();

    /**
     * Preloads all image assets used by the cards into memory.
     *
     * @param cardNames list of card image names (excluding file extension)
     */
    public static void preloadAllImages(List<String> cardNames) {
        for (String name : cardNames) {
            loadCardImage(name); // will cache it
        }
        loadCardImage("back");
        loadCardImage("backHover");
    }

    /**
     * Loads and caches a static card image.
     *
     * @param cardName the name of the image file (without extension)
     * @return the scaled ImageIcon
     */
    public static ImageIcon loadCardImage(String cardName) {
        if (!cache.containsKey(cardName)) {
            Image img = new ImageIcon(ImageCache.class.getResource("/Cards/" + cardName + ".png"))
                    .getImage()
                    .getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
            cache.put(cardName, new ImageIcon(img));
        }
        return cache.get(cardName);
    }

    /**
     * Loads a looping GIF animation from resources.
     *
     * @param GIFName name of the .gif file (without extension)
     * @return ImageIcon of the GIF
     */
    public static ImageIcon loadGIFImage(String GIFName) {
        if (!cache.containsKey(GIFName)) {
            Image img = new ImageIcon(ImageCache.class.getResource("/Game/GIF/" + GIFName + ".gif"))
                    .getImage();
            cache.put(GIFName, new ImageIcon(img));
        }
        return cache.get(GIFName);
    }

    /**
     * Loads and scales a PNG image.
     *
     * @param imageName name of the PNG file (without extension)
     * @param width     desired width
     * @param height    desired height
     * @return the scaled ImageIcon
     */
    public static ImageIcon loadPNGImage(String imageName, int width, int height) {
        if (!cache.containsKey(imageName)) {
            Image img = new ImageIcon(ImageCache.class.getResource("/Game/" + imageName + ".png"))
                    .getImage()
                    .getScaledInstance(width, height, Image.SCALE_SMOOTH);
            cache.put(imageName, new ImageIcon(img));
        }
        return cache.get(imageName);
    }
}