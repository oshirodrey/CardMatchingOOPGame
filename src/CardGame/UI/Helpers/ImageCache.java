package CardGame.UI.Helpers;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageCache {
    private static final Map<String, Image> cache = new HashMap<>();

    public static Image getScaledImage(String path, int width, int height) {
        String key = path + "_" + width + "x" + height;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        Image image = new ImageIcon(ImageCache.class.getResource(path))
                .getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH);
        cache.put(key, image);
        return image;
    }
}