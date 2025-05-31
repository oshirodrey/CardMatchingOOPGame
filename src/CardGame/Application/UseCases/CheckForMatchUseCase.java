package CardGame.Application.UseCases;

import CardGame.Domain.Entities.GameBoard;

public class CheckForMatchUseCase {
   private GameBoard gameBoard;
   public CheckForMatchUseCase(GameBoard gameBoard) {

       this.gameBoard = gameBoard;
   }
   public void execute(){
       gameBoard.when2CardsFlipped();
   }
   public boolean isMatched(){
       return gameBoard.checkForMatch();
   }

}
