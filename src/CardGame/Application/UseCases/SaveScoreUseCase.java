package CardGame.Application.UseCases;

import CardGame.Domain.Entities.Score;
import CardGame.Domain.Services.IScoreRepository;

public class SaveScoreUseCase {
    private final IScoreRepository scoreRepository;
    public SaveScoreUseCase(IScoreRepository repo) {
        this.scoreRepository = repo;
    } //Dependency injection
    public void execute(Score score) {
        scoreRepository.saveScore(score);
    }
    //will edit the flow later I guess
}
