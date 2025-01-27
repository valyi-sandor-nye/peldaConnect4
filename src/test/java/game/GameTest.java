package game;

import exception.FullColumnException;
import model.Board;
import model.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.BoardService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameTest {

    private Game game;
    private BoardService mockBoardService;

    @BeforeEach
    void setUp() {
        game = new Game();
        mockBoardService = mock(BoardService.class);
        game.setBoardService(mockBoardService);
    }

    @Test
    void testGetGameState() {
        GameState gameState = game.getGameState();
        assertNotNull(gameState, "GameState should not be null");
    }

    @Test
    void testGetBoardService() {
        BoardService boardService = game.getBoardService();
        assertNotNull(boardService, "BoardService should not be null");
    }

    @Test
    void testSetBoardService() {
        BoardService newBoardService = new BoardService(new Board());
        game.setBoardService(newBoardService);
        assertEquals(newBoardService, game.getBoardService(), "BoardService should be set correctly");
    }

    @Test
    void testProcessCommand_ValidCommand() {
        game.processCommand("NAME player1");
        assertEquals("player1", game.getGameState().getPlayerName(), "Player name should be set to player1");
    }

    @Test
    void testProcessCommand_InvalidCommand() {
        game.processCommand("INVALID");
        // Here, we cannot assert System.out.println, but we can verify the logger
        // Not easily testable without changing the visibility of logger or adding a log appender
        // We can still check that no exception is thrown
    }

    @Test
    void testAiMove() throws FullColumnException {
        when(mockBoardService.isFull()).thenReturn(false);
        game.aiMove();
        verify(mockBoardService, times(1)).doMove(any());
    }

    @Test
    void testPrintBoard() {
        game.printBoard();
        // We cannot easily verify console output, but we can ensure no exceptions are thrown
    }

    @Test
    void testRun() {
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream("EXIT\n".getBytes()));
            game.run();
            assertTrue(game.getGameState().getExitus(), "Game should have exited");
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void testCheckEndState_BoardFull() {
        when(mockBoardService.isFull()).thenReturn(true);
        boolean result = game.checkEndState();
        assertTrue(result, "CheckEndState should return true if the board is full");
    }

    @Test
    void testCheckEndState_HumanWin() {
        when(mockBoardService.hasWon(game.getGameState().getHuman())).thenReturn(true);
        boolean result = game.checkEndState();
        assertTrue(result, "CheckEndState should return true if human has won");
    }

    @Test
    void testCheckEndState_MachineWin() {
        when(mockBoardService.hasWon(-game.getGameState().getHuman())).thenReturn(true);
        boolean result = game.checkEndState();
        assertTrue(result, "CheckEndState should return true if machine has won");
    }
}
