package game.command;

import exception.FullColumnException;
import game.Game;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Command {
    Pattern getPattern();
    void execute(Matcher matcher, Game game);
}
