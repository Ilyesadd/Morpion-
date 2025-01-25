import java.util.Scanner;

public class Morpion {

    private static final char EMPTY = '-';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    private static char[][] board;
    private static char currentPlayer;

    public static void main(String[] args) {
        initializeBoard();
        currentPlayer = PLAYER_X;
        boolean gameRunning = true;

        System.out.println("Bienvenue au jeu du Morpion !");

        while (gameRunning) {
            printBoard();
            playerMove();

            if (checkWin()) {
                printBoard();
                System.out.println("Le joueur " + currentPlayer + " a gagné !");
                gameRunning = false;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("Match nul !");
                gameRunning = false;
            } else {
                switchPlayer();
            }
        }
    }

    private static void initializeBoard() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private static void printBoard() {
        System.out.println("\nPlateau actuel :");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void playerMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        while (true) {
            System.out.println("\nJoueur " + currentPlayer + ", entrez votre coup (ligne et colonne, de 1 à 3) :");
            System.out.print("Ligne : ");
            row = scanner.nextInt() - 1;
            System.out.print("Colonne : ");
            col = scanner.nextInt() - 1;

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == EMPTY) {
                board[row][col] = currentPlayer;
                break;
            } else {
                System.out.println("Coup invalide. Réessayez.");
            }
        }
    }

    private static boolean checkWin() {
        // Vérification des lignes et des colonnes
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }

        // Vérification des diagonales
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }

        return false;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }
}
