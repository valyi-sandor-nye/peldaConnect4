package game.command;

import game.Game;
import model.Board;
import service.BoardService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResetCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("^RESET$");

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public void execute(Matcher matcher, Game game) {
        game.getGameState().setBoard(new Board());
        game.setBoardService(new BoardService(game.getGameState().getBoard()));
        game.getGameState().setPlayer(1);
        System.out.println("Game reset.");
        game.printBoard();
    }
}
