package model;

import java.util.Scanner;

public class Game {
    private final Board board;
    private final Player playerX;
    private final Player playerO;
    private Player currentPlayer;
    private GameResult result;
    private int moveCount;
    private final Scanner scanner;

    public Game(Player playerX, Player playerO) {
        this.board = new Board();
        this.playerX = playerX;
        this.playerO = playerO;
        this.currentPlayer = playerX;
        this.result = GameResult.IN_PROGRESS;
        this.moveCount = 0;
        this.scanner = new Scanner(System.in);
    }

    public void play() {
        System.out.println("\nStarting Tic-Tac-Toe!");
        board.display();

        while (result == GameResult.IN_PROGRESS) {
            processTurn();
            board.display();
            updateGameState();
            switchPlayerIfNeeded();
        }
    }

    private void processTurn() {
        boolean validMove = false;

        while (!validMove) {
            System.out.println(currentPlayer.getName() + " (" + currentPlayer.getSymbol() + "), enter row and column (1-3):");

            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;

            validMove = board.placeMark(row, col, currentPlayer.getSymbol());

            if (!validMove) {
                System.out.println("Invalid move. Try again.");
            } else {
                moveCount++;
            }
        }
    }

    private void updateGameState() {
        if (board.hasWinner(currentPlayer.getSymbol())) {
            if (currentPlayer.getSymbol() == 'X') {
                result = GameResult.PLAYER_X_WINS;
            } else {
                result = GameResult.PLAYER_O_WINS;
            }
        } else if (board.isFull()) {
            result = GameResult.DRAW;
        }
    }

    private void switchPlayerIfNeeded() {
        if (result == GameResult.IN_PROGRESS) {
            currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
        }
    }

    public GameRecord toGameRecord() {
        return new GameRecord(
                playerX.getName(),
                playerO.getName(),
                result,
                moveCount
        );
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayerX() {
        return playerX;
    }

    public Player getPlayerO() {
        return playerO;
    }

    public GameResult getResult() {
        return result;
    }

    public int getMoveCount() {
        return moveCount;
    }
}
