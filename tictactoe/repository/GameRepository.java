package repository;

import model.GameRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameRepository {
    private final List<GameRecord> gameRecords;

    public GameRepository() {
        this.gameRecords = new ArrayList<>();
    }

    public void save(GameRecord gameRecord) {
        gameRecords.add(gameRecord);
    }

    public List<GameRecord> findAll() {
        return Collections.unmodifiableList(gameRecords);
    }

    public int count() {
        return gameRecords.size();
    }
}
