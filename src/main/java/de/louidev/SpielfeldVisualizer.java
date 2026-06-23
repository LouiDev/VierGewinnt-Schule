package de.louidev;

public class SpielfeldVisualizer {
    public static String emptyField = "  1   2   3   4   5   6   7  \n" + //
            "+---+---+---+---+---+---+---+\n" + //
            "|   |   |   |   |   |   |   |\n" + //
            "+---+---+---+---+---+---+---+\n" + //
            "|   |   |   |   |   |   |   |\n" + //
            "+---+---+---+---+---+---+---+\n" + //
            "|   |   |   |   |   |   |   |\n" + //
            "+---+---+---+---+---+---+---+\n" + //
            "|   |   |   |   |   |   |   |\n" + //
            "+---+---+---+---+---+---+---+\n" + //
            "|   |   |   |   |   |   |   |\n" + //
            "+---+---+---+---+---+---+---+\n" + //
            "|   |   |   |   |   |   |   |\n" + //
            "+---+---+---+---+---+---+---+";

    public static String visualize(int[][] board) {

        final String[] symbols = {" ", "X", "O"};
        final String horizontal = "+---+---+---+---+---+---+---+";
        String output = "  1   2   3   4   5   6   7\n";

        for(int row = 0; row < board[row].length; row++) {

            output += horizontal + "\n";

            String line = "|";

            for (int col = 0; col < board.length; col++) {

                final int cell = board[col][row];
                line += " " + symbols[cell] + " |";

            }

            output += line + "\n";
        }

        output += horizontal;

        return output;
    }
}
