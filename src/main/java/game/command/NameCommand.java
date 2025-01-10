package game.command;

import game.Game;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("^NAME\\s+([a-zA-Z0-9]+)$");

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public void execute(Matcher matcher, Game game) {
        game.getGameState().setPlayerName(matcher.group(1));
        System.out.println("Player name set to: " + game.getGameState().getPlayerName());
    }
}
