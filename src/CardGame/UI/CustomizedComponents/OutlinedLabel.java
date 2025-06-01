package CardGame.UI.CustomizedComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;

public class OutlinedLabel extends JComponent {
    private final String text;
    private final Font font;
    private final Color fillColor;
    private final Color strokeColor;

    public OutlinedLabel(String text, Font font, Color fillColor, Color strokeColor) {
        this.text = text;
        this.font = font;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor; //outline color
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);// for smoother edge
        g2.setFont(font);

        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(text)) / 2;
        int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;

        GlyphVector gv = font.createGlyphVector(g2.getFontRenderContext(), text);
        Shape shape = gv.getOutline(x, y);

        g2.setColor(strokeColor);
        g2.setStroke(new BasicStroke(10));
        g2.draw(shape);

        g2.setColor(fillColor);
        g2.fill(shape);
    }

    @Override
    public Dimension getPreferredSize() {
        Graphics g = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics();
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        return new Dimension(fm.stringWidth(text) , -fm.getHeight()*3/4);
    }
}
