package CardGame.Infrastructure.Persistence;

import CardGame.Domain.Entities.GameBoard;
import CardGame.Domain.Entities.Score;
import CardGame.Domain.Services.ScoreRepository;

public class FileScoreRepository implements ScoreRepository {
    private GameBoard gameBoard;
    public FileScoreRepository(GameBoard gb) {
        this.gameBoard = gb;
    }
    @Override
    public void saveScore(Score score) {


    }

    @Override
    public int loadHighScore() {
        return 0;
    }
    //how to actually save your score to DB(in this case: maybe a JSON file)
    //implement the ScoreRepository interface
}
