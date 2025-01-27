package game.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import game.Game;
import model.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.BoardService;

public class ResetCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("^RESET$");
    private static final Logger logger = LoggerFactory.getLogger(Command.class);

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public void execute(Matcher matcher, Game game) {
        game.getGameState().setBoard(new Board());
        game.setBoardService(new BoardService(game.getGameState().getBoard()));
        game.getGameState().setPlayer(1);
        game.printBoard();
        System.out.println("Game reset.");
        logger.info("Game reset command executed.");
    }
}
