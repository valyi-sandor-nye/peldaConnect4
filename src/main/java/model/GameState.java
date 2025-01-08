package model;

public class GameState {
    Board board;
    int player = 1;
    int human = 1;
    String playerName;

    GameState(Board board, int player, int human, String playerName) {
        this.board = board;
        this.player = player;
        this.human = human;
        this.playerName = playerName;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getHuman() {
        return human;
    }

    public void setHuman(int human) {
        this.human = human;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
