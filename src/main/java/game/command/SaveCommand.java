package game.command;

import game.Game;
import model.GameState;

import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaveCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("^SAVE$");

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
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }
}
