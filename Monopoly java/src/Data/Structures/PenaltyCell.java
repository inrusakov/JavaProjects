/**
 * @author <a href="mailto:inrusakov@edu.hse.ru"> Ivan Rusakov</a>
 */

package Data.Structures;

import Data.Player;
import Data.Structures.Abstract.Entity;

/**
 * Класс клетки со штрафом.
 */
public class PenaltyCell extends Entity {

    /**
     * Коэффициент штрафа.
     */
    public double penaltyCoeff = Math.random()*0.1;

    /**
     * Конструктор класса клетки со штрафом.
     * @param name - Название.
     * @param x - Х координата.
     * @param y - Y координата.
     */
    public PenaltyCell(String name, int x, int y) {
        super(name, x, y);
    }

    /**
     * Метод использования клетки со штрафом.
     * @param player   - Игрок.
     * @param player2  - Компьютер.
     * @param wealth   - Состояние пользователя.
     * @param computer - Индикатор использования клетки компьютером.
     */
    @Override
    public void invoke(Player player, Player player2, double wealth, boolean computer)
    {
        System.out.println("You are at Penalty Cell X("+ super.X+ ") Y("+ super.Y+")");
        double balance = player.getBalance();
        player.setBalance(balance - balance*penaltyCoeff);
        System.out.println("You paid "+ balance*penaltyCoeff);
    }
}
