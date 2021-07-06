package DiceGame;

/**
 * Класс игрока.
 */
public class Player {
    private int wins;
    private int currentPoints;

    /**
     * Конструктор.
     */
    public Player() {
        this.wins = 0;
    }

    /**
     * @return Число побед в раундах.
     */
    public int getWins(){
        return wins;
    }

    /**
     * Метод для регистрации выигрыша в раунде.
     */
    public void winRound(){
        wins++;
    }

    /**
     * Метод для регистрации выпавших очков.
     * @param points Выпавшие очки.
     */
    public void roll(int points){
        currentPoints += points;
    }

    /**
     * @return Число выпавших в раунде очков.
     */
    public int getCurrentPoints(){
        return currentPoints;
    }

    /**
     * Переход на новый раунд.
     */
    public void clear(){
        currentPoints = 0;
    }
}
