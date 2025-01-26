package game.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import game.Game;


public class DelNameCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("^DELNAME$");

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public void execute(Matcher matcher, Game game) {
        game.getGameState().setPlayerName("");
        System.out.println("Player name set to an empty one ");
    }
}

