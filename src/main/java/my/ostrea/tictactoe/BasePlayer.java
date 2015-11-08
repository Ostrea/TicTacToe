package my.ostrea.tictactoe;

public abstract class BasePlayer {
    private char symbol;

    protected final Board board;

    protected BasePlayer(Board board, char symbol) {
        this.board = board;
        this.symbol = symbol;
    }

    public abstract void move();

    public char getSymbol() {
        return symbol;
    }
}
