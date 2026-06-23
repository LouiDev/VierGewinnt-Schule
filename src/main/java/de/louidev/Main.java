package de.louidev;

import java.util.Scanner;

public class Main {
    private static Scanner scanner;

    static void main() {
        Spielfeld spielfeld = new Spielfeld();
        boolean keepAlive = true;
        scanner = new Scanner(System.in);

        System.out.println(SpielfeldVisualizer.visualize(spielfeld.spielfeld()));

        while (keepAlive) {
            if (spielfeld.istSpielfeldVoll()) {
                keepAlive = false;
                System.out.println("Unentschieden! Spielfeld ist voll.");
                continue;
            }

            System.out.println("Spieler " + spielfeld.playerTurn() + " ist am Zug.");
            int rowInput = readRowInput();

            if (rowInput == -1) {
                System.out.println("Ungültige Eingabe!");
                continue;
            }

            if (spielfeld.steinSetzen(rowInput)) {
                System.out.println(SpielfeldVisualizer.visualize(spielfeld.spielfeld()));

                if (spielfeld.hatAktuellerSpielerGewonnen()) {
                    keepAlive = false;
                    System.out.println("Spieler " + spielfeld.playerTurn() + " hat gewonnen!");
                }

                spielfeld.switchPlayers();
            } else {
                System.out.println("Ungültige Eingabe oder Spalte voll!");
            }
        }

        scanner.close();
    }

    /**
     * Liest Nutzereingabe für die Spalte
     * @return Den Spaltenindex (Eingabe - 1)
     */
    static int readRowInput() {
        System.out.print("Spalte eingeben (1 - 7): ");
        String in = scanner.next();

        try {
            return Integer.parseInt(in) - 1;
        } catch (NumberFormatException ex) {
            return -1;
        }
    }
}
