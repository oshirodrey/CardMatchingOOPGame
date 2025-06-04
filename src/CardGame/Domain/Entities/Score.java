package CardGame.Domain.Entities;

/**
 * Represents
 */
public class Score {
    private final int moveCount;
    private final double elapsedTime;

    public Score(int moveCount, double elapsedTime) {
        this.moveCount = moveCount;
        this.elapsedTime = elapsedTime;
    }

    public static Score fromString(String line) {
        String[] parts = line.split(",");
        int moves = Integer.parseInt(parts[0]);
        double time = Double.parseDouble(parts[1]);
        return new Score(moves, time);
    }

    public int getMoveCount() {
        return moveCount;
    }

    public double getElapsedTime() {
        return elapsedTime;
    }

    @Override
    public String toString() {
        return moveCount + "," + elapsedTime;
    }
}
