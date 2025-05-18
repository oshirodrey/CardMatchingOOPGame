package CardGame.Domain.Services;

import CardGame.Domain.Entities.Score;
import java.util.List;

public interface IScoreRepository {
    void saveScore(Score score);
    List<Score> loadScores();
}