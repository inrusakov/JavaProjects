package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConservatorTest {

    /**
     * Проверка конструктора Консерватора. Проверяется правильность передачи коллекции детей.
     */
    @Test
    void test_getChild() {
        Innovator<Integer> superParent = new Innovator<>(1);
        Conservator<Integer> parent = new Conservator<Integer>(superParent);

        assertEquals(parent.children, parent.getChildren());
    }

    /**
     * Проверка констурктора Консерватора. Проверяется правильность передачи всех детей Инноватора Консерватору.
     */
    @Test
    void test_getAllChildren() {
        Innovator<Integer> inParent = new Innovator<>(1);
        Innovator<Integer> inChild1 = new Innovator<>(2, inParent);
        Innovator<Integer> inChild2 = new Innovator<>(3, inParent);
        Innovator<Integer> inChild3 = new Innovator<>(4, inParent);
        Innovator<Integer> inChild4 = new Innovator<>(5, inParent);
        Innovator<Integer> inChild5 = new Innovator<>(6, inParent);
        Innovator<Integer> inChild6 = new Innovator<>(7, inParent);
        Conservator<Integer> conParent = new Conservator<Integer>(inParent);

        assertEquals(conParent.getAllFamily().size(), inParent.getAllFamily().size());
    }

    /**
     * Проверка конструктора Консерватора. Проверяется правильность передачи значений всех родителей.
     */
    @Test
    void test_compareFamilyValues() {
        Innovator<String> praded = new Innovator<String>("praded");
        Innovator<String> ded = new Innovator<String>("ded", praded);
        Innovator<String> batya = new Innovator<String>("batya", ded);
        Innovator<String> sin = new Innovator<String>("sin", batya);

        Conservator<String> sinConservator = new Conservator<String>(sin);

        assertEquals(sinConservator.getParent().getValue(), "batya");
        assertEquals(sinConservator.getParent().getParent().getValue(), "ded");
        assertEquals(sinConservator.getParent().getParent().getParent().getValue(), "praded");
    }

    /**
     * Проверка метода hasChild().
     */
    @Test
    void test_hasChild() {
        Innovator<Integer> inParent = new Innovator<>(1);
        Innovator<Integer> inChild1 = new Innovator<>(2, inParent);
        Innovator<Integer> inChild2 = new Innovator<>(3, inParent);
        Innovator<Integer> inChild3 = new Innovator<>(4, inParent);
        Innovator<Integer> inChild4 = new Innovator<>(5, inParent);
        Innovator<Integer> inChild5 = new Innovator<>(6, inParent);
        Innovator<Integer> inChild6 = new Innovator<>(7, inParent);
        Innovator<Integer> inChild7 = new Innovator<>(8, inChild6);
        Innovator<Integer> inChild8 = new Innovator<>(9, inChild7);
        Conservator<Integer> conParent = new Conservator<Integer>(inParent);

        assertFalse(conParent.hasChildWithValue(1));
        assertTrue(conParent.hasChildWithValue(2));
        assertTrue(conParent.hasChildWithValue(3));
        assertTrue(conParent.hasChildWithValue(4));
        assertTrue(conParent.hasDescadantWithValue(9));
    }
}