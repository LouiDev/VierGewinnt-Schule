package de.louidev;

public class Spielfeld {
	private final int spielfeld[][] = new int[BOARD_WIDTH][BOARD_HEIGHT];
	private int playerTurn = 0;

	private static final int PLAYER_ONE = 0;
	private static final int PLAYER_TWO = 1;
	private static final int BOARD_HEIGHT = 6;
	private static final int BOARD_WIDTH = 7;
}
