package ui;

import model.Game;
import model.GameResult;
import model.Player;
import service.StatisticsService;

import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
    }

    public Game createGame() {
        System.out.println("=== TIC-TAC-TOE ===");

        System.out.print("Enter Player X name: ");
        String playerXName = scanner.nextLine();

        System.out.print("Enter Player O name: ");
        String playerOName = scanner.nextLine();

        Player playerX = new Player(playerXName, 'X');
        Player playerO = new Player(playerOName, 'O');

        return new Game(playerX, playerO);
    }

    public void showGameSummary(Game game) {
        System.out.println("=== GAME SUMMARY ===");

        GameResult result = game.getResult();

        switch (result) {
            case PLAYER_X_WINS:
                System.out.println("Winner: " + game.getPlayerX().getName() + " (X)");
                break;
            case PLAYER_O_WINS:
                System.out.println("Winner: " + game.getPlayerO().getName() + " (O)");
                break;
            case DRAW:
                System.out.println("Result: Draw");
                break;
            default:
                System.out.println("Game still in progress.");
        }

        System.out.println("Total moves: " + game.getMoveCount());
        System.out.println();
    }

    public void showOverallStatistics(StatisticsService statisticsService) {
        System.out.println("=== OVERALL STATISTICS ===");
        System.out.println("Games played: " + statisticsService.getTotalGamesPlayed());
        System.out.println("X wins: " + statisticsService.getTotalWinsForX());
        System.out.println("O wins: " + statisticsService.getTotalWinsForO());
        System.out.println("Draws: " + statisticsService.getTotalDraws());
        System.out.printf("Average moves per game: %.2f%n", statisticsService.getAverageMovesPerGame());
        System.out.println();
    }

    public boolean askToPlayAgain() {
        System.out.print("Play again? (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y");
    }

    public void showGoodbyeMessage() {
        System.out.println("Thank you for playing Tic-Tac-Toe.");
    }
}
