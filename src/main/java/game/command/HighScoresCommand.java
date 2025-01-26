package game.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import game.Game;


public class HighScoresCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("^HIGHSCORES$");

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public void execute(Matcher matcher, Game game) {
        System.out.println("Highscores command executed (placeholder).");
    }
}
