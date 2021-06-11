package Utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @Test
    void dataChecker() {
        assertTrue(Utility.dataChecker("test1.txt", "test1.txt"));
        assertFalse(Utility.dataChecker("test1.txt", "test1.txr"));
        assertFalse(Utility.dataChecker("test1.txt", "test12.txt"));
        assertTrue(Utility.dataChecker("hello.xls", "hello|xls"));
    }

    @Test
    void fileChecker() {
        assertTrue(Utility.fileChecker("text1.txt"));
        assertFalse(Utility.fileChecker("text1,txt"));
        assertFalse(Utility.fileChecker("text1|txt"));
        assertTrue(Utility.fileChecker("hello.xls"));
    }
}