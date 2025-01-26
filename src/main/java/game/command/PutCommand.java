package game.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.FullColumnException;
import game.Game;
import model.Board;
import model.Move;


public class PutCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("^PUT\\s+(\\d{1,2})$");

    @Override
    public Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public void execute(Matcher matcher, Game game) {
        int col = Integer.parseInt(matcher.group(1));
        if (col < 0 || col >= Board.getM()) {
            System.out.println("Invalid column number.");
            return;
        }
        Move move = new Move();
            move.setCol(col);
            move.setPlayer(game.getGameState().getHuman());
        try {
            game.getBoardService().doMove(move);
        } catch (FullColumnException fcex) {
            System.err.println("teli oszlopba raktál.");
        }
        game.getGameState().setPlayer(game.getGameState().getHuman() * -1);
        game.printBoard();
        game.aiMove();
        game.printBoard();

    }
}
