package com.game.tic_tac_toe.servlet;

import java.util.Arrays;

/**
 *  Класс имеет ряд методов для работы с данными игроков в ходе игры и предназначен для реализации полного функционала во время игры
 *  Все переменные и методы объявлены как статические (принадлежат классу)
 */
public class Players {

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
    //  двух мерном массив игроков (EMPTY, USER1, USER2)
    public static int[][] pboard = null; // игровая доска в двух мерном массиве

    public static int currntRow, currentCol; // текущие столбец и строка

    // Имя игрока
    public String player;

    // Критерии проверки хода на выйгрышность
    public static final int ROW_CHECKING = 1;
    public static final int COLUMN_CHECKING = 2;
    public static final int DIAGONAL_CHECKING = 3;
    public static final int OPPOSITE_DIAGONAL_CHECKING = 4;

    /**
     * Функция для создания массива игровой доски
     *
     * @param tableSize - значение матрицы клеток для игры
     */
    public void createArray(int tableSize){
        if(null==pboard){
            pboard = new int[tableSize][tableSize];
            System.out.print("\nArray size : " + pboard.length);
        }

    }

    /** Функция возвращения истины, если ничья (пустых ячеек нет)
     *
     * @param tableSize - значение матрицы клеток для игры
     * @return - true, если ничья и false, если ничьи нет
     */
    public static boolean drawChecking(int tableSize) {
        for (int row = 0; row < tableSize; ++row) {
            for (int col = 0; col < tableSize; ++col) {
                if(null!=pboard){
                    if (pboard[row][col] == EMPTY) {
                        return false; // если найдена пустая ячейка, то выход из метода
                    }
                }
            }
        }
        return true;
    }

    /**
     * Функция выполнение хода каждого игрока
     *
     * @param theSeed - порядковый номер игрока (USER1 - 1; USER2 - 2)
     * @param row - номер строки хода
     * @param col - номер столбца хода
     * @param tableSize - значение матрицы клеток для игры
     */
    public void movePlayer(int theSeed, int row, int col, int tableSize) {

        if (theSeed == USER1) { // Если ходит USER1, то ставится "X"
            System.out.print("\nPlayer 'X', enter your move (row[1-3] " + row + " column[1-3]): " + col);
        } else { // А если ходит USER2, то ставится "0"
            System.out.print("\nPlayer 'O', enter your move (row[1-3] " + row + " column[1-3]): " + col);
        }
        // Обновление содержимого игровой доски
        int row_ = row - 1;
        int col_ = col - 1;
        if (row_ >= 0 && row_ < tableSize && col_ >= 0 && col_ < tableSize && pboard[row_][col_] == EMPTY) {   // Если ход был совершён верно
            currntRow = row_;
            currentCol = col_;
            pboard[currntRow][currentCol] = theSeed;
        } else { // Если ход был совершён неверный (выход за границы игровой доски), то выводится сообщение об ошибке
            System.out.println("\nThis move at (" + (row_ + 1) + "," + (col_ + 1)
                    + ") is not valid. Try again...");
        }

    }

    /**
     * Функция обновления статуса игры после того, как игрок сделал ход.
     * @param theSeed - порядковый номер игрока (USER1 - 1; USER2 - 2)
     * @param currentRow - номер строки хода игрока
     * @param currentCol- номер столбца хода игрока
     * @param tableSize - значение матрицы клеток для игры
     * @return статус игры (PLAYING по умолчанию)
     */
    public int gameStatus(int theSeed, int currentRow, int currentCol, int tableSize) {
        int status = PLAYING; // Начальный статус игры (игра идёт)
        if (isWon(theSeed, currentRow, currentCol, tableSize)) { // проверка хода, выигрышный ли он?
            if (theSeed == USER1) {
                status = USER1_WON;
            }
            else {
                status = USER2_WON;
            }
        } else if (drawChecking(tableSize)) { // проверка на ничью
            status = DRAW;
        }

        return status; // вывод обновлённого статуса
    }

    /**
     * Функция проверки хода на выйгрышь игрока
     * @param theSeed - порядковый номер игрока (USER1 - 1; USER2 - 2)
     * @param currentRow - номер строки хода игрока
     * @param currentCol- номер столбца хода игрока
     * @param tableSize - значение матрицы клеток для игры
     * @return true, если кто то выйграл и false, если не выйграл никто
     */
    public  boolean isWon(int theSeed, int currentRow, int currentCol, int tableSize) {
        boolean check1 = false;
        boolean check2 = false;
        boolean check3 = false;
        boolean check4 = false;

        check1 = wonChecking(theSeed, currentRow, currentCol, tableSize, ROW_CHECKING);
        check2 = wonChecking(theSeed, currentRow, currentCol, tableSize, COLUMN_CHECKING);
        check3 = wonChecking(theSeed, currentRow, currentCol, tableSize, DIAGONAL_CHECKING);
        check4 = wonChecking(theSeed, currentRow, currentCol, tableSize, OPPOSITE_DIAGONAL_CHECKING);

        return (check1 || check2 || check3 || check4);

    }

    /**
     * Проверка хода на выйгрышь по 4-м критериям (3 в ряд, 3 в столбце, 3 по диагонали, 3 на противоположной диагонали)
     * @param theSeed - порядковый номер игрока (USER1 - 1; USER2 - 2)
     * @param currentRow - номер строки хода игрока
     * @param currentCol- номер столбца хода игрока
     * @param tableSize - значение матрицы клеток для игры
     * @param mode - критерий проверки (3 в ряд, 3 в столбце, 3 по диагонали, 3 на противоположной диагонали)
     * @return true, если кто то выйграл и false, если не выйграл никто
     */
    public boolean wonChecking(int theSeed, int currentRow, int currentCol, int tableSize, int mode){
        boolean result = false;
        System.out.print("\nmode : " + mode);
        System.out.println("\n"+Arrays.deepToString(pboard));
        switch(mode){
            case 1 :
                for (int i = 0; i < tableSize; ++i) {
                    if(pboard[currentRow][i] == theSeed){
                        result = true;
                    }else{
                        result = false;
                        break;
                    }
                    System.out.println("\nmode : " + mode + " result : " + result);
                }
                break;
            case 2 :
                for (int i = 0; i < tableSize; ++i) {
                    if(pboard[i][currentCol] == theSeed){
                        result = true;
                    }else{
                        result = false;
                        break;
                    }
                    System.out.println("\nmode : " + mode + " result : " + result);
                }
                break;
            case 3 :
                for (int i = 0; i < tableSize; ++i) {
                    if(pboard[i][i] == theSeed){
                        result = true;
                    }else{
                        result = false;
                        break;
                    }
                    System.out.println("\nmode : " + mode + " result : " + result);
                }
                break;
            case 4 :
                int temp = tableSize - 1;
                for (int i = 0; i < tableSize; ++i) {
                    if(pboard[i][temp] == theSeed){
                        result = true;
                    }else{
                        result = false;
                        break;
                    }
                    temp--;
                    System.out.println("\nmode : " + mode + " result : " + result);
                }
                break;
        }

        System.out.println("final result : " + result);

        return result;

    }


    public int[][] getPboard() {
        return pboard;
    }

    public void setPboard(int[][] pboard) {
        this.pboard = pboard;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

}
