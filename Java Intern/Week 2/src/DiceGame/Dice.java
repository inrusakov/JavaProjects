package DiceGame;

/**
 * Класс одного кубика.
 */
public class Dice {
    /**
     * Метод для броска одного кубика.
     * @return Число очков на выпавшей стороне.
     */
    public static int get(){
        return 1+(int)(Math.random() * 6);
    }
}
