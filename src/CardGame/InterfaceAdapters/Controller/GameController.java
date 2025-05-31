package CardGame.InterfaceAdapters.Controller;

import CardGame.Application.UseCases.CheckForMatchUseCase;
import CardGame.Application.UseCases.FlipCardUseCase;
import CardGame.InterfaceAdapters.ISoundPlayer;
import CardGame.InterfaceAdapters.Presenter.GamePresenter;

import javax.swing.*;

public class GameController {
    private final FlipCardUseCase flipCardUseCase;
    private final CheckForMatchUseCase checkForMatchUseCase;
    private final GamePresenter presenter;
    private final ISoundPlayer soundPlayer;

    public GameController(FlipCardUseCase flipCardUseCase, CheckForMatchUseCase matchUseCase, GamePresenter presenter, ISoundPlayer soundPlayer) {
        this.flipCardUseCase = flipCardUseCase;
        this.checkForMatchUseCase = matchUseCase;
        this.presenter = presenter;
        this.soundPlayer = soundPlayer;
    }

    public void onCardClicked(int row, int col) {
        if (flipCardUseCase.getGameBoard().getFlippedCardsSize() < 2) {
            //get clicked card location by access the core card entity
            flipCardUseCase.execute(row,col);
            presenter.presentFlip();
            soundPlayer.play("cardFlip");

            if (flipCardUseCase.getGameBoard().getFlippedCardsSize() == 2) {

                Timer timer = new Timer(1000, e -> {
                    if(!checkForMatchUseCase.isMatched()) {soundPlayer.play("cardFlip");}/*check first before
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
