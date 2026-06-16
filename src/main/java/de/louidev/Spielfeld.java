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
			if (index == PLAYER_ONE || index == PLAYER_TWO)
				return true;
		}

		return false;
	}

	/**
	 * Überprüft das Spielfeld, ob der aktuelle Spieler am Zug gewonnen hat
	 * @return true, wenn der aktuelle Spieler gewonnen hat, sonst false
	 */
	public boolean hatAktuellerSpielerGewonnen() {
		int width = lastPlacedX;
		int height = lastPlacedY;

		int consecHorizontals = 0;
		int consecVertical = 0;
		int consecMainDiagonal = 0;
		int consecSideDiagonal = 0;

		for(int i = 1; i < 4; i++) {
			boolean isOutOfRightBound = width + i >= BOARD_WIDTH - 1;
			boolean isOutOfLeftBound = width - i >= BOARD_WIDTH -1 ;
			boolean isOutOfTopBound = height - i >= BOARD_HEIGHT - 1;
			boolean isOutOfBottomBound = height + i >= BOARD_HEIGHT - 1;

			//Horizontaler Check
			if (!isOutOfRightBound) {
				if(spielfeld[width][height] == spielfeld[width+i][height]) {
					consecHorizontals++;
				}
			};

			if (!isOutOfLeftBound) {
				if(spielfeld[width][height] == spielfeld[width-i][height]) {
					consecHorizontals++;
				}
			};


			// if(consecHorizontals > 3) return true;

			//Vertikaler Check
			if (!isOutOfTopBound) {
				if(spielfeld[width][height] == spielfeld[width][height-i]) {
					consecVertical++;
				}
			};


			if (!isOutOfBottomBound) {
				if(spielfeld[width][height] == spielfeld[width][height+i]) {
					consecVertical++;
				}
			};



			//Hauptdiagonaler Check

			if (!isOutOfRightBound && !isOutOfBottomBound) {
				if(spielfeld[width][height] == spielfeld[width+i][height+i] ) {
					consecMainDiagonal++;
				}
			};



			if (!isOutOfLeftBound && !isOutOfTopBound) {
				if(spielfeld[width][height] == spielfeld[width-i][height-i]) {
					consecMainDiagonal++;
				}
			};




			//Seitdiagonaler Check

			if (!isOutOfRightBound && !isOutOfTopBound) {
				if(spielfeld[width][height] == spielfeld[width+i][height-i]) {
					consecSideDiagonal++;
				}
			}

			if (!isOutOfRightBound && !isOutOfBottomBound) {
				if(spielfeld[width][height] == spielfeld[width+i][height]) {
					consecHorizontals++;
				}

				if(spielfeld[width][height] == spielfeld[width-i][height+i]) {
					consecSideDiagonal++;
				}
			}
		}

        return consecHorizontals > 3 ||
                consecVertical > 3 ||
                consecMainDiagonal > 3 ||
                consecSideDiagonal > 3;
    }
}
