package game.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.FullColumnException;
import game.Game;
import model.Board;
import model.Move;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PutCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("^PUT\\s+(\\d{1,2})$");

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }
    private static final Logger logger = LoggerFactory.getLogger(Command.class);

    @Override
    public void execute(Matcher matcher, Game game) {
        int col = Integer.parseInt(matcher.group(1));
        if (col < 0 || col >= Board.getM()) {
            System.out.println("Invalid column number.");
            logger.error("Put command execution error: invald column number");
            return;
        }
        Move move = new Move();
            move.setCol(col);
            move.setPlayer(game.getGameState().getHuman());
        try {
            game.getBoardService().doMove(move);
            logger.info("put command executed into col "+col+".");
        } catch (FullColumnException fcex) {
            System.err.println("teli oszlopba raktál.");
            logger.error("teli oszlopba rakott a humán.");
        }
        game.getGameState().setPlayer(game.getGameState().getHuman() * -1);
        game.printBoard();
        game.aiMove();
        game.printBoard();

    }
}
