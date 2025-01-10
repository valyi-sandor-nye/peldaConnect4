package game.command;

import game.Game;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DrawbackCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("^DRAWBACK1$");

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public void execute(Matcher matcher, Game game) {
        // Implement drawback logic here
        System.out.println("Drawback command executed (not fully implemented).");
    }
}
