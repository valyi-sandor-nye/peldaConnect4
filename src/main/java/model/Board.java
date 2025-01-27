package model;

import java.util.Arrays;

public class Board {
    static int N = 6;
    static int M = 7;
    int[][] board = new int[N][M]; // 0 -- empty, -1 -- red, 1 -- yellow

    public static int getN() {
        return N;
    }

    public static void setN(int n) {
        N = n;
    }

    public static int getM() {
        return M;
    }

    public static void setM(int m) {
        M = m;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return "Board{" +
                "board=" + Arrays.deepToString(board) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Board)) {
            return false;
        }
        Board board1 = (Board) o;
        return Arrays.deepEquals(board, board1.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }
}
