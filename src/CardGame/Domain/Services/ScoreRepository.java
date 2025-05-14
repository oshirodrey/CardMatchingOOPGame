package CardGame.Domain.Services;

import CardGame.Domain.Entities.Score;

public interface ScoreRepository {
    void saveScore(Score score);
    int loadHighScore();
}