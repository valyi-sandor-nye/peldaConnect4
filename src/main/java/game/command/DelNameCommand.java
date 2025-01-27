package game.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import game.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DelNameCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("^DELNAME$");
    private static final Logger logger = LoggerFactory.getLogger(Command.class);

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public void execute(Matcher matcher, Game game) {
        game.getGameState().setPlayerName("");
        System.out.println("Player name set to an empty one ");
        logger.info("delname command executing");
    }
}

