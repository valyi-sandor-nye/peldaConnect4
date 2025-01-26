package game.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import game.Game;


public interface Command {
    Pattern getPattern();

    void execute(Matcher matcher, Game game);
}
