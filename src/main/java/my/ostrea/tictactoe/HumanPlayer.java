package my.ostrea.tictactoe;

import java.util.Scanner;

public class HumanPlayer extends BasePlayer {
    public HumanPlayer(Board board, char symbol) {
        super(board, symbol);
    }

    @Override
    public void move() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int i;
            do {
                System.out.println("\nInput first coordinate ([1..3]): ");
                i = scanner.nextInt();
            } while (i > 3 || i < 1);

            int j;
            do {
                System.out.println("\nInput second coordinate ([1..3]): ");
                j = scanner.nextInt();
            } while (j > 3 || j < 1);

            i--;
            j--;

            if (!board.getPlayerInSquare(i, j).isPresent()) {
                board.setPlayerForSquare(i, j, this);
            } else {
                continue;
            }

            break;
        }
    }
}
