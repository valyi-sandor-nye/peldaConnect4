package game;

import java.util.Random;
import java.util.regex.Matcher;

import exception.FullColumnException;
import game.command.*;
import model.Board;
import model.GameState;
import model.Move;
import service.BoardService;

public class Game {
    GameState gameState;
    BoardService boardService;
    private final Command[] commands = {
            new NameCommand(),
            new PutCommand(),
            new DrawbackCommand(),
            new ResetCommand(),
            new SaveCommand(),
            new LoadCommand(),
            new HighScoresCommand(),
            new DelNameCommand()
    };

    public Game() {
        gameState = new GameState(new Board(), 1, 1, "");
        boardService = new BoardService(gameState.getBoard());
    }

    public GameState getGameState() {
        return gameState;
    }

    public BoardService getBoardService() {
        return boardService;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    public void processCommand(String commandString) {
        for (Command command : commands) {
            Matcher matcher = command.getPattern().matcher(commandString);
            if (matcher.matches()) {
                command.execute(matcher, this);
                return;
            }
        }
        System.out.println("Invalid command.");
    }

    public void aiMove() {
        Random random = new Random();
        int col;
        int[][] board = gameState.getBoard().getBoard();
        do {
            col = random.nextInt(Board.getM());
        } while (board[0][col] != 0);
        Move move = new Move();
        move.setCol(col);
        move.setPlayer(gameState.getHuman() * -1);
        try {
            boardService.doMove(move);
        } catch (FullColumnException e) {
            System.err.println("Érdekes szitu. Az ai rossz teli soszlopba lépett.");
        }
        gameState.setPlayer(gameState.getHuman());
    }

    public void printBoard() {
        int[][] board = gameState.getBoard().getBoard();
        System.out.println();
        for (int i = 0; i < Board.getN(); i++) {
            for (int j = 0; j < Board.getM(); j++) {
                System.out.print(sign(board[i][j]) + " ");
            }
            System.out.println();
        }
    }

    private char sign(int player) {
        return player == 1 ? 'x' : player == 0 ? '.' : 'o';
    }


}