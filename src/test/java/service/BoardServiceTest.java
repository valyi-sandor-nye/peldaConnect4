package service;

import exception.FullColumnException;
import model.Board;
import model.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardServiceTest {

    private Board board;
    private BoardService boardService;

    @BeforeEach
    void setUp() {
        board = new Board();
        boardService = new BoardService(board);
    }

    @Test
    void testIsFull_EmptyBoard() {
        assertFalse(boardService.isFull(), "The board should not be full initially");
    }

    @Test
    void testIsFull_FullBoard() {
        for (int i = 0; i < Board.getM(); i++) {
            for (int j = 0; j < Board.getN(); j++) {
                board.getBoard()[j][i] = 1;
            }
        }
        assertTrue(boardService.isFull(), "The board should be full");
    }

    @Test
    void testDoMove_ValidMove() throws FullColumnException {
        Move move = new Move();
        move.setCol(0);
        move.setPlayer(1);
        boardService.doMove(move);
        assertEquals(1, board.getBoard()[Board.getN() - 1][0], "The move should be placed correctly");
    }

    @Test
    void testDoMove_FullColumn() {
        Move move = new Move();
        move.setCol(0);
        move.setPlayer(1);
        for (int i = 0; i < Board.getN(); i++) {
            board.getBoard()[i][0] = 1;
        }
        assertThrows(FullColumnException.class, () -> boardService.doMove(move), "Should throw FullColumnException for a full column");
    }

    @Test
    void testHasWon_HorizontalWin() {
        int player = 1;
        for (int i = 0; i < 4; i++) {
            board.getBoard()[0][i] = player;
        }
        assertTrue(boardService.hasWon(player), "The player should win horizontally");
    }

    @Test
    void testHasWon_VerticalWin() {
        int player = 1;
        for (int i = 0; i < 4; i++) {
            board.getBoard()[i][0] = player;
        }
        assertTrue(boardService.hasWon(player), "The player should win vertically");
    }

    @Test
    void testHasWon_DiagonalLeftToRightWin() {
        int player = 1;
        for (int i = 0; i < 4; i++) {
            board.getBoard()[i][i] = player;
        }
        assertTrue(boardService.hasWon(player), "The player should win diagonally from left to right");
    }

    @Test
    void testHasWon_DiagonalRightToLeftWin() {
        int player = 1;
        for (int i = 0; i < 4; i++) {
            board.getBoard()[i][Board.getM() - 1 - i] = player;
        }
        assertTrue(boardService.hasWon(player), "The player should win diagonally from right to left");
    }
}
