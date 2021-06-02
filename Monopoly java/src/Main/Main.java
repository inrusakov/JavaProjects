/**
 * @author <a href="mailto:inrusakov@edu.hse.ru"> Ivan Rusakov</a>
 */

package Main;

import Data.*;

/**
 * Класс запуска игры.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Monopoly!");
        if (args.length == 3) {
            int height = Integer.parseInt(args[0]);
            int width = Integer.parseInt(args[1]);
            int money = Integer.parseInt(args[2]);
            if(height <= 30 && height >= 6 && width <= 30 && width >= 6 && money <= 15000 && money >= 500){
                Game game = new Game(height,width,money);
                game.start();
            }
            else{
                System.out.println("Wrong params!");
            }
            System.out.println("Thanks for playing Monopoly!");
        }
        else {
            System.out.println("Wrong params!");
            System.out.println("Thanks for playing Monopoly!");
        }
    }
}
