package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void testEquals_SameObject() {
        Board board1 = new Board();
        assertEquals(board1, board1, "Same object should be equal");
    }

    @Test
    public void testEquals_NullObject() {
        Board board1 = new Board();
        Board board2 = null;
        assertNotEquals(board1, board2, "Object compared with null should not be equal");
    }

    @Test
    public void testEquals_DifferentClass() {
        Board board1 = new Board();
        String notABoard = "Not a board";
        assertNotEquals(board1, notABoard, "Object of different class should not be equal");
    }

    @Test
    public void testEquals_EqualBoards() {
        Board board1 = new Board();
        Board board2 = new Board();
        assertEquals(board1, board2, "New boards  should be equal");
    }

    @Test
    public void testEquals_DifferentBoards() {
        Board board1 = new Board();
        Board board2 = new Board();
        board2.board[0][0] = 1;
        assertNotEquals(board1, board2, "Boards with different state should not be equal");
    }

    @Test
    public void testHashCode_EqualBoards() {
        Board board1 = new Board();
        Board board2 = new Board();
        assertEquals(board1.hashCode(), board2.hashCode(), "Equal boards should have same hash code");
    }

    @Test
    public void testHashCode_DifferentBoards() {
        Board board1 = new Board();
        Board board2 = new Board();
        board2.board[0][0] = 1;
        assertNotEquals(board1.hashCode(), board2.hashCode(), "Different boards should have different hash codes");
    }
}
