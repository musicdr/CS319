import model.Game;
import repository.GameRepository;
import service.StatisticsService;
import ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        GameRepository repository = new GameRepository();
        StatisticsService statisticsService = new StatisticsService(repository);
        ConsoleUI consoleUI = new ConsoleUI();

        boolean keepRunning = true;

        while (keepRunning) {
            Game game = consoleUI.createGame();
            game.play();

            repository.save(game.toGameRecord());

            consoleUI.showGameSummary(game);
            consoleUI.showOverallStatistics(statisticsService);

            keepRunning = consoleUI.askToPlayAgain();
        }

        consoleUI.showGoodbyeMessage();
    }
}
