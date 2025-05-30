package CardGame.UI.Helpers;
import java.awt.*;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FontHelper {
    private static final Map<String, Font> fontCache = new HashMap<>();

    public static Font get(String fontName, float size) {
        String fontPath= "/UI/Fonts/"+fontName+".ttf";
        Font baseFont = fontCache.get(fontPath);
        if (baseFont == null) {
            try {
                InputStream is = FontHelper.class.getResourceAsStream(fontPath);
                baseFont = Font.createFont(Font.TRUETYPE_FONT, is);
                fontCache.put(fontPath, baseFont); // Cache the font for later use
            } catch (Exception e) {
                e.printStackTrace();
                return new Font("Serif", Font.PLAIN, (int) size); // fallback
            }
        }
        return baseFont.deriveFont(size);
    }
}