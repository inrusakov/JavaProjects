package WordAnalytics;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class WordAnalyticsTest {

    @Test
    public void intersection_test(){
        WordAnalytics wordAnalytics = new WordAnalytics("babase", "kaseta");

        ArrayList<Character> expected = new ArrayList<>();
        expected.add('a');
        expected.add('e');
        expected.add('s');

        ArrayList<Character> expectedR = new ArrayList<>();
        expectedR.add('s');
        expectedR.add('e');
        expectedR.add('a');

        assertEquals(expected, wordAnalytics.intersection(FORMAT.NORMAL, 0));
        assertEquals(expectedR, wordAnalytics.intersection(FORMAT.REVERSE, 0));
    }

    @Test
    public void onlyFirst_test(){
        WordAnalytics wordAnalytics = new WordAnalytics("qwerty", "yuiop");

        ArrayList<Character> expected = new ArrayList<>();
        expected.add('e');
        expected.add('q');
        expected.add('r');
        expected.add('t');
        expected.add('w');

        ArrayList<Character> expectedR = new ArrayList<>();
        expectedR.add('w');
        expectedR.add('t');
        expectedR.add('r');
        expectedR.add('q');
        expectedR.add('e');

        ArrayList<Character> hashExp = Arrays.stream(new Character[]{'Ê', 'â', 'ä','è', 'î'})
                .collect(Collectors.toCollection(ArrayList::new));


        assertEquals(expected, wordAnalytics.onlyFirstString(FORMAT.NORMAL, 0));
        assertEquals(expectedR, wordAnalytics.onlyFirstString(FORMAT.REVERSE, 0));
        assertEquals(expected, wordAnalytics.onlyFirstString(FORMAT.SHIFT, 0));
        assertEquals(hashExp, wordAnalytics.onlyFirstString(FORMAT.SHIFT, 1));
    }

    @Test
    public void union_test(){
        WordAnalytics wordAnalytics = new WordAnalytics("qwerty", "yuiop");

        ArrayList<Character> expected = new ArrayList<>();
        expected.add('e');
        expected.add('i');
        expected.add('o');
        expected.add('p');
        expected.add('q');
        expected.add('r');
        expected.add('t');
        expected.add('u');
        expected.add('w');
        expected.add('y');

        ArrayList<Character> expectedR = new ArrayList<>();
        expectedR.add('y');
        expectedR.add('w');
        expectedR.add('u');
        expectedR.add('t');
        expectedR.add('r');
        expectedR.add('q');
        expectedR.add('p');
        expectedR.add('o');
        expectedR.add('i');
        expectedR.add('e');

        ArrayList<Character> hashExp = Arrays.stream(new Character[]{'Ɣ','Ƥ', 'Ƽ', 'ǀ', 'Ǆ', 'ǈ', 'ǐ', 'ǔ', 'ǜ','Ǥ'})
                .collect(Collectors.toCollection(ArrayList::new));

        assertEquals(expected, wordAnalytics.unionIntersection(FORMAT.NORMAL, 0));
        assertEquals(expectedR, wordAnalytics.unionIntersection(FORMAT.REVERSE, 0));
        assertEquals(expected, wordAnalytics.unionIntersection(FORMAT.SHIFT, 0));
        assertEquals(hashExp, wordAnalytics.unionIntersection(FORMAT.SHIFT, 2));
    }
}