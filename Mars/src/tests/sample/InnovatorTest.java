package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InnovatorTest {

    /**
     * Проверка метода addChildren(). Проверяется добавление ребенку родителя в качестве ребенка.
     */
    @Test
    void test_addChildren() {
        Innovator<Double> parent1 = new Innovator<>(1.1);
        Innovator<Double> parent2 = new Innovator<>(1.2, parent1);
        Innovator<Double> parent3 = new Innovator<>(1.3, parent2);
        Innovator<Double> parent4 = new Innovator<>(1.4, parent3);

        assertFalse(parent4.addChild(parent1));
    }

    /**
     * Проверка метода changeParents().
     * Проверяется количество детей у 1 родителя после передачи его детей другому родителю
     */
    @Test
    void test_changeParents() {
        Innovator<Double> parent1 = new Innovator<>(1.1);
        Innovator<Double> parent2 = new Innovator<>(1.2);
        Innovator<Double> parent3 = new Innovator<>(1.3, parent1);
        Innovator<Double> parent4 = new Innovator<>(1.4, parent2);

        parent3.changeParent(parent2);

        assertEquals(parent2.children.size(), 2);
        assertEquals(parent1.children.size(), 0);
    }

    /**
     * Проверка метода changeChildren().
     * Проверяется правильность обмена детьми у двух родителей.
     */
    @Test
    void test_changeChildren() {
        Innovator<Double> parent1 = new Innovator<>(1.1);
        Innovator<Double> parent2 = new Innovator<>(1.2);
        Innovator<Double> child1 = new Innovator<>(1.3, parent1);
        Innovator<Double> child2 = new Innovator<>(1.4, parent2);
        Innovator<Double> child3 = new Innovator<>(1.5, parent2);
        Innovator<Double> child4 = new Innovator<>(1.6, parent2);

        parent1.changeChildren(parent2);

        assertEquals(parent2.children.size(), 1);
        assertEquals(parent1.children.size(), 3);
    }

    /**
     * Проверка метода hasChild().
     * Проверяется наличие детей с разными индексами у одного родителя.
     * Проверяется наличие потомков с указанными индексами у одного родителя.
     */
    @Test
    void test_hasChild() {
        Innovator<Double> parent1 = new Innovator<>(1.1);
        Innovator<Double> parent2 = new Innovator<>(1.2, parent1);
        Innovator<Double> child1 = new Innovator<>(1.3, parent2);
        Innovator<Double> child2 = new Innovator<>(1.4, child1);
        Innovator<Double> child3 = new Innovator<>(1.5, child2);
        Innovator<Double> child4 = new Innovator<>(1.6, child3);

        assertTrue(parent1.hasChildWithValue(1.2));
        assertTrue(parent1.hasDescadantWithValue(1.3));
        assertFalse(parent1.hasChildWithValue(1.3));
        assertFalse(parent1.hasDescadantWithValue(1.1));
        assertTrue(parent1.hasDescadantWithValue(1.6));
    }

    /**
     * Проверка метода changeValues().
     * Проверяется правильность смены значений ген-кода у марсиан.
     */
    @Test
    void test_changeValues() {
        Innovator<String> martian1 = new Innovator<String>("Mars");
        Innovator<Double> martian2 = new Innovator<Double>(1.1);
        Innovator<Integer> martian3 = new Innovator<Integer>(1);

        assertEquals(martian1.getValue(), "Mars");
        assertEquals(martian2.getValue(), 1.1);
        assertEquals(martian3.getValue(), 1);

        martian1.setValue("sraM");
        martian2.setValue(2.1);
        martian3.setValue(2);

        assertEquals(martian1.getValue(), "sraM");
        assertEquals(martian2.getValue(), 2.1);
        assertEquals(martian3.getValue(), 2);
    }

    /**
     * Проверка методя changeChildren().
     * Проверяется правильность обмена пустой коллекцией детей с коллекцией, где есть дети.
     */
    @Test
    void test_changeChildren2() {
        Innovator<Double> parent1 = new Innovator<>(1.1);
        Innovator<Double> parent2 = new Innovator<>(1.2);
        Innovator<Double> parent3 = new Innovator<>(1.3, parent1);
        Innovator<Double> parent4 = new Innovator<>(1.4, parent2);

        parent3.changeParent(parent2);

        assertEquals(parent2.children.size(), 2);
        assertEquals(parent1.children.size(), 0);
    }

    /**
     * Проверка метода deleteChildren().
     * Проверяется правильность удаления детей у родителя.
     * Проверяется правильность удаления родитей у детей.
     */
    @Test
    void test_deleteChildren() {
        Innovator<Double> parent1 = new Innovator<>(1.1);
        Innovator<Double> parent2 = new Innovator<>(1.2, parent1);
        Innovator<Double> parent3 = new Innovator<>(1.3, parent2);
        Innovator<Double> parent4 = new Innovator<>(1.4, parent3);

        assertFalse(parent4.deleteChild(parent4, parent1));
        assertTrue(parent1.deleteChild(parent1, parent2));
        assertNull(parent2.getParent());
    }
}