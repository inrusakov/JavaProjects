/**
 * @author <a href="mailto:inrusakov@edu.hse.ru"> Ivan Rusakov</a>
 */

package Data.Structures;

import Data.Player;
import Data.Structures.Abstract.Entity;

/**
 * Класс клетки Такси.
 */
public class Taxi extends Entity {
    /**
     * Конструктор класса Такси.
     * @param name - Название.
     * @param x - Х координата.
     * @param y - Y координата.
     */
    public Taxi(String name, int x, int y) {
        super(name, x, y);
        taxiDistance = 3+ (int) (Math.random() * (5 - 3));
    }

    /**
     * Количество клеток на которое такси перемещает пользователя.
     */
    public int taxiDistance;

    /**
     * Метод использования клекти Такси.
     * @param player   - Игрок.
     * @param player2  - Компьютер.
     * @param wealth   - Состояние пользователя.
     * @param computer - Индикатор использования клетки компьютером.
     */
    @Override
    public void invoke(Player player, Player player2, double wealth, boolean computer)
    {
        System.out.println("You are at Taxi X("+ super.X+ ") Y("+ super.Y+")");
        System.out.println("You are shifted forward by "+ taxiDistance +" cells");
        player.go(player,taxiDistance);
        System.out.println("Your new position is X("+ player.getX()+ ") Y("+ player.getY()+")");
    }
}
