/**
 * @author <a href="mailto:inrusakov@edu.hse.ru"> Ivan Rusakov</a>
 */

package Data;

/**
 * Класс кубика для получения количества ходов игрока.
 */
public class Dice {
    /**
     * Метод для получения количества ходов игрока
     * @return Число на 1 кубике.
     */
    public int getDice()
    {
        return 1+(int)(Math.random() * 6);
    }
}
