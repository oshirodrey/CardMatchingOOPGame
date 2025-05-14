package CardGame.Domain.Entities;

public class Score {
    private final int moveCount;
    private final long elapsedTime;

    public Score(int moveCount, long elapsedTime) {
        this.moveCount = moveCount;
        this.elapsedTime = elapsedTime;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }
}
