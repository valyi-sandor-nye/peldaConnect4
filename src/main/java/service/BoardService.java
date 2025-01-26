package service;

import exception.FullColumnException;
import model.Board;
import model.Move;

public class BoardService {

    static int M = Board.getM();
    static int N = Board.getN();
    Board board;


    public BoardService(Board board) {
        this.board = board;
    }


    public boolean hasWon(int player) { //TODO
        return false;
    }

    public boolean isFull() {
        boolean foundEmpty = false;
        int[][] arr = board.getBoard();
        for (int col = 0; col < M; col++) {
            if (arr[0][col] == 0) {
                foundEmpty = true;
            }
        }
        return !foundEmpty;
    }

    public void doMove(Move move) throws FullColumnException {
        int[][] arr = board.getBoard();
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
}



