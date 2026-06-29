package de.louidev;

public class Spielfeld {
	private final int[][] spielfeld;
	private int playerTurn;

	private int lastPlacedX = -1;
	private int lastPlacedY = -1;

	private static final int PLAYER_ONE = 1;
	private static final int PLAYER_TWO = 2;
	private static final int BOARD_HEIGHT = 6;
	private static final int BOARD_WIDTH = 7;

	public Spielfeld() {
		playerTurn = PLAYER_ONE;
		spielfeld = new int[BOARD_WIDTH][BOARD_HEIGHT];
	}

	/**
	 * Wechselt den aktuellen Spieler
	 */
	public void switchPlayers() {
		playerTurn = playerTurn == PLAYER_ONE
				? PLAYER_TWO
				: PLAYER_ONE;
	}

	/**
	 * Gibt den aktuellen Spielerindex zurück
	 * @return den aktuellen Spielerindex
	 */

	public int playerTurn() {
		return playerTurn;
	}

	/**
	 * Gibt das Spielfeld zurück
	 * @return Das Spielfeld
	 */
	public int[][] spielfeld() {
		return spielfeld;
	}

	/**
	 * Versucht den Stein des aktuellen Spielers an der angegebenen Breite zu setzen.
	 * @param width die Breite
	 * @return true, wenn setzen erfolgreich, sonst false
	 */
	public boolean steinSetzen(int width) {
		int height = getFreeHeight(width);
		if (height == -1)
			return false;

		spielfeld[width][height] = playerTurn;
		lastPlacedX = width;
		lastPlacedY = height;
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

	/**
	 * Überprüft, ob das Spielfeld voll ist
	 * @return true, wenn das Spielfeld voll ist, sonst false
	 */
	public boolean istSpielfeldVoll() {
		for (int i = 0; i < BOARD_WIDTH; i++) {
			int index = spielfeld[i][0];
			if (index != PLAYER_ONE && index != PLAYER_TWO)
				return false;
		}

		return true;
	}

	/**
	 * @param x die horizontale Richtung in die geprüft wird. Kann -1, 0 oder 1 sein.
	 * @param y die vertikale Richtung in die geprüft wird. Kann -1, 0 oder 1 sein.
	 * @return Anzahl an Steinen die die gleiche Farbe wie der zu letzt platzierte Stein haben
	 */
	private int consecutiveValue(int x, int y) {
		int consecVal = 0;
		for(int i = 1; i < 4; i++) {
			boolean isOutOfHorizontalBound =
					x != 0 &&
					(x * i + lastPlacedX >= BOARD_WIDTH || x * i + lastPlacedX < 0);
			
			boolean isOutOfVerticalBound =
					y != 0 &&
					(y * i + lastPlacedY >= BOARD_HEIGHT || y * i + lastPlacedY < 0);

			boolean isOutBound = isOutOfHorizontalBound || isOutOfVerticalBound;

			if (isOutBound) break;
			if (spielfeld[lastPlacedX][lastPlacedY] == spielfeld[lastPlacedX + i * x][lastPlacedY + i * y]) consecVal++;
			else break;
			

		}
		return consecVal;
	}

	/**
	 * Überprüft das Spielfeld, ob der aktuelle Spieler am Zug gewonnen hat
	 * @return true, wenn der aktuelle Spieler gewonnen hat, sonst false
	 */
	public boolean hatAktuellerSpielerGewonnen() {
        boolean horizontalWin = consecutiveValue(-1, 0) + consecutiveValue(1, 0) > 2;
		boolean verticalWin = consecutiveValue(0, 1) > 2;
		boolean mainDiagonalWin = consecutiveValue(-1, -1) + consecutiveValue(1, 1) > 2;
		boolean sideDiagonalWin = consecutiveValue(-1, 1) + consecutiveValue(1, -1) > 2;

		return horizontalWin || verticalWin || mainDiagonalWin || sideDiagonalWin;
    }
}


