package model;

public class GameRecord {
    private final String playerXName;
    private final String playerOName;
    private final GameResult result;
    private final int totalMoves;

    public GameRecord(String playerXName, String playerOName, GameResult result, int totalMoves) {
        this.playerXName = playerXName;
        this.playerOName = playerOName;
        this.result = result;
        this.totalMoves = totalMoves;
    }

    public String getPlayerXName() {
        return playerXName;
    }

    public String getPlayerOName() {
        return playerOName;
    }

    public GameResult getResult() {
        return result;
    }

    public int getTotalMoves() {
        return totalMoves;
    }

    @Override
    public String toString() {
        return "GameRecord{" +
                "playerXName='" + playerXName + '\'' +
                ", playerOName='" + playerOName + '\'' +
                ", result=" + result +
                ", totalMoves=" + totalMoves +
                '}';
    }
}
