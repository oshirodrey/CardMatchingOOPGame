package CardGame.InterfaceAdapters.Presenter;

import CardGame.Domain.Entities.GameBoard;
import CardGame.InterfaceAdapters.ISoundPlayer;
import CardGame.InterfaceAdapters.IUIController;

public class GamePresenter {
    private final GameBoard gameBoard;
    private final IUIController ui;
    private final ISoundPlayer soundPlayer;

    public GamePresenter(GameBoard gameBoard, IUIController ui, ISoundPlayer soundPlayer) {
        this.gameBoard = gameBoard;
        this.ui = ui;
        this.soundPlayer = soundPlayer;
    }

    public void presentFlip() {
        ui.updateCardIcons();
        soundPlayer.play("cardFlip");
    }

    public void presentStatus() {
        ui.updateMoveAndTime(gameBoard.getMoveCount(), gameBoard.getElapsedTime());

        if (gameBoard.isAllCardsMatched()) {
            ui.showWinScreen(gameBoard.getScoreSnapshot());
        }
    }
}
