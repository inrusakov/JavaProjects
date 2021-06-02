package sample;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    /**
     * Проверка метода getReport() и getFromReport().
     * Проверяется правильность получения генеалогического древа у марсианина из любой ветви семьи.
     * Проверяется правильность извлечения коллеции семьи из генеалогического древа, полученного от другого марсианина.
     */
    @Test
    void test_getReportAndGetFromReport() {
        Innovator<Integer> Kolya = new Innovator<Integer>(1);
        Innovator<Integer> Kolyr = new Innovator<Integer>(2, Kolya);
        Innovator<Integer> Kolyt = new Innovator<Integer>(3, Kolyr);
        Innovator<Integer> Kolyp = new Innovator<Integer>(4, Kolyt);
        Innovator<Integer> Vasya = new Innovator<Integer>(5, Kolyp);
        Innovator<Integer> Vasyn = new Innovator<Integer>(6, Vasya);
        Innovator<Integer> Vasyp = new Innovator<Integer>(7, Vasyn);

        Tree<Integer> tree = new Tree<>(Kolyp);
        ArrayList<String> report = tree.getReport();

        Martian<Integer> temp = tree.getIntegerTreeFromReport(tree.getReport()).get(0);

        ArrayList<String> report2 = tree.getReport();

        assertEquals(report, report2);
    }

    /**
     * Проверка констуктора дерева.
     * Проверяется правильность извлечения всей семьи из разных узлов древа.
     */
    @Test
    void test_familyTreeForAncestorAndDescendant() {
        Innovator<Integer> Kolya = new Innovator<Integer>(1);
        Innovator<Integer> Kolyr = new Innovator<Integer>(2, Kolya);
        Innovator<Integer> Kolyt = new Innovator<Integer>(3, Kolyr);
        Innovator<Integer> Kolyp = new Innovator<Integer>(4, Kolyt);
        Innovator<Integer> Vasya = new Innovator<Integer>(5, Kolyp);
        Innovator<Integer> Vasyn = new Innovator<Integer>(6, Vasya);
        Innovator<Integer> Vasyp = new Innovator<Integer>(7, Vasyn);

        Tree<Integer> tree1 = new Tree<Integer>(Kolya);
        Tree<Integer> tree2 = new Tree<Integer>(Vasyp);

        assertEquals(tree1.family, tree2.family);
    }

    /**
     * Проверка извлечения семьи из древа.
     */
    @Test
    void test_InsertingMemberFamilyFromReport() {
        Innovator<Integer> Kolya = new Innovator<Integer>(1);
        Innovator<Integer> Kolyr = new Innovator<Integer>(2, Kolya);
        Innovator<Integer> Kolyt = new Innovator<Integer>(3, Kolyr);
        Innovator<Integer> Kolyp = new Innovator<Integer>(4, Kolyt);
        Innovator<Integer> Vasya = new Innovator<Integer>(5, Kolyp);
        Innovator<Integer> Vasyn = new Innovator<Integer>(6, Vasya);
        Innovator<Integer> Vasyp = new Innovator<Integer>(7, Vasyn);

        Tree<Integer> tree1 = new Tree<Integer>(Kolya);
        ArrayList<String> report = tree1.getReport();
        Tree<Integer> tree2 = new Tree<Integer>();
        tree2.family = tree2.getIntegerTreeFromReport(report);

        assertEquals(tree1.family.size(), tree2.family.size());
    }

    /**
     * Проверка дерева для консерваторов.
     * Проверяется правильность конструктора Консерватора.
     * Проверяется правильность узлов древа семьи консерватора.
     * Проверяется правильность семьи консерватора после получения отчета.
     */
    @Test
    void test_getReportForConservative() {
        Innovator<Integer> Kolya = new Innovator<Integer>(1);
        Innovator<Integer> Kolyr = new Innovator<Integer>(2, Kolya);
        Innovator<Integer> Kolyt = new Innovator<Integer>(3, Kolyr);
        Innovator<Integer> Kolyp = new Innovator<Integer>(4, Kolyt);
        Innovator<Integer> Vasya = new Innovator<Integer>(5, Kolyp);
        Innovator<Integer> Vasyn = new Innovator<Integer>(6, Vasya);
        Innovator<Integer> Vasyp = new Innovator<Integer>(7, Vasyn);

        Tree<Integer> tree = new Tree<>(Kolyp);
        ArrayList<String> report = tree.getReport();
        Conservator<Integer> newVasyp = new Conservator<Integer>(Vasyp);
        Tree<Integer> tree2 = new Tree<Integer>(newVasyp);
        ArrayList<String> report2 = tree2.getReport();

        assertEquals(report.size(), report2.size());
    }

    /**
     * Проверка создания отчета для консерваторов со значением ген-кода в Integer.
     * Проверяется правильность создания отчета для Консерватора.
     * Проверятеся правильность извлечения семьи из отчета для Консерватора.
     */
    @Test
    void test_getReportAndGetFromReportConservative() {
        Innovator<Integer> Kolya = new Innovator<Integer>(1);
        Innovator<Integer> Kolyr = new Innovator<Integer>(2, Kolya);
        Innovator<Integer> Kolyt = new Innovator<Integer>(3, Kolyr);
        Innovator<Integer> Kolyp = new Innovator<Integer>(4, Kolyt);
        Innovator<Integer> Vasya = new Innovator<Integer>(5, Kolyp);
        Innovator<Integer> Vasyn = new Innovator<Integer>(6, Vasya);
        Innovator<Integer> Vasyp = new Innovator<Integer>(7, Vasyn);
        Conservator<Integer> Kolyan = new Conservator<>(Vasyp);

        Tree<Integer> tree = new Tree<>(Kolyan);
        ArrayList<String> report = tree.getReport();

        Tree<Integer> tree2 = new Tree<Integer>();
        tree2.family = tree2.getIntegerTreeFromReport(report);
        Collections.reverse(tree2.family);
        ArrayList<String> report2 = tree2.getReport();


        assertEquals(report, report2);
    }

    /**
     * Проверка создания отчета для консерваторов со значением ген-кода в виде String.
     * Проверяется правильность создания отчета.
     * Проверятеся правильность извлечения семьи из отчета.
     */
    @Test
    void test_getStringReportAndGetFromReport() {
        Innovator<String> Kolya = new Innovator<String>("a1");
        Innovator<String> Kolyr = new Innovator<String>("a2", Kolya);
        Innovator<String> Kolyt = new Innovator<String>("a3", Kolyr);
        Innovator<String> Kolyp = new Innovator<String>("a4", Kolyt);
        Innovator<String> Vasya = new Innovator<String>("a5", Kolyp);
        Innovator<String> Vasyn = new Innovator<String>("a6", Vasya);
        Innovator<String> Vasyp = new Innovator<String>("a7", Vasyn);

        Tree<String> tree = new Tree<String>(Kolyp);
        ArrayList<String> report = tree.getReport();

        Martian<String> temp = tree.getStringTreeFromReport(tree.getReport()).get(0);

        ArrayList<String> report2 = tree.getReport();

        assertEquals(report, report2);
    }

    /**
     * Проверка создания отчета для консерваторов со значением ген-кода в Double.
     * Проверяется правильность создания отчета.
     * Проверятеся правильность извлечения семьи из отчета.
     */
    @Test
    void test_getDoubleReportAndGetFromReport() {
        Innovator<Double> Kolya = new Innovator<Double>(1.1);
        Innovator<Double> Kolyr = new Innovator<Double>(1.2, Kolya);
        Innovator<Double> Kolyt = new Innovator<Double>(1.3, Kolyr);
        Innovator<Double> Kolyp = new Innovator<Double>(1.4, Kolyt);
        Innovator<Double> Vasya = new Innovator<Double>(1.5, Kolyp);
        Innovator<Double> Vasyn = new Innovator<Double>(1.6, Vasya);
        Innovator<Double> Vasyp = new Innovator<Double>(1.7, Vasyn);

        Tree<Double> tree = new Tree<Double>(Kolyp);
        ArrayList<String> report = tree.getReport();

        Martian<Double> temp = tree.getDoubleTreeFromReport(tree.getReport()).get(0);

        ArrayList<String> report2 = tree.getReport();

        assertEquals(report, report2);
    }


    /**
     * Проверка отчета для большой семьи.
     * Проверяется правильность создания отчета.
     * Проверятеся правильность извлечения семьи из отчета.
     */
    @Test
    void test_getReportForBigFamily() {
        Innovator<Integer> superParent = new Innovator<Integer>(0);
        for (int i = 1; i < 10000; i++) {
            Innovator<Integer> newChild = new Innovator<Integer>(i);
            newChild.changeParent(superParent);
        }

        Innovator<Integer> newChild1 = new Innovator<Integer>(10000, superParent);
        for (int i = 1; i < 10000; i++) {
            Innovator<Integer> newChild = new Innovator<Integer>(i);
            newChild.changeParent(newChild1);
        }

        Innovator<Integer> newChild2 = new Innovator<Integer>(20000, newChild1);
        for (int i = 1; i < 10000; i++) {
            Innovator<Integer> newChild = new Innovator<Integer>(i);
            newChild.changeParent(newChild2);
        }

        Innovator<Integer> newChild3 = new Innovator<Integer>(30000, newChild2);
        for (int i = 1; i < 10000; i++) {
            Innovator<Integer> newChild = new Innovator<Integer>(i);
            newChild.changeParent(newChild3);
        }


        Tree<Integer> tree = new Tree<>(superParent);
        ArrayList<String> report = tree.getReport();

        Martian<Integer> temp = tree.getIntegerTreeFromReport(tree.getReport()).get(0);
        ArrayList<String> report2 = tree.getReport();


        assertEquals(report, report2);
    }
}