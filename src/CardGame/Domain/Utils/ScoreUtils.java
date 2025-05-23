package CardGame.Domain.Utils;

import CardGame.Domain.Entities.Score;

import java.util.Comparator;

public class ScoreUtils {
    public static Comparator<Score> comparator() {
        return Comparator
                .comparingInt(Score::getMoveCount)
                .thenComparingDouble(Score::getElapsedTime);
    }
}
