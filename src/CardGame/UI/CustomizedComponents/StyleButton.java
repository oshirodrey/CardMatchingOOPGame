package CardGame.UI.CustomizedComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StyleButton extends JButton implements MouseListener {
    private Color initColor = new Color(255, 250, 240);
    private Color hoverColor = new Color(254, 241, 230);
    private Font initFont = new Font("Serif", Font.PLAIN, 30);
    private Font hoverFont= new Font("Serif", Font.BOLD, 32);
    public StyleButton(String text) {
        this.setText(text);
        this.setForeground(initColor);
        this.setFont(initFont);
        this.setOpaque(false);
        this.setBorder(null);
        this.setContentAreaFilled(false);
        this.addMouseListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setForeground(hoverColor);
        this.setFont(hoverFont);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setForeground(initColor);
        this.setFont(initFont);

    }
}
