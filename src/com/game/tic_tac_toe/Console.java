package com.game.tic_tac_toe;

import java.util.Scanner;

/**
 *  Класс имеет ряд методов для работы с данными игроков в ходе игры и предназначен для реализации полного функционала во время игры (консольная версия)
 *  Все переменные и методы объявлены как статические (принадлежат классу)
 */
public class Console {

    // Имя игрока для представления содержимого ячеек
    public static final int EMPTY = 0;
    public static final int USER1 = 1;
    public static final int USER2 = 2;

    // Различные состояний игры
    public static final int PLAYING = 0;
    public static final int DRAW = 1;
    public static final int USER1_WON = 2;
    public static final int USER2_WON = 3;

    // Игровая доска и статус игры
    public static final int ROWS = 3, COLS = 3; // количество строк и столбцов
    public static int[][] board = new int[ROWS][COLS]; // игровая доска в двух мерном массиве
    //  двух мерном массив игроков (USER1, USER2, NOUGHT)
    public static int currentState;  // статус игры (PLAYING, DRAW, USER1_WON, USER2_WON)
    public static int currentPlayer; // текущий игрок (CROSS или NOUGHT)
    public static int currntRow, currentCol; // текущие столбец и строка

    public static Scanner in = new Scanner(System.in); // ввод данных в консоль

    /**
     * Основной метод записи (здесь запускается программа)
     */
    public static void main(String[] args) {
        initGame(); // Инициализация игровой доски и текущего статуса
        do {
            playerMove(currentPlayer); // обновление строки и столбца
            updateGame(currentPlayer, currntRow, currentCol); // обновление статуса игры
            printBoard();
            if (currentState == USER1_WON) { // Выйграл USER1
                System.out.println("'X' won! Bye!");
            } else if (currentState == USER2_WON) { // Выйграл USER2
                System.out.println("'O' won! Bye!");
            } else if (currentState == DRAW) { // Ничья
                System.out.println("It's a Draw! Bye!");
            }
            // Смена игрока
            currentPlayer = (currentPlayer == USER1) ? USER2 : USER1;
        } while (currentState == PLAYING); // Если игра окончена, повторить игру
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Для консольного приложения

    /**
     * Функция инициализации содержимого игровой доски и текущего состояния
     */
    public static void initGame() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                board[row][col] = EMPTY;  // обозначение, что все ячейки игрового поля изначально пусты
            }
        }
        currentState = PLAYING; // обозначение о начале игры
        currentPlayer = USER1;  // игрок USER1 ходит первым
    }

    /**
     * Функция выполнение хода каждого игрока с проверкой ввода
     * @param theSeed - порядковый номер игрока (USER1 - 1; USER2 - 2)
     */
    public static void playerMove(int theSeed) {
        boolean validInput = false;  // переменная для проверки ввода хода
        do {
            if (theSeed == USER1) { // Если ходит USER1, то ставится "X"
                System.out.println("Player 'X', enter your move (row[1-3] column[1-3]): ");
            } else { // А если ходит USER2, то ставится "0"
                System.out.println("Player '0', enter your move (row[1-3] column[1-3]): ");
            }
            int row = in.nextInt() - 1;
            int col = in.nextInt() - 1;
            // Обновление содержимого игровой доски
            if (row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col] == EMPTY) { // Если ход был совершён верно
                currntRow = row; // записывается номер строки
                currentCol = col; // и номер столбца
                board[currntRow][currentCol] = theSeed; // и ход записывается в массив
                validInput = true;  // Если всё окей, то происходит выход из цикла
            } else { // Если ход был совершён неверный (выход за границы игровой доски), то выводится сообщение об ошибке
                System.out.println("This move at (" + (row + 1) + "," + (col + 1)
                        + ") is not valid. Try again...");
            }
        } while (!validInput);  // цикл повторяется, пока ввод не станет верным
    }

    /**
     * Функция обновиления статуса игры "currentState" после того, как игрок сделал ход.
     * @param theSeed - порядковый номер игрока (USER1 - 1; USER2 - 2)
     * @param currentRow - номер строки хода игрока
     * @param currentCol- номер столбца хода игрока
     */
    public static void updateGame(int theSeed, int currentRow, int currentCol) {
        if (hasWon(theSeed, currentRow, currentCol)) {  // проверка хода, выигрышный ли он?
            currentState = (theSeed == USER1) ? USER1_WON : USER2_WON;
        } else if (isDraw()) {  // проверка на ничью
            currentState = DRAW;
        }
        // В противном случае никаких изменений в статусе игры, т. е. игра продолжается
    }

    /** Функция возвращения истины, если ничья (пустых ячеек нет)
     * @return - true, если ничья и false, если ничьи нет
     */
    public static boolean isDraw() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (board[row][col] == EMPTY) {
                    return false;  // если найдена пустая ячейка, то выход из метода
                }
            }
        }
        return true;
    }

    /**
     * Функция возвращения истины, если игрок CROSS или NOUGHT выиграл
     * @param theSeed - порядковый номер игрока (USER1 - 1; USER2 - 2)
     * @param currentRow - номер строки хода игрока
     * @param currentCol- номер столбца хода игрока
     * @return true, если кто то выйграл и false, если не выйграл никто
     */
    public static boolean hasWon(int theSeed, int currentRow, int currentCol) {

        return (board[currentRow][0] == theSeed         // 3 в ряд
                && board[currentRow][1] == theSeed
                && board[currentRow][2] == theSeed
                || board[0][currentCol] == theSeed      // 3 в столбце
                && board[1][currentCol] == theSeed
                && board[2][currentCol] == theSeed
                || currentRow == currentCol            // 3 по диагонали
                && board[0][0] == theSeed
                && board[1][1] == theSeed
                && board[2][2] == theSeed
                || currentRow + currentCol == 2  // 3 на противоположной диагонали
                && board[0][2] == theSeed
                && board[1][1] == theSeed
                && board[2][0] == theSeed);
    }

    /**
     * Вывод процесса игры (Игровой доски)
     */
    public static void printBoard() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                printCell(board[row][col]); // вывод каждой ячейки
                if (col != COLS - 1) {
                    System.out.print("|");   // вывод вертикальных бортиков
                }
            }
            System.out.println();
            if (row != ROWS - 1) {
                System.out.println("-----------"); // вывод горизонтальных бортиков
            }
        }
        System.out.println();
    }

    /**
     * Вывод ячейки, крестик, нолик или ничего
     * @param content - элемент массива игровой доски, в котором содержится информация об игроке и какой фигурой он играл
     */
    public static void printCell(int content) {
        switch (content) {
            case EMPTY:
            {
                System.out.print("   "); break;
            }
            case USER1:
            {
                System.out.print(" X "); break;
            }
            case USER2:
            {
                System.out.print(" O "); break;
            }
        }
    }

}
