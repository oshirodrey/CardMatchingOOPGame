package CardGame.Domain.Services;

import CardGame.Domain.Entities.Score;

import java.util.List;

public interface IScoreRepository {

    List<Score> loadScores();

    void saveAll(List<Score> scores);
}