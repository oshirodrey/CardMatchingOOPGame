package CardGame.Application.UseCases;

import CardGame.Domain.Entities.GameBoard;

public class FlipCardUseCase {
    private final GameBoard gameBoard;

    public FlipCardUseCase(GameBoard gameBoard) {

        this.gameBoard = gameBoard;
    }

    public void execute(int row, int col) {
        gameBoard.flipCard(row, col);

    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
