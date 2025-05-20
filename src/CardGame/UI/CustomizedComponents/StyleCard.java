package CardGame.UI.CustomizedComponents;

import CardGame.Domain.Entities.Card;
import CardGame.UI.Helpers.ImageCache;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StyleCard extends JButton implements MouseListener {
    private Card card;
    //Card size
    private final int cardWidth= 90;
    private final int cardHeight= 128;
    //Card images
    private ImageIcon cardBack = new ImageIcon(ImageCache.getScaledImage("/Cards/back.png", cardWidth, cardHeight));
    private ImageIcon cardBackHover = new ImageIcon(ImageCache.getScaledImage("/Cards/backHover.png", cardWidth, cardHeight));

    private ImageIcon cardFont;



    public StyleCard(Card card) {
        this.card = card;
        cardFont = new ImageIcon(ImageCache.getScaledImage("/Cards/" + card.getCardName() + ".png", cardWidth, cardHeight));

        init();
    }
    public void init(){
        this.setPreferredSize(new Dimension(cardWidth,cardHeight));
        this.setOpaque(false);
        this.setBorder(null);
        this.setContentAreaFilled(false);
        this.setIcon(cardBack);
        this.setFocusable(false);

        this.addMouseListener(this);
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        card.flip();
        this.setIcon(cardFont);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(!card.isFaceUp()) {
            this.setIcon(cardBackHover);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(!card.isFaceUp()) {
            this.setIcon(cardBack);
        }
    }
}
