/**
 * @author <a href="mailto:inrusakov@edu.hse.ru"> Ivan Rusakov</a>
 */

package Data;

import Data.Structures.*;
import Data.Structures.Abstract.Entity;

/**
 * Класс основной доски для игры.
 */
public class Desk {
    /**
     * Длина и ширина поля.
     */
    int height, width;
    /**
     * Массив с клетками.
     */
    public Entity[][]coords;
    /**
     * Пользователь 1.
     */
    public Player player1;
    /**
     * Пользователь 2.
     */
    public Player player2;

    /**
     * Метод вычисления состояния игрока.
     * @param player - Пользователь.
     * @return - Общее состояние игрока.
     */
    public double getWealth(Player player)
    {
        double wealth = 0;
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (coords[i][j] != null) {
                    if (coords[i][j].name.equals("Shop")) {
                        Shop shop = (Shop) coords[i][j];
                        if (shop.owner == player)
                            wealth += shop.N;
                    }
                }
            }
        }
        return wealth;
    }

    /**
     * Метод вывода поля на экран.
     */
    public void printDesk()
    {
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if(coords[i][j] != null){
                    switch (coords[i][j].name){
                        case "Bank":
                        {
                            System.out.print("$ ");
                            break;
                        }
                        case "Empty":
                        {
                            System.out.print("E ");
                            break;
                        }
                        case "Taxi":
                        {
                            System.out.print("T ");
                            break;
                        }
                        case "Penalty":
                        {
                            System.out.print("% ");
                            break;
                        }
                        case "Shop":
                        {
                           Shop shop = (Shop)coords[i][j];
                           if (shop.owner == player1) {
                               System.out.print("M ");
                               break;
                           }
                           if (shop.owner == null) {
                               System.out.print("S ");
                               break;
                           }
                           if (shop.owner == player2) {
                               System.out.print("O ");
                               break;
                           }
                        }
                    }
                }
                else{
                    System.out.print("+ ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Метод заполнения массива пустыми клетками.
     */
    public void fillEmpty()
    {
        coords[0][0] = new EmptyCell("Empty",0,0);
        coords[0][width-1] = new EmptyCell("Empty",0,width-1);
        coords[height-1][0] = new EmptyCell("Empty",height-1,0);
        coords[height-1][width-1] = new EmptyCell("Empty",height-1,width-1);
    }

    /**
     * Метод заполнения массива клетками банка.
     */
    public void fillBank()
    {
        int x = 1 + (int)(Math.random() * (width- 1 - 1));
        coords[0][x] = new Bank("Bank", 0, x);
        x = 1 + (int)(Math.random() * (height - 1 - 1));
        coords[x][0] = new Bank("Bank", x, 0);
        x = 1 + (int)(Math.random() * ((width - 1 - 1)));
        coords[height-1][x] = new Bank("Bank", height-1, x);
        x = 1 + (int)(Math.random() * ((height - 1 - 1)));
        coords[x][width-1] = new Bank("Bank", x, width-1);
    }

    /**
     * Метод заполнения массива клетками такси.
     */
    public void fillTaxi()
    {
        int x;
        int count = (int)(Math.random() * 3);
        for (int i = 0; i < count; i++){
            x = (int) (Math.random() * width);
            if (coords[0][x]== null)
                coords[0][x] = new Taxi("Taxi",0, x);
            else
                i--;
        }
        count = (int)(Math.random() * 3);
        for (int i = 0; i < count; i++){
            x = (int) (Math.random() * height);
            if (coords[x][0]== null)
                coords[x][0] = new Taxi("Taxi", x, 0);
            else
                i--;
        }
        count = (int)(Math.random() * 3);
        for (int i = 0; i < count; i++){
            x = (int) (Math.random() * width);
            if (coords[height-1][x]== null)
                coords[height-1][x] = new Taxi("Taxi", height-1, x);
            else
                i--;
        }
        count = (int)(Math.random() * 3);
        for (int i = 0; i < count; i++){
            x = (int) (Math.random() * height);
            if (coords[x][width-1]== null)
                coords[x][width-1] = new Taxi("Taxi", x, width-1);
            else
                i--;
        }
    }

    /**
     * Метод заполнения массива клетками штрафа.
     */
    public void fillPenalty()
    {
        int x;
        int count = (int)(Math.random() * 3);
        for (int i = 0; i < count; i++){
            x = (int) (Math.random() * width);
            if (coords[0][x]== null)
                coords[0][x] = new PenaltyCell("Penalty",0, x);
            else
                i--;
        }
        count = (int)(Math.random() * 3);
        for (int i = 0; i < count; i++){
            x = (int) (Math.random() * height);
            if (coords[x][0]== null)
                coords[x][0] = new PenaltyCell("Penalty", x, 0);
            else
                i--;
        }
        count = (int)(Math.random() * 3);
        for (int i = 0; i < count; i++){
            x = (int) (Math.random() * width);
            if (coords[height-1][x]== null)
                coords[height-1][x] = new PenaltyCell("Penalty", height-1, x);
            else
                i--;
        }
        count = (int)(Math.random() * 3);
        for (int i = 0; i < count; i++){
            x = (int) (Math.random() * height);
            if (coords[x][width-1]== null)
                coords[x][width-1] = new PenaltyCell("Penalty", x, width-1);
            else
                i--;
        }
    }

    /**
     * Метод заполнения массива клетками магазина.
     */
    public void fillShop()
    {
        for (int i = 0; i < width; i++){
            if (coords[0][i]== null)
                coords[0][i] = new Shop("Shop",0, i);
        }
        for (int i = 0; i < height; i++){
            if (coords[i][0]== null)
                coords[i][0] = new Shop("Shop",i, 0);
        }
        for (int i = 0; i < width; i++){
            if (coords[height-1][i]== null)
                coords[height-1][i] = new Shop("Shop",height-1, i);
        }
        for (int i = 0; i < height; i++){
            if (coords[i][width-1]== null)
                coords[i][width-1] = new Shop("Shop",i, width-1);
        }

    }

    /**
     * Конструктор доски.
     * @param height - Высота.
     * @param width - Ширина.
     * @param money - Начальные деньги игроков.
     */
    public Desk(int height, int width, double money)
    {
        this.height = height;
        this.width = width;
        this.player1 = new Player(money, height, width);
        this.player2 = new Player(money, height, width);
        coords = new Entity[height][width];
        fillEmpty();
        fillBank();
        fillTaxi();
        fillPenalty();
        fillShop();
    }
}

