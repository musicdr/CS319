package service;

import model.GameRecord;
import model.GameResult;
import repository.GameRepository;

import java.util.List;

public class StatisticsService {
    private final GameRepository repository;

    public StatisticsService(GameRepository repository) {
        this.repository = repository;
    }

    public int getTotalGamesPlayed() {
        return repository.count();
    }

    public int getTotalDraws() {
        int draws = 0;
        for (GameRecord record : repository.findAll()) {
            if (record.getResult() == GameResult.DRAW) {
                draws++;
            }
        }
        return draws;
    }

    public int getTotalWinsForX() {
        int wins = 0;
        for (GameRecord record : repository.findAll()) {
            if (record.getResult() == GameResult.PLAYER_X_WINS) {
                wins++;
            }
        }
        return wins;
    }

    public int getTotalWinsForO() {
        int wins = 0;
        for (GameRecord record : repository.findAll()) {
            if (record.getResult() == GameResult.PLAYER_O_WINS) {
                wins++;
            }
        }
        return wins;
    }

    public double getAverageMovesPerGame() {
        List<GameRecord> records = repository.findAll();

        if (records.isEmpty()) {
            return 0.0;
        }

        int totalMoves = 0;
        for (GameRecord record : records) {
            totalMoves += record.getTotalMoves();
        }

        return (double) totalMoves / records.size();
    }
}
