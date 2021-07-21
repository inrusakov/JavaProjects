package DiceGame;

public interface Player {

    /**
     * @return Число побед в раундах.
     */
    public int getWins();

    /**
     * Метод для регистрации выигрыша в раунде.
     */
    public void winRound();

    /**
     * Метод для регистрации выпавших очков.
     * @param points Выпавшие очки.
     */
    public void roll(int points);

    /**
     * @return Число выпавших в раунде очков.
     */
    public int getCurrentPoints();

    /**
     * Переход на новый раунд.
     */
    public void clear();
}
