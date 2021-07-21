package DiceGame;

public class Computer implements Player{
    private int wins;
    private int currentPoints;

    public Computer() {
        this.wins = 0;
        this.currentPoints = 0;
    }

    @Override
    public int getWins() {
        return wins;
    }

    @Override
    public void winRound() {
        wins++;
    }


    @Override
    public void roll(int points) {
        currentPoints += points;
    }

    @Override
    public int getCurrentPoints() {
        return currentPoints;
    }

    @Override
    public void clear() {
        currentPoints = 0;
    }
}
