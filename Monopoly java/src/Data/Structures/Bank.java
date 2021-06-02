/**
 * @author <a href="mailto:inrusakov@edu.hse.ru"> Ivan Rusakov</a>
 */

package Data.Structures;

import Data.Player;
import Data.Structures.Abstract.Entity;
import java.util.Random;
import java.util.Scanner;

/**
 * Класс Банка.
 */
public class Bank extends Entity {

    /**
     * Конструктор Банка
     * @param name - Название.
     * @param x - Координата Х.
     * @param y - Координата Y.
     */
    public Bank(String name, int x, int y) {
        super(name, x, y);
        Random rnd = new Random();
        debtCoeff = 1+(3-1)*rnd.nextDouble();
        creditCoeff = 0.002 + (0.2 - 0.002) * rnd.nextDouble();
    }

    /**
     * Коэффициент долга.
     */
    public double debtCoeff;

    /**
     * Коэффициент кредита.
     */
    public double creditCoeff;

    /**
     * Метод использования банка игроком.
     * @param player - Игрок.
     * @param player2 - Компьютер.
     * @param wealth - Состояние игрока.
     * @param computer - Индикатор компьютера.
     */
    @Override
    public void invoke(Player player, Player player2, double wealth, boolean computer)
    {
        if(!computer)
            {
            if(player.debt > 0){
                System.out.println("You have debt! Which is: "+ player.debt);
                System.out.println("Your credit will be debited from your account!");
                player.setBalance(player.getBalance() - player.debt*debtCoeff);
                player.debt = 0;
            }
            else{
                System.out.println("Would you like to get credit? The max you can get is : "
                        + (int)(creditCoeff * wealth));
                Scanner in = new Scanner(System.in);
                String answer = "";
                while (!(answer.equals("Y") || answer.equals("N"))) {
                    System.out.println("Y/N?");
                    answer = in.nextLine();
                }
                if (answer.equals("Y")) {
                    double credit = -1;
                    while (credit < 0 || credit > creditCoeff * wealth) {
                        System.out.println("Enter summ from: " + 0 + " to: " + (int)(creditCoeff * wealth));
                        try{
                            in = new Scanner(System.in);
                            credit = in.nextDouble();
                        } catch (Exception e){
                            System.out.println("Wrong input try again!");
                    }
                }
                player.setBalance(player.getBalance() + credit);
                player.debt += credit;
                }
            }
        }
    }
}
