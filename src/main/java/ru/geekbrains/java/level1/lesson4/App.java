package ru.geekbrains.java.level1.lesson4;

import java.util.Random;
import java.util.Scanner;

public class App {
    private static final int SIZE = 5; //размер игрового поля
    private static final String[][] map = new String[SIZE][SIZE]; //игровое поле
    private static final String mapPlaceholder = "*";
    private static final String AI = "O"; //значёк для бота
    private static final String NI1 = "X"; //значёк для игрока X
    private static final String NI2 = "Y"; //значёк для игрока Y
    private static final String NI3 = "Z"; //значёк для игрока Z
    private static final String[] PLAYERS = {NI1, NI2, NI3, AI};
    private static final Random rn = new Random();
    private static final Scanner sc = new Scanner(System.in);

    /**
     *Коментарий без смысла
     */
    public static void main(String[] args) {
        do {
            int repeatGame; //запрос на повтор игры
            String winPlayer = null; //для записи выигравшего
            boolean gameNotOver = true; //игра продолжается
            initMap(); //заполнение игрового поля
            do {
                for (String player : PLAYERS) {
                    move(player);
                    if (checkWin()) {
                        winPlayer = player;
                        gameNotOver = false;
                        break;
                    }
                    if (checkMapFilling()) {
                        winPlayer = "Ничья";
                        gameNotOver = false;
                        break;
                    }
                }
            } while (gameNotOver);
            System.out.println("Выграл игрок: " + winPlayer);
            System.out.print("Повторить игру? любое число - да / 0-нет ");
            repeatGame = sc.nextInt();
            if (repeatGame == 0){
                System.out.println("До встречи.");
                break;
            }
        } while (true);
    }

    /**
     * Выбор игры для NI или AI
     */
    private static void move(String player){
        if (player.equals(AI)) {
            aiMove();
        }else {
            niMove(player);
        }
    }

    /**
     * Заполняет игровое поле пласхолдером
     */
    private static void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = mapPlaceholder;
            }
        }
        printMap();
    }

    /**
     * Вывод на печать игрового поля
     */
    private static void printMap() {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1));
        }
        System.out.println("X");
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[j][i]);
            }
            System.out.println();
        }
        System.out.println("Y");
    }

    /**
     * Ход игрока AI старый вариант
     */
    private static void aiMove() {
        do {
            int x = rn.nextInt(SIZE);
            int y = rn.nextInt(SIZE);
            if (map[x][y].equals(mapPlaceholder)) {
                map[x][y] = AI;
                System.out.println("Бот сделал ход X-" + x+ " Y-" + y);
                printMap();
                break;
            }
        } while (true);
    }

    /**
     * Ход игрока NI
     */
    private static void niMove(String player) {
        do {
            System.out.print("Игрок " + player + " Введите значение XY: ");
            int x = sc.nextInt();
            int y = sc.nextInt();
            if (map[x - 1][y - 1].equals(mapPlaceholder)) {
                map[x - 1][y - 1] = player;
                printMap();
                break;
            }
            System.out.println("Клетка занята");
        } while (true);
    }

    /**
     * Проверка на заполненность игрового поля
     */
    private static boolean checkMapFilling() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j].equals(mapPlaceholder)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Проверка на выигрыш
     */
    private static boolean checkWin() {
        return checkRowsWin() || checkColumnWin() || checkDiagWin();
    }

    /**
     * Проверка выигрыша по строкам
     */
    private static boolean checkRowsWin() {
        boolean check = false;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j].equals(mapPlaceholder)) {
                    break;
                }
                if (!map[i][j].equals(map[i][0])) {
                    break;
                }
                if (j == SIZE - 1) {
                    check = true;
                    break;
                }
            }
            if (check) {
                break;
            }
        }
        return check;
    }

    /**
     * Проверка выигрыша по столбцам
     */
    private static boolean checkColumnWin() {
        boolean check = false;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[j][i].equals(mapPlaceholder)) {
                    break;
                }
                if (!map[j][i].equals(map[0][i])) {
                    break;
                }
                if (j == SIZE - 1) {
                    check = true;
                    break;
                }
            }
            if (check) {
                break;
            }
        }
        return check;
    }

    /**
     * Проверка выигрыша по диогоналям
     */
    private static boolean checkDiagWin() {
        boolean check = false;
        for (int i = 0; i < SIZE; i++) {
            if (map[i][i].equals(mapPlaceholder)) {
                break;
            }
            if (!map[i][i].equals(map[0][0])) {
                break;
            }
            if (i == SIZE - 1) {
                check = true;
                break;
            }
        }
        if (check) {
            return true;
        }
        for (int i = SIZE - 1; i >= 0; i--) {
            if (map[i][SIZE - 1 - i].equals(mapPlaceholder)) {
                break;
            }
            if (!map[i][SIZE - 1 - i].equals(map[SIZE - 1][0])) {
                break;
            }
            if (i == 0) {
                check = true;
                break;
            }
        }
        return check;
    }
}
