package CardGame.Domain.Entities;

public class Score {
    private final int moveCount;
    private final double elapsedTime;

    public Score(int moveCount, double elapsedTime) {
        this.moveCount = moveCount;
        this.elapsedTime = elapsedTime;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public double getElapsedTime() {
        return elapsedTime;
    }

    @Override
    public String toString() {
        return "Move: " + moveCount + " ElapsedTime: " + elapsedTime;
    }
}
