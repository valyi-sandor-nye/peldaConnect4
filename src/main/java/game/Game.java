package game;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;

import exception.FullColumnException;
import game.command.*;
import model.Board;
import model.GameState;
import model.Move;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.BoardService;

public class Game {
    GameState gameState;
    BoardService boardService;
    private final Command[] commands = {
            new NameCommand(),
            new PutCommand(),
            new ExitCommand(),
            new ResetCommand(),
            new SaveCommand(),
            new LoadCommand(),
            new HighScoresCommand(),
            new DelNameCommand(),
            new PrintCommand()
    };
    private static final Logger logger = LoggerFactory.getLogger(Game.class);


    public Game() {
        Board b = new Board();
        gameState = new GameState(b, 1, 1, "");
        boardService = new BoardService(b);
    }

    public GameState getGameState() {
        return gameState;
    }

    public BoardService getBoardService() {
        return boardService;
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
        logger.error("Invalid command arrived.");
    }

    public void aiMove() {
        if (gameState.getExitus()) {
            return;
        }
        if (!gameState.getExitus() && checkEndState()) {
            return;
        }
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
            logger.info("ai made a move to col {}.", col);
        } catch (FullColumnException e) {
            System.err.println("Weird. The ai found out a bad column.");
            logger.warn("Weird. The ai found out a bad column.");
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

    public void run() {
        Scanner scanner = new Scanner(System.in);
        printBoard();
        while (true) {
            if (checkEndState()) {
                break;
            }
            System.out.print(gameState.getPlayerName() + "> ");
            String command = scanner.nextLine();
            processCommand(command);
        }
        scanner.close();
    }

    public boolean checkEndState() {
        if (boardService.isFull()) {
            System.out.println("The game is ended but  undecided, the board is full");
            logger.info("The game is ended but  undecided, the board is full");
            gameState.setExitus();
            return true;
        }
        if (boardService.hasWon(gameState.getHuman())) {
            System.out.println("Human (" + gameState.getPlayerName() + ") has won");
            logger.info("Human ({}) has won", gameState.getPlayerName());
            gameState.setExitus();
            new persistence.PointsDBSaver().add1point(gameState.getPlayerName());
            return true;
        } else if (boardService.hasWon(-gameState.getHuman())) {
            System.out.println("Machine has won");
            logger.info("Machine has won");
            new persistence.PointsDBSaver().add1point("ai");
            gameState.setExitus();
            return true;
        } else if (gameState.getExitus()) {
            System.out.println("Exit command exitcuted.");
            logger.info("Exit command exitcuted.");
            return true;
        }
        return false;
    }


}