package game.command;


import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import game.Game;
import model.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaveCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("^SAVE$");
    private static final Logger logger = LoggerFactory.getLogger(Command.class);

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public void execute(Matcher matcher, Game game) {
        GameState gameState = game.getGameState();
        String filename = "connect4save_" + gameState.getPlayerName() + ".txt";
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(gameState.getPlayerName() + "\n");
            writer.write(gameState.getPlayer() + "\n");
            int[][] board = gameState.getBoard().getBoard();
            for (int[] row : board) {
                for (int cell : row) {
                    writer.write(cell + " ");
                }
                writer.write("\n");
            }
            System.out.println("Game saved to " + filename);
            logger.info("Game saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
            logger.error("Error saving game: " + e.getMessage());
        }
    }
}
