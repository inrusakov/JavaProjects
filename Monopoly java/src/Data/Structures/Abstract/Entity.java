/**
 * @author <a href="mailto:inrusakov@edu.hse.ru"> Ivan Rusakov</a>
 */

package Data.Structures.Abstract;

import Data.Player;

/**
 * Абстрактный класс сущности для создания ячеек на поле.
 */
public abstract class Entity {

    /**
     * Название сущности.
     */
    public String name;
    /**
     * X и Y Координаты.
     */
    public int X,Y;

    /**
     * Конструктор абстрактного класса.
     * @param name - Название.
     * @param x - Координата Х.
     * @param y - Координата Y.
     */
    public Entity(String name, int x, int y)
    {
        this.name = name;
        X = x;
        Y = y;
    }

    /**
     * Метод использования клекти игроками.
     * @param player - Игрок.
     * @param player2 - Компьютер.
     * @param wealth - Состояние пользователя.
     * @param computer - Индикатор использования клетки компьютером.
     */
    public void invoke(Player player, Player player2, double wealth, boolean computer)
    {

    }
}
