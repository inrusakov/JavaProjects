package DiceGame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    public void test1(){
        Board board = new Board(5,5);
        board.startRound();
    }
}