package game.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import game.Game;


public class ExitCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("^EXIT$");

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public void execute(Matcher matcher, Game game) {
        game.getGameState().setExitus();
    }
}
