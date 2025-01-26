import game.Game;

public class Main {
        public static void main(String[] args) {
            Game game = new Game();
            game.run();
        }
    }



    /*  public static void main2(String[] args) { //TODO THIS IS JUST FOR MANUAL TESTS
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

    } */

