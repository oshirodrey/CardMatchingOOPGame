package CardGame.Infrastructure.Persistence;

import CardGame.Domain.Entities.GameBoard;
import CardGame.Domain.Entities.Score;
import java.util.*;
import CardGame.Domain.Services.IScoreRepository;

import java.util.List;

public class FileScoreRepository implements IScoreRepository {
    private GameBoard gameBoard;
    public FileScoreRepository(GameBoard gb) {
        this.gameBoard = gb;
    }
    @Override
    public void saveScore(Score score) {


    }

    @Override
    public List<Score> loadScores() {
        return new ArrayList<>();
    }
    //how to actually save your score to DB(in this case: maybe a JSON file)
    //implement the IScoreRepository interface
}
