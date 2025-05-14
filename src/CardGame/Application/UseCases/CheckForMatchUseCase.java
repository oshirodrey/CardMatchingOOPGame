package CardGame.Application.UseCases;

import CardGame.Domain.Entities.GameBoard;

public class CheckForMatchUseCase {
   private GameBoard gameBoard;
   public CheckForMatchUseCase(GameBoard gameBoard) {

       this.gameBoard = gameBoard;
   }
   public void execute(){
       gameBoard.checkForMatch();
       //count moves?
   }
}
