package wr4;

import java.util.Scanner;
import java.util.Random;



public class Main
{
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {

        final int SIZE = 5;
        char[][] board = new char[SIZE][SIZE];

        resetBoard(board);


        System.out.println("===== WELCOME TO THE GAME!! =====\n");
        showBoard(board);


        System.out.print(" Which symbol do you want to play, \"x\" or \"o\"? ");//спросить игрокаа чем [очет игрпть
        char userSymbol = sc.next().toLowerCase().charAt(0);
        char compSymbol = (userSymbol == 'x') ? 'o' : 'x';


        System.out.println();//спросить хочет ли игрок ходить первым
        System.out.print(" Do you want to go first (y/n)? ");
        char ans = sc.next().toLowerCase().charAt(0);

        int turn; // 0 — игрок, 1 — пк
        int remainCount = SIZE * SIZE;

// первый ход.
        if (ans == 'y') {
            turn = 0;
            userPlay(board, userSymbol);
        }
        else {
            turn = 1;
            compPlay(board, compSymbol);
        }

        showBoard(board);//показать разметку после хода
        remainCount--;


        boolean done = false;
        int winner = -1;

        while (!done && remainCount > 0) {

            done = isGameWon(board, turn, userSymbol, compSymbol); //проверка выиграл ли кто

            if (done)
                winner = turn; // последний ход побеждает
            else {//в случаен если никто не победил переходит к следующему ходу

                turn = (turn + 1 ) % 2;

                if (turn == 0)
                    userPlay(board, userSymbol);
                else
                    compPlay(board, compSymbol);


                showBoard(board);
                remainCount--;
            }
        }

// объявление победителя
        if (winner == 0)
            System.out.println("\n** YOU WON. CONGRATULATIONS!! **");
        else if (winner == 1)
            System.out.println("\n** YOU LOST.. Maybe next time :) **");
        else
            System.out.println("\n** DRAW... **");

    }

    public static void resetBoard(char[][] brd)
    {
        for (int i = 0; i < brd.length; i++)
            for (int j = 0; j < brd[0].length; j++)
                brd[i][j] = ' ';
    }

    public static void showBoard(char[][] brd)
    {
        int numRow = brd.length;
        int numCol = brd[0].length;

        System.out.println();


        System.out.print(" ");
        for (int i = 0; i < numCol; i++)
            System.out.print(i + " ");
        System.out.print('\n');

        System.out.println();

// создание таблицы
        for (int i = 0; i < numRow; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < numCol; j++) {
                if (j != 0)
                    System.out.print("|");
                System.out.print(" " + brd[i][j] + " ");
            }

            System.out.println();

            if (i != (numRow - 1)) {

                System.out.print(" ");
                for (int j = 0; j < numCol; j++) {
                    if (j != 0)
                        System.out.print("+");
                    System.out.print("---");
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void userPlay(char[][] brd, char usym)//ход игрока
    {
        System.out.print("\nEnter the row and column indices: ");
        int rowIndex = sc.nextInt();
        int colIndex = sc.nextInt();

        while (brd[rowIndex][colIndex] != ' ') {
            System.out.print("\n!! The cell is already taken.\nEnter the row and column indices: ");
            rowIndex = sc.nextInt();
            colIndex = sc.nextInt();
        }

        brd[rowIndex][colIndex] = usym;
    }

    public static void compPlay(char[][] brd, char csym)//ход ии
    {

        for (int i = 0; i < brd.length; i++) {
            for (int j = 0; j < brd[0].length; j++) {
                if (brd[i][j] == ' ') {
                    brd[i][j] = csym;
                    return;
                }

            }
        }
    }

    public static boolean isGameWon(char[][] brd, int turn, char usym, char csym)
    {
        char sym;
        if (turn == 0)
            sym = usym;
        else
            sym= csym;

        int i, j;
        boolean win = false;

// проверки  побед
        for (i = 0; i < brd.length-2 && !win; i++) {
            for (j = 0; j < brd[0].length-2; j++) {
                if (brd[i][j] != sym)
                    break;
            }
            if (j == brd[0].length-2)
                win = true;
        }


        for (j = 0; j < brd[0].length-2 && !win; j++) {
            for (i = 0; i < brd.length-2; i++) {
                if (brd[i][j] != sym)
                    break;
            }
            if (i == brd.length-2)
                win = true;
        }


        if (!win) {
            for (i = 0; i < brd.length-2; i++) {
                if (brd[i][i] != sym)
                    break;
            }
            if (i == brd.length-2)
                win = true;
        }


        if (!win) {
            for (i = 0; i < brd.length-2; i++) {
                if (brd[i][brd.length - 1 - i-2] != sym)
                    break;
            }
            if (i == brd.length-2)
                win = true;
        }

// возврат победы
        return win;
    }
}
