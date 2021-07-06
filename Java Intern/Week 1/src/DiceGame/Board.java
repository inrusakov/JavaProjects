package DiceGame;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Класс для игры в кости.
 */
public class Board {
    private Dice[] dice;

    private Player[] players;

    Queue<Player> playerQueue = new LinkedList<>();

    /** Конструктор.
     * @param N Количество игроков.
     * @param K Количество кубиков.
     */
    public Board(int N, int K) {
        if (N > 0) {
            players = new Player[N];
            for (int i = 0; i < N; i++){
                players[i] = new Player();
            }
        }
        if (K > 0) {
            dice = new Dice[K];
            for (int i = 0; i < K; i++){
                dice[i] = new Dice();
            }
        }
    }

    /**
     * Проверяется, выиграл ли какой-то из игроков.
     * @return true если есть победитель, false если нет.
     */
    public boolean checkWin(){
        for (int i = 0; i < players.length; i++){
            if (players[i].getWins() >= 7) {
                System.out.println("Player "+ i + " won the game!");
                printScoreboard();
                return true;
            }
        }
        return false;
    }

    /**
     * Метод для вывода таблица счета по победам в раундах.
     */
    public void printScoreboard(){
        for (int i = 0; i < players.length; i++){
            System.out.println("Player "+i+" got "+players[i].getWins()+"points.");
        }
    }

    /**
     * Метод для поиска победителя в игре.
     * @return Номер победителя в массиве игроков.
     */
    public int findWinner(){
        int max = 0;
        int current = 0;
        for (int i = 0; i < players.length; i++){
            if (players[i].getCurrentPoints() > max) {
                current = i;
                max = players[i].getCurrentPoints();
            }
        }
        for (int i = 0; i < players.length; i++){
            if (players[i].getCurrentPoints() == max && i != current)
                return -1;
        }
        return current;
    }

    /**
     * Метод для начала раунда.
     */
    public void playRound(){
        for (int i = 0; i < players.length; i++){
            if (!playerQueue.contains(players[i]))
                playerQueue.add(players[i]);
        }
        for (int i = 0; i < players.length; i++){
            Player player = playerQueue.poll();
            for (int j = 0; j < dice.length; j++){
                if (player != null) {
                    player.roll(Dice.get());
                }
            }
        }
        int result = findWinner();
        if (result != -1) {
            playerQueue.add(players[result]);
            players[result].winRound();
        }

        for (int i = 0; i < players.length; i++){
            players[i].clear();
        }
    }

    /**
     * Метод для начала игры.
     */
    public void startRound(){
        while(!checkWin()){
            playRound();
        }
    }
}
