package game.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import game.Game;


public class PrintCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("^PRINT$");

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public void execute(Matcher matcher, Game game) {
        game.printBoard();
    }
}
