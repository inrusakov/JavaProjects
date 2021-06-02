/**
 * @author <a href="mailto:inrusakov@edu.hse.ru"> Ivan Rusakov</a>
 */

package Data;

/**
 * Класс одного игрока.
 */
public class Player {
    /**
     * Высота и Ширина поля для игры.
     */
    int height, width;
    /**
     * Долг игрока перед банком.
     */
    public double debt;

    /**
     * Конструктор класса игрока.
     * @param balance - Начальное количество денег.
     * @param height - Высота доски для игры.
     * @param width - Ширина доски для игры.
     */
    public Player(double balance, int height, int width)
    {
        this.balance = balance;
        X = 0;
        Y = 0;
        this.height = height;
        this.width = width;
        debt = 0;
    }

    /**
     * Свойство координаты Х.
     */
    private int X;
    public int getX() {return X;}
    public void setX(int X){this.X = X;}

    /**
     * Свойство координаты Y.
     */
    private int Y;
    public int getY() {return Y;}
    public void setY(int Y){this.Y = Y;}

    /**
     * Свойство количества денег у игрока.
     */
    private double balance;
    public double getBalance() {return balance;}
    public void setBalance(double balance){this.balance = balance;}

    /**
     * Проверка банкротства игрока.
     * @return - Банкрот или нет.
     */
    public boolean isAlive()
    {
        if (balance > 0){
            return true;
        }
        return false;
    }

    /**
     * Метод одного хода игрока.
     * @param player - Какой игрок должен ходить.
     * @param count - Количество шагов.
     */
    public void go(Player player, int count)
    {
        while(count > 0) {
            switch (getPlayerPos(player)) {
                case 0:
                    if (count > (width-1 - player.getY())) {
                        count = count - (width - 1 - player.getY());
                        player.setY(width - 1);
                        player.setX(0);
                    }
                    else
                    {
                        player.setY(player.getY() + count);
                        count = 0;
                    }
                    break;
                case 1:
                    if (count > (height-1 - player.getX())) {
                        count = count - (height - 1 - player.getX());
                        player.setX(height - 1);
                        player.setY(width-1);
                    }
                    else
                    {
                        player.setX(player.getX() + count);
                        count = 0;
                    }
                    break;
                case 2:
                    if (count > (player.getY())) {
                        count = count - (player.getY());
                        player.setX(height - 1);
                        player.setY(0);
                    }
                    else
                    {
                        player.setY(player.getY() - count);
                        count = 0;
                    }
                    break;
                case 3:
                    if (count > (player.getX())) {
                        count = count - (player.getX());
                        player.setY(0);
                        player.setX(0);
                    }
                    else
                    {
                        player.setX(player.getX() - count);
                        count = 0;
                    }
                    break;
            }
        }
    }

    /**
     * Вспомогательный метод для определения позиции игрока на доске.
     * @param player - Игрок.
     * @return 0 - Верхний край; 1 - Правый край; 2 - Нижний край доски; 3 - Нижний край; 4 - Левый край; -1 - Ошибка;
     */
    public int getPlayerPos(Player player)
    {
        if (player.getX() == 0 && player.getY()<width-1)
            return 0;
        if (player.getX() < height-1 && player.getY() == width-1)
            return 1;
        if (player.getX() == height-1 && player.getY()>0)
            return 2;
        if (player.getX() > 0 && player.getY() == 0)
            return 3;
        return -1;
    }
}
