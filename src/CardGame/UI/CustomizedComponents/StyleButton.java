package CardGame.UI.CustomizedComponents;

import CardGame.UI.Helpers.FontHelper;
import CardGame.UI.Sound.SFXPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StyleButton extends JButton implements MouseListener {
    private final String text;
    private final SFXPlayer sfxPlayer;
    private Color initColor = new Color(255, 250, 240);
    private Color hoverColor = new Color(254, 241, 230);
    private Font initFont = FontHelper.get("FuturaAgMiteOpti-Six", 30f);
    private Font hoverFont = FontHelper.get("OPTIFuturaDemiBold", 30f);

    public StyleButton(String text) {
        this.setText(text);
        this.setForeground(initColor);
        this.setFont(initFont);
        this.setOpaque(false);
        this.setBorder(null);
        this.setContentAreaFilled(false);
        this.addMouseListener(this);
        this.text = text;
        sfxPlayer = new SFXPlayer();

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        sfxPlayer.play("clickButton");
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setForeground(hoverColor);
        this.setFont(hoverFont);
        this.setText("\u2741" + text + "\u2741");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setForeground(initColor);
        this.setFont(initFont);
        this.setText(text);

    }

    public void setInitAndHoverColor(Color initColor, Color hoverColor) {
        this.initColor = initColor;
        this.hoverColor = hoverColor;
        this.setForeground(initColor);
    }

    public void setFontSize(float size) {
        initFont = initFont.deriveFont(size);
        hoverFont = hoverFont.deriveFont(size);
        this.setFont(initFont);
    }

}
