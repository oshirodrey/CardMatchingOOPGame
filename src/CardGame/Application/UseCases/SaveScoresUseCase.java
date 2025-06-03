package CardGame.Application.UseCases;

import CardGame.Domain.Entities.Score;
import CardGame.Domain.Services.IScoreRepository;
import CardGame.Domain.Utils.ScoreUtils;

import java.util.List;

public class SaveScoresUseCase {
    private final IScoreRepository repo;

    public SaveScoresUseCase(IScoreRepository repo) {
        this.repo = repo;
    } //Dependency injection

    public void execute(Score newScore) {
        List<Score> scores = repo.loadScores();
        scores.add(newScore);

        scores.sort(ScoreUtils.comparator());

        if (scores.size() > 10) {
            scores = scores.subList(0, 10);
        }

        repo.saveAll(scores);
    }
}
