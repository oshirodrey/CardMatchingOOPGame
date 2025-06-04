package CardGame.InterfaceAdapters.Controller;

import CardGame.Application.UseCases.CheckForMatchUseCase;
import CardGame.Application.UseCases.FlipCardUseCase;
import CardGame.InterfaceAdapters.ISoundPlayer;
import CardGame.InterfaceAdapters.Presenter.GamePresenter;

import javax.swing.*;
/**
 * Handles player input logic for flipping cards and checking matches.
 * Acts as the Controller in a simplified MVC setup.
 */
public class GameController {
    private final FlipCardUseCase flipCardUseCase;
    private final CheckForMatchUseCase checkForMatchUseCase;
    private final GamePresenter presenter;
    private final ISoundPlayer soundPlayer;
    /**
     * Constructs the game controller with necessary dependencies.
     *
     * @param flipCardUseCase       use case for flipping cards
     * @param matchUseCase          use case for checking matches
     * @param presenter             presenter to update the UI
     * @param soundPlayer           sound player interface
     */
    public GameController(FlipCardUseCase flipCardUseCase, CheckForMatchUseCase matchUseCase, GamePresenter presenter, ISoundPlayer soundPlayer) {
        this.flipCardUseCase = flipCardUseCase;
        this.checkForMatchUseCase = matchUseCase;
        this.presenter = presenter;
        this.soundPlayer = soundPlayer;
    }

    /**
     * Triggered when a card is clicked.
     *
     * @param row row of clicked card
     * @param col column of clicked card
     */
    public void onCardClicked(int row, int col) {
        if (flipCardUseCase.getGameBoard().getFlippedCardsSize() < 2) {
            //get clicked card location by access the core card entity
            flipCardUseCase.execute(row, col);
            presenter.presentFlip();
            soundPlayer.play("cardFlip");

            if (flipCardUseCase.getGameBoard().getFlippedCardsSize() == 2) {

                Timer timer = new Timer(1000, e -> {
                    if (!checkForMatchUseCase.isMatched()) {
                        soundPlayer.play("cardFlip");
                    }/*check first before
                    the flippedCards list is reset*/
                    checkForMatchUseCase.execute();
                    presenter.presentFlip();// Loop through styleCards to update icons

                });
                timer.setRepeats(false);
                timer.start();

            }
        }
    }
}
