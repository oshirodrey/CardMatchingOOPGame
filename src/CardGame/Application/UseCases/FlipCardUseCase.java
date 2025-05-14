package CardGame.Application.UseCases;

import CardGame.Domain.Entities.GameBoard;

public class FlipCardUseCase {
    private GameBoard gameBoard;
    public FlipCardUseCase(GameBoard gameBoard) {

        this.gameBoard = gameBoard;
    }
    public void execute(int col, int row) {
        gameBoard.flipCard(col, row);
    }
}
