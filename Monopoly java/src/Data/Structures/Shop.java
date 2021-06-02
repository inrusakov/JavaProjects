/**
 * @author <a href="mailto:inrusakov@edu.hse.ru"> Ivan Rusakov</a>
 */

package Data.Structures;

import Data.Player;
import Data.Structures.Abstract.Entity;
import java.util.Random;
import java.util.Scanner;

/**
 * Класс магазина.
 */
public class Shop extends Entity {
    /**
     * Конструктор класса магазина.
     * @param name - Название.
     * @param x - Х координата.
     * @param y - Y координата.
     */
    public Shop(String name, int x, int y) {
        super(name, x, y);
        N = 50 + (int) (Math.random() * 500);
        Random rnd = new Random();
        improvementCoeff = 0.1 + (2 - 0.1) * rnd.nextDouble();
        compensationCoeff = 0.1 + (1 - 0.1) * rnd.nextDouble();
        K = N * 0.5 + (0.9 - 0.5) * rnd.nextDouble();
    }

    /**
     * Владелец магазина.
     */
    public Player owner;
    /**
     * Стоимость магазина.
     */
    public double N;
    /**
     * Компенсация магазина.
     */
    public double K;
    /**
     * Коффициент улучшения.
     */
    public double improvementCoeff;
    /**
     * Коффициент улучшения магазина.
     */
    public double compensationCoeff;

    /**
     * Метод использования магазина.
     * @param player   - Игрок.
     * @param player2  - Компьютер.
     * @param wealth   - Состояние пользователя.
     * @param computer - Индикатор использования клетки компьютером.
     */
    @Override
    public void invoke(Player player, Player player2, double wealth, boolean computer)
    {
        System.out.println("You are at Shop X("+ super.X+ ") Y("+ super.Y+") The price of this shop is: "+N);
        Scanner in = new Scanner(System.in);
        if (owner == null) {
            System.out.println("Do you want to buy this Shop?");
            if (!computer) {
                String answer = "";
                while (!(answer.equals("Y") || answer.equals("N"))) {
                    System.out.println("Y/N?");
                    answer = in.nextLine();
                }
                if (answer.equals("Y")) {
                    if (player.getBalance() >= N) {
                        player.setBalance(player.getBalance() - N);
                        owner = player;
                    } else {
                        System.out.println("You dont have enough money!");
                    }
                }
            }
            if(computer)
            {
                switch ((int)(Math.random()*2)) {
                    case 0: {
                        if (player.getBalance() >= N)
                        {
                            player.setBalance(player.getBalance()-N);
                            owner = player;
                            System.out.println("YES");
                        }
                        else {
                            System.out.println("You dont have enough money!");
                        }
                        break;
                    }
                    case 1:
                    {
                        System.out.println("NO");
                        break;
                    }
                }
            }
        }
        else if(owner == player)
        {
            System.out.println("This is your shop. Do you want to upgrade it? The price is: " + N*improvementCoeff);
            if (!computer) {
                String answer = "";
                while (!(answer.equals("Y") || answer.equals("N"))) {
                    System.out.println("Y/N?");
                    answer = in.nextLine();
                }
                if (answer.equals("Y")) {
                    if (player.getBalance() >= (N * improvementCoeff)) {
                        N = N + improvementCoeff * N;
                        K = K + compensationCoeff * K;
                        player.setBalance(player.getBalance() - N * improvementCoeff);
                    } else {
                        System.out.println("You dont have enough money!");
                    }
                }
            }
            if(computer)
            {
                switch ((int)(Math.random()*2)) {
                    case 0: {
                        if(player.getBalance() >= (N*improvementCoeff))
                        {
                            N =  N + improvementCoeff * N;
                            K =  K + compensationCoeff * K;
                            player.setBalance(player.getBalance() - N*improvementCoeff);
                            System.out.println("YES");
                        }
                        else {
                            System.out.println("You dont have enough money!");
                        }
                        break;
                    }
                    case 1:
                    {
                        System.out.println("NO");
                        break;
                    }
                }
            }
        }
        else if (owner == player2)
        {
            System.out.println("This is your opponent's shop! You have to pay compensation fee");
            System.out.println("The fee is: "+ K);
            player.setBalance(player.getBalance() - K);
            player2.setBalance(player2.getBalance() + K);
        }
    }
}
