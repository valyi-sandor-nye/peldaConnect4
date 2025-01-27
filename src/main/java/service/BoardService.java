package service;

import exception.FullColumnException;
import model.Board;
import model.Move;

public class BoardService {

    int M = Board.getM();
    int N = Board.getN();
    Board board;
    int[][] arr;

    public BoardService(Board board) {
        this.board = board;
        M = Board.getM();
        N = Board.getN();
        arr = board.getBoard();
    }

    public boolean isFull() {
        boolean foundEmpty = false;
        for (int col = 0; col < M; col++) {
            if (arr[0][col] == 0) {
                foundEmpty = true;
                break;
            }
        }
        return !foundEmpty;
    }

    public void doMove(Move move) throws FullColumnException {
        int actualCol = move.getCol();
        int row = 0;
        while (row < N   && arr[row][actualCol] == 0) {
            row++;
        }
        if (row == N) {
            arr[N - 1][actualCol] = move.getPlayer();
        } else {
            if (!(arr[0][actualCol] == 0)) {
                throw new FullColumnException();
            } else {
                arr[row - 1][actualCol] = move.getPlayer();
            }
        }
    }

    // Ellenőrzés, hogy egy játékos nyert-e
    public boolean hasWon(int player) {
        return checkHorizontal(player) || checkVertical(player) ||
                checkDiagonalLeftToRight(player) || checkDiagonalRightToLeft(player);
    }

    // Vízszintes ellenőrzés
    private boolean checkHorizontal(int player) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M - 3; j++) {
                if (arr[i][j] == player && arr[i][j + 1] == player &&
                        arr[i][j + 2] == player && arr[i][j + 3] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    // Függőleges ellenőrzés
    private boolean checkVertical(int player) {
        for (int i = 0; i < N - 3; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == player && arr[i + 1][j] == player &&
                        arr[i + 2][j] == player && arr[i + 3][j] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    // Átlós ellenőrzés balról jobbra
    private boolean checkDiagonalLeftToRight(int player) {
        for (int i = 0; i < N - 3; i++) {
            for (int j = 0; j < M - 3; j++) {
                if (arr[i][j] == player && arr[i + 1][j + 1] == player &&
                        arr[i + 2][j + 2] == player && arr[i + 3][j + 3] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    // Átlós ellenőrzés jobbról balra
    private boolean checkDiagonalRightToLeft(int player) {
        for (int i = 0; i < N - 3; i++) {
            for (int j = 3; j < M; j++) {
                if (arr[i][j] == player && arr[i + 1][j - 1] == player &&
                        arr[i + 2][j - 2] == player && arr[i + 3][j - 3] == player) {
                    return true;
                }
            }
        }
        return false;
    }

}



