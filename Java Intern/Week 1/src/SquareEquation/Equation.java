package SquareEquation;

/**
 * Структура данного класса.
 * Главным классов является публичный класс одного уравнения
 * с вложеным статическим классов для поиска дискримината в уравнении.
 * Статических класс может использоваться для вычисления дискриминанта и для других уравнений.
 */

/**
 * Класс для решения квадратных уравнений.
 */
public class Equation {
    /**
     * Внутренний класс для расчета дискриминанта.
     */
    public static class Discriminant {
        /**
         * Метод расчета дискриминанта.
         * @param a Коэф а
         * @param b Коэф b
         * @param c Коэф c
         * @return Значение дискриминанта.
         */
        public static double getDiscriminant(int a, int b, int c) {
            return b * b - 4 * a * c;
        }
    }
    private int a, b, c;
    private double discriminant;

    /** Конструктор с вычислением дискриминанта.
     * @param a
     * @param b
     * @param c
     */
    public Equation(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        discriminant = Discriminant.getDiscriminant(a, b, c);
    }

    /** Методя для решение уравнения.
     * @return Значения X и Y.
     */
    public double[] getResults() {
        if (discriminant < 0)
            return new double[0];
        else if (discriminant == 0)
            return new double[]{-b / (2 * a)};
        else
            return new double[]{(b * (-1) + Math.sqrt(discriminant)) / (2 * a),
                    (b * (-1) - Math.sqrt(discriminant)) / (2 * a)};
    }
}
