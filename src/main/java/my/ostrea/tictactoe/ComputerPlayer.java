package my.ostrea.tictactoe;

import java.util.Optional;

public class ComputerPlayer extends BasePlayer {
    private BasePlayer opponent;

    public ComputerPlayer(Board board, char symbol, BasePlayer opponent) {
        super(board, symbol);
        this.opponent = opponent;
    }

    @Override
    public void move() {
        int move = -1;
        int score = -2;

        for (int i : board.getEmptySquares()) {
            board.setPlayerForSquare(i, this);

            int tempScore = -minimax(opponent);

            board.setPlayerForSquare(i, null);

            if (tempScore > score) {
                score = tempScore;
                move = i;
            }
        }

        board.setPlayerForSquare(move, this);
    }

    private int minimax(BasePlayer player) {

        // How is the position like for player (their turn) on board?
        Optional<BasePlayer> winner = board.findWinner();

        if (winner.isPresent()) {
            if (winner.get() == player) {
                return 1;
            }
            return -1;
        }

        int move = -1;

        // Losing moves are preferred to no move.
        int score = -2;

        // For all legal moves.
        for (int i : board.getEmptySquares()) {
            // Try the move.
            board.setPlayerForSquare(i, player);
            int thisScore = -minimax(getAnotherPlayer(player));

            // Pick the one that's worst for the opponent.
            if (thisScore > score) {
                score = thisScore;
                move = i;
            }
            // Reset board after try.
            board.setPlayerForSquare(i, null);
        }

        return move == -1 ? 0 : score;
    }

    private BasePlayer getAnotherPlayer(BasePlayer player) {
        return player == this ? opponent : this;
    }
}
