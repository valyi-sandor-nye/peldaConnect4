package game.command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import game.Game;
import model.Board;
import model.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.BoardService;


public class LoadCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("^LOAD$");
    private static final Logger logger = LoggerFactory.getLogger(Command.class);

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public void execute(Matcher matcher, Game game) {
        GameState gamestate = game.getGameState();
        if (gamestate.getPlayerName() == null || gamestate.getPlayerName().isEmpty()) {
            System.out.println("Player name must be set before loading.");
            logger.error("load command execution tried without use name");
            return;
        }

        String filename = "connect4save_" + gamestate.getPlayerName() + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            gamestate.setPlayerName(reader.readLine());
            gamestate.setPlayer(Integer.parseInt(reader.readLine()));
            int[][] boardData = new int[Board.getN()][Board.getM()];
            for (int i = 0; i < Board.getN(); i++) {
                String[] rowData = reader.readLine().split(" ");
                for (int j = 0; j < Board.getM(); j++) {
                    boardData[i][j] = Integer.parseInt(rowData[j]);
                }
            }
            gamestate.setBoard(new Board());
            gamestate.getBoard().setBoard(boardData);
            game.setBoardService(new BoardService(gamestate.getBoard()));
            System.out.println("Game loaded from " + filename);
            game.printBoard();
            logger.info("Game loaded from " + filename);

        } catch (IOException e) {
            System.err.println("Error loading game: " + e.getMessage());
            logger.error("Error loading game: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error loading game: Invalid file format");
            logger.error("Error loading game: Invalid file format");
        }
    }
}
