import exception.FullColumnException;
import model.Board;
import model.Move;
import service.BoardService;

public class Main {
    public static void main(String[] args) throws FullColumnException {
        Board board = new Board();
        int N = Board.getN();
        int M = Board.getM();
        System.out.println(board.toString());
        var boardService = new BoardService(board);
        Move move = new Move();
        move.setPlayer(1);
        move.setCol(3);
        boardService.doMove(move);
        System.out.println(board.toString());
        move.setPlayer(-1);
        move.setCol(3);
        boardService.doMove(move);
        System.out.println(board.toString());
        boardService.doMove(move);
        boardService.doMove(move);
        boardService.doMove(move);
        boardService.doMove(move);
        boardService.doMove(move);
        System.out.println(board.toString());

    }
}
