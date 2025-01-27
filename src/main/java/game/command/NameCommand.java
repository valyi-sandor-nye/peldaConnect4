package game.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import game.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NameCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("^NAME\\s+([a-zA-Z0-9]+)$");
    private static final Logger logger = LoggerFactory.getLogger(Command.class);

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public void execute(Matcher matcher, Game game) {
        game.getGameState().setPlayerName(matcher.group(1));
        System.out.println("Player name set to: " + game.getGameState().getPlayerName());
        logger.info("Player name set to: " + game.getGameState().getPlayerName());
    }
}
