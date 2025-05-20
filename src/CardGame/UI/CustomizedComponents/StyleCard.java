package CardGame.UI.CustomizedComponents;

import CardGame.Application.UseCases.FlipCardUseCase;
import CardGame.Domain.Entities.Card;
import CardGame.Domain.Entities.GameBoard;
import CardGame.UI.Helpers.ImageCache;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StyleCard extends JButton implements MouseListener {
    private Card card;
    private GameBoard gameBoard;
    private FlipCardUseCase flipCardUseCase;
    //Card size
    private final int cardWidth= 90;
    private final int cardHeight= 128;
    //Card images
    private ImageIcon cardBack = ImageCache.loadCardImage("back");
    private ImageIcon cardBackHover = ImageCache.loadCardImage("backHover");
    private ImageIcon cardFont;



    public StyleCard(Card card, GameBoard gameBoard) {
        this.card = card;
        this.gameBoard = gameBoard;
        flipCardUseCase = new FlipCardUseCase(gameBoard);
        cardFont = ImageCache.loadCardImage(this.card.getCardName());
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
        if(gameBoard.getFlippedCardsSize()< 2) {
            flipCardUseCase.execute(card.getRow(), card.getCol());
            this.setIcon(cardFont);
        }
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
