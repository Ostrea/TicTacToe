package my.ostrea.tictactoe;

import java.util.Optional;
import java.util.Scanner;

public class Game {
    private final Board board;
    private BasePlayer firstPlayer;
    private BasePlayer secondPlayer;

    public Game() {
        board = new Board();
    }

    public void start() {
        Pair userInput = getUserInput();
        createPlayers(userInput.humanFirst, userInput.symbol);
        play(userInput.humanFirst);
        finish();
    }

    private void finish() {
        Optional<BasePlayer> winner = board.findWinner();

        System.out.println(winner.map(winnerPlayer -> winnerPlayer.getSymbol() + " has won! Congratulations!")
                .orElse("Draw!"));
    }

    private void play(boolean humanFirst) {
        String formatString = "You: %c, Second player: %c";
        String outputString;
        if (humanFirst) {
            outputString = String.format(formatString, firstPlayer.getSymbol(), secondPlayer.getSymbol());
        } else {
            outputString = String.format(formatString, secondPlayer.getSymbol(), firstPlayer.getSymbol());
        }
        System.out.println(outputString);

        for (int turn = 0; turn < 9 && !board.findWinner().isPresent(); turn++) {
            System.out.println("---------------");
            board.draw();
            if (turn % 2 == 0) {
                firstPlayer.move();
            } else {
                secondPlayer.move();
            }

        }

        board.draw();
    }

    private void createPlayers(boolean humanFirst, char symbol) {
        firstPlayer = new HumanPlayer(board, symbol);
        secondPlayer = new ComputerPlayer(board, 'O', firstPlayer);

        if (humanFirst) {
            return;
        }

        BasePlayer temp = firstPlayer;
        firstPlayer = secondPlayer;
        secondPlayer = temp;
    }

    private static class Pair {
        public boolean humanFirst;
        public char symbol;
    }

    private static Pair getUserInput() {
        System.out.println("Would you like to go first?[Y/N]");
        Scanner scanner = new Scanner(System.in);
        String  answer = scanner.next().toUpperCase();
        Pair result = new Pair();
        result.humanFirst = "Y".equals(answer);

        System.out.println("Choose your symbol:");
        result.symbol = scanner.next().toUpperCase().charAt(0);
        return result;
    }
}
