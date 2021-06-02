/**
 * @author <a href="mailto:inrusakov@edu.hse.ru"> Ivan Rusakov</a>
 */

package Data.Structures;

import Data.Player;
import Data.Structures.Abstract.Entity;

/**
 * Класс для пустой клетки по краям доски.
 */
public class EmptyCell extends Entity {

    /**
     * Конструктор Пустой клетки
     * @param name - Название.
     * @param x - Координата Х.
     * @param y - Координата Y.
     */
    public EmptyCell(String name, int x, int y) {
        super(name, x, y);
    }

    /**
     * Метод использования пустой клетки.
     * @param player   - Игрок.
     * @param player2  - Компьютер.
     * @param wealth   - Состояние пользователя.
     * @param computer - Индикатор использования клетки компьютером.
     */
    @Override
    public void invoke(Player player, Player player2, double wealth, boolean computer)
    {
        System.out.println("Just relax there");
    }
}
