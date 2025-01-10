package game;

import exception.FullColumnException;
import game.command.*;
import model.Board;
import model.GameState;
import model.Move;
import service.BoardService;
import java.util.Random;
import java.util.regex.Matcher;
import game.command.*;

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
            new HighscoresCommand()
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

    public void processCommand(String commandString) throws FullColumnException {
        for (Command command : commands) {
            Matcher matcher = command.getPattern().matcher(commandString);
            if (matcher.matches()) {
                command.execute(matcher, this);
                return;
            }
        }
        System.out.println("Invalid command.");
    }

    public void aiMove() throws FullColumnException {
        Random random = new Random();
        int col;
        int[][] board = gameState.getBoard().getBoard();
        do {
            col = random.nextInt(Board.getM());
        } while (board[0][col] != 0);
        Move move = new Move();
        move.setCol(col);
        move.setPlayer(gameState.getHuman() * -1);
        boardService.doMove(move);
        gameState.setPlayer(gameState.getHuman());
    }

    public void printBoard(){
        int[][] board = gameState.getBoard().getBoard();
        System.out.println();
        for (int i = 0; i< Board.getN(); i++){
            for(int j = 0; j< Board.getM(); j++){
                System.out.print(sign(board[i][j])+" ");
            }
            System.out.println();
        }
    }

    private char sign(int player) {
        return player == 1 ? 'X': player == 0? '.':'o';
    }

    public static void main3(String[] args) throws FullColumnException { // TODO REMOVE
            Game game = new Game();
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            game.printBoard();
            while (true) {
                System.out.print("> ");
                String command = scanner.nextLine();
                game.processCommand(command);
            }
    }

}