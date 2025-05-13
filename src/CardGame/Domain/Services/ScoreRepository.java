package CardGame.Domain.Services;

public interface ScoreRepository {
    void saveScore(int score);
    int loadHighScore();
}