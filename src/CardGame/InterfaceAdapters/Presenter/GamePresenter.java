package CardGame.InterfaceAdapters.Presenter;

import CardGame.Domain.Entities.GameBoard;
import CardGame.InterfaceAdapters.IUIController;

public class GamePresenter {
    private final GameBoard gameBoard;
    private final IUIController ui;

    public GamePresenter(GameBoard gameBoard, IUIController ui) {
        this.gameBoard = gameBoard;
        this.ui = ui;
    }

    public void presentFlip() {
        ui.updateCardIcons();
    }

    public void presentStatus() {
        ui.updateMoveAndTime(gameBoard.getMoveCount(), gameBoard.getElapsedTime());

        if (gameBoard.isAllCardsMatched()) {
            ui.showWinScreen(gameBoard.getScoreSnapshot());
        }
    }
}
