package de.louidev;

public class Spielfeld {
	private final int[][] spielfeld;
	private int playerTurn;

	private static final int PLAYER_ONE = 1;
	private static final int PLAYER_TWO = 2;
	private static final int BOARD_HEIGHT = 6;
	private static final int BOARD_WIDTH = 7;

	public Spielfeld() {
		playerTurn = PLAYER_ONE;
		spielfeld = new int[BOARD_WIDTH][BOARD_HEIGHT];
	}

	/**
	 * Versucht den Stein des aktuellen Spielers an der angegebenen Breite zu setzen.
	 * @param width die Breite
	 * @return true, wenn setzen erfolgreich, sonst false
	 */
	private boolean steinSetzen(int width) {
		int height = getFreeHeight(width);
		if (height == -1)
			return false;

		spielfeld[width][height] = playerTurn;
		return true;
	}

	/**
	 * Gibt die nächst freie Höhe an der angegeben width zurück, oder -1, wenn die Spalte voll ist.
	 * @param width die Breite
	 * @return die nächst freie Höhe oder -1
	 */
	private int getFreeHeight(int width) {
		int indexAtTop = spielfeld[width][0];

		if (indexAtTop == PLAYER_ONE || indexAtTop == PLAYER_TWO)
			return -1;

		for (int i = BOARD_HEIGHT - 1; i > -1; i--) {
			int index = spielfeld[width][i];
			if (index != PLAYER_ONE && index != PLAYER_TWO)
				return i;
		}

		return -1;
	}
}
