package my.ostrea.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Board {
    private final Optional<BasePlayer>[] field;
    private final int width;
    private final int height;

    @SuppressWarnings("unchecked")
    public Board() {
        width = height = 3;
        field = new Optional[width*height];
        for (int i = 0; i < width * height; i++) {
            field[i] = Optional.empty();
        }
    }

    // TODO rewrite
    public Optional<BasePlayer> findWinner() {
        int[][] wins = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 },
                { 2, 5, 8 }, { 0, 4, 8 }, { 2, 4, 6 } };

        for (int[] win : wins) {
            if (field[win[0]].isPresent()) {
                if (field[win[1]].isPresent() &&
                        field[win[0]].get().getSymbol() == field[win[1]].get().getSymbol()) {
                    if (field[win[2]].isPresent() &&
                            field[win[0]].get().getSymbol() == field[win[2]].get().getSymbol()) {
                        return field[win[2]];
                    }
                }
            }
        }
        return Optional.empty();
    }

    public void draw() {
        System.out.println("--|--|--");
        for (int i = 0; i < field.length; i += 3) {
            System.out.println(getSymbol(field[i]) + " | " + getSymbol(field[i + 1]) +
                    "| " + getSymbol(field[i + 2]));
            System.out.println("--|--|--");
        }
    }

    private char getSymbol(Optional<BasePlayer> player) {
        return player.map(BasePlayer::getSymbol).orElse(' ');
    }

    public Optional<BasePlayer> getPlayerInSquare(int i, int j) {
        return field[convertToOneDimensionalCoordinate(i, j)];
    }

    public void setPlayerForSquare(int position, BasePlayer player) {
        field[position] = Optional.ofNullable(player);
    }

    public void setPlayerForSquare(int i, int j, BasePlayer player) {
        setPlayerForSquare(convertToOneDimensionalCoordinate(i, j), player);
    }

    private int convertToOneDimensionalCoordinate(int i, int j) {
        return i * width + j;
    }

    public List<Integer> getEmptySquares() {
        List<Integer> emptySquares = new ArrayList<>();
        for (int i = 0; i < width * height; i++) {
            if (!field[i].isPresent()) {
                emptySquares.add(i);
            }
        }
        return emptySquares;
    }
}
