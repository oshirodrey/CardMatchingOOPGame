package CardGame.Application.UseCases;

import CardGame.Domain.Entities.Score;
import CardGame.Domain.Services.ScoreRepository;

public class SaveScoreUseCase {
    private final ScoreRepository scoreRepository;
    public SaveScoreUseCase(ScoreRepository repo) {
        this.scoreRepository = repo;
    } //Dependency injection
    public void execute(Score score) {
        scoreRepository.saveScore(score);
    }
    //will edit the flow later I guess
}
