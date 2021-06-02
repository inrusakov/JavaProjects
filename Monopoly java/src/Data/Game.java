/**
 * @author <a href="mailto:inrusakov@edu.hse.ru"> Ivan Rusakov</a>
 */

package Data;

import java.util.Scanner;

/**
 * Класс игры.
 */
public class Game {
    /**
     * Поле с доской для игры.
     */
    public Desk desk;
    /**
     * Поле для игрального кубика.
     */
    public Dice dice;
    /**
     * Поле для определения очередности хода.
     */
    int turn;

    /**
     * Конструктор игры.
     * @param height - Высота.
     * @param width - Ширина.
     * @param money - Начальные деньги игроков.
     */
    public Game(int height, int width, int money)
    {
        desk = new Desk(height,width,money);
        dice = new Dice();
        if (dice.getDice() <= 3)
            turn = 1;
        else    
            turn = 2;
    }

    /**
     * Метод запуска игры.
     */
    public void start()
    {
        while(desk.player1.isAlive() && desk.player2.isAlive()){
            Scanner scanner = new Scanner(System.in);
            switch (turn) {
                case 1: {
                    // Вычисление шагов, которыес делает игрок.
                    int step = dice.getDice()+ dice.getDice();

                    // Выводится состояние доски на начало хода игрока.
                    desk.printDesk();
                    System.out.println("Player 1 turn! Position: X("
                            + desk.player1.getX() +
                            "), Y(" + desk.player1.getY()+") Your balance: "+ desk.player1.getBalance());
                    System.out.println("You got " + step + " on your dice");

                    // Игрок делает шаг.
                    desk.player1.go(desk.player1, step);
                    System.out.println("Your new position: X("
                            + desk.player1.getX() +
                            "), Y(" + desk.player1.getY()+")");

                    // Выполняется действие на ячейке, на которую пришел игрок.
                    desk.coords[desk.player1.getX()][desk.player1.getY()].
                            invoke(desk.player1, desk.player2, desk.getWealth(desk.player1), false);

                    // Если новая клетка такси, она снова задействуется.
                    while (desk.coords[desk.player1.getX()][desk.player1.getY()].name.equals("Taxi"))
                        desk.coords[desk.player1.getX()][desk.player1.getY()].
                                invoke(desk.player1, desk.player2, desk.getWealth(desk.player1), false);

                    // Выводится состояние доски на конец хода игрока.
                    desk.printDesk();
                    turn = 2;
                    System.out.println("Press Enter to make next move");
                    scanner.nextLine();
                    break;
                }
                case 2: {
                    // Вычисление шагов, которыес делает игрок.
                    int step = dice.getDice()+ dice.getDice();

                    // Выводится состояние доски на начало хода игрока.
                    desk.printDesk();
                    System.out.println("Player 2 turn! Position: X("
                            + desk.player2.getX() +
                            "), Y(" + desk.player2.getY()+") Your balance: "+ desk.player2.getBalance());
                    System.out.println("You got " + step + " on your dice");

                    // Игрок делает шаг.
                    desk.player2.go(desk.player2, step);
                    System.out.println("Your new position: X("
                            + desk.player2.getX() +
                            "), Y(" + desk.player2.getY()+")");

                    // Выполняется действие на ячейке, на которую пришел игрок.
                    desk.coords[desk.player2.getX()][desk.player2.getY()].
                            invoke(desk.player2, desk.player1, desk.getWealth(desk.player2), true);

                    // Если новая клетка такси, она снова задействуется.
                    while (desk.coords[desk.player2.getX()][desk.player2.getY()].name.equals("Taxi"))
                        desk.coords[desk.player2.getX()][desk.player2.getY()].
                                invoke(desk.player2, desk.player1, desk.getWealth(desk.player2), false);

                    // Выводится состояние доски на конец хода игрока.
                    desk.printDesk();
                    turn = 1;
                    System.out.println("Press Enter to make next move");
                    scanner.nextLine();
                    break;
                }
            }
        }
        if(desk.player1.isAlive()){
            System.out.println("Player 1 wins!");
        }
        else{
            System.out.println("Player 2 wins!");
        }
    }
}
