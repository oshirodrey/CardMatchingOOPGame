package CardGame.Application.UseCases;

import CardGame.Domain.Entities.Score;
import CardGame.Domain.Services.IScoreRepository;

import java.util.ArrayList;
import java.util.List;

public class LoadScoresUseCase {
    private final IScoreRepository repo;
    public LoadScoresUseCase(IScoreRepository repo) {
        this.repo = repo;
    }

    public List<Score> execute() {
        List<Score> scores;
        scores= repo.loadScores();
        return scores;
    }
}
