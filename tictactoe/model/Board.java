package model;

public class Board {
    private static final int SIZE = 3;
    private final char[][] grid;

    public Board() {
        grid = new char[SIZE][SIZE];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                grid[row][col] = ' ';
            }
        }
    }

    public boolean placeMark(int row, int col, char symbol) {
        if (!isValidPosition(row, col)) {
            return false;
        }

        if (grid[row][col] != ' ') {
            return false;
        }

        grid[row][col] = symbol;
        return true;
    }

    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    public boolean isFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasWinner(char symbol) {
        return hasWinningRow(symbol) || hasWinningColumn(symbol) || hasWinningDiagonal(symbol);
    }

    private boolean hasWinningRow(char symbol) {
        for (int row = 0; row < SIZE; row++) {
            if (grid[row][0] == symbol && grid[row][1] == symbol && grid[row][2] == symbol) {
                return true;
            }
        }
        return false;
    }

    private boolean hasWinningColumn(char symbol) {
        for (int col = 0; col < SIZE; col++) {
            if (grid[0][col] == symbol && grid[1][col] == symbol && grid[2][col] == symbol) {
                return true;
            }
        }
        return false;
    }

    private boolean hasWinningDiagonal(char symbol) {
        return (grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol)
                || (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol);
    }

    public char getCell(int row, int col) {
        return grid[row][col];
    }

    public int getSize() {
        return SIZE;
    }

    public void display() {
        System.out.println();
        for (int row = 0; row < SIZE; row++) {
            System.out.println(" " + grid[row][0] + " | " + grid[row][1] + " | " + grid[row][2] + " ");
            if (row < SIZE - 1) {
                System.out.println("---+---+---");
            }
        }
        System.out.println();
    }
}
