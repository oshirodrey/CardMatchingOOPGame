package CardGame.UI.CustomizedComponents;

import CardGame.Domain.Entities.Card;
import CardGame.UI.Helpers.ImageCache;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StyleCard extends JButton implements MouseListener {
    //Card size
    private static final int scale = 8;
    private static final int cardWidth = 10 * scale;
    private static final int cardHeight = 22 * scale;
    private final Card card;
    private final CardClickListener cardClickListener;
    //Card images
    private final ImageIcon cardBack = ImageCache.loadCardImage("back");
    private final ImageIcon cardBackHover = ImageCache.loadCardImage("backHover");
    private final ImageIcon cardFont;
    private ImageIcon currentIcon;


    public StyleCard(Card card, CardClickListener cardClickListener) {
        this.card = card;
        this.cardClickListener = cardClickListener;
        cardFont = ImageCache.loadCardImage(this.card.getCardName());
        init();
    }

    public static int getCardWidth() {
        return cardWidth;
    }

    public static int getCardHeight() {
        return cardHeight;
    }

    public void init() {
        this.setPreferredSize(new Dimension(cardWidth, cardHeight));
        this.setOpaque(false);
        this.setMargin(new Insets(0, 0, 0, 0)); // No padding inside the button
        this.setBorder(BorderFactory.createEmptyBorder());   // No border
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
        if (!card.isFaceUp()) {
            cardClickListener.onCardClicked(this);
            //instead of the style card handling what happens after the click
            //the game screen will now do that for the card
            //callback pattern
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {


    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!card.isFaceUp() && currentIcon == cardBack) {
            this.setIcon(cardBackHover);
        }// only change the icon to cardBackHover if the initial icon is cardBack
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (currentIcon == cardBackHover) {
            updateCardIcons();
        }
    }

    public void updateCardIcons() {

        if (card.isFaceUp()) {
            this.setIcon(cardFont);
        } else {
            this.setIcon(cardBack);
        }

    }

    public void showCardFont() {
        this.setIcon(cardFont);
    }

    public Card getCardEntity() {
        return card;
    }

    @Override
    public void setIcon(Icon icon) {
        super.setIcon(icon);
        currentIcon = (ImageIcon) icon;//Override to set this
    }// this will avoid the card being flipped back before the delay time ends
}
