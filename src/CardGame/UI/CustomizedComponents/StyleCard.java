package CardGame.UI.CustomizedComponents;

import CardGame.Domain.Entities.Card;

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
    private Image cardBackImg = new ImageIcon(getClass().getResource("/Cards/back.png")).getImage();
    private ImageIcon cardBack = new ImageIcon(cardBackImg.getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH));

    private Image cardBackHoverImg = new ImageIcon(getClass().getResource("/Cards/backHover.png")).getImage();
    private ImageIcon cardBackHover = new ImageIcon(cardBackHoverImg.getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH));

    private Image cardFontImg;
    private ImageIcon cardFont;



    public StyleCard(Card card) {
        this.card = card;
        cardFontImg = new ImageIcon(getClass().getResource("/Cards/"+this.card.getCardName()+".png")).getImage();
        cardFont = new ImageIcon(cardFontImg.getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH));
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
