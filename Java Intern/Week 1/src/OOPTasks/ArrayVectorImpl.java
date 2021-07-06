package OOPTasks;
//package ru.skillbench.tasks.basics.math;

public class ArrayVectorImpl implements ArrayVector{

    /**
     * Хранящий все данные о векторе массив.
     */
    private double[] array;

    /**
     * @param array Конструктор с параметром массивом.
     */
    public ArrayVectorImpl(double[] array) {
        this.array = array;
    }

    /**
     * Пустой конструктор.
     */
    public ArrayVectorImpl() {
        this.array = new double[0];
    }

    /**
     * Возвращает все элементы вектора. Массив не клонируется.
     * @param elements Не равен null
     */
    @Override
    public void set(double... elements) {
        array = new double[elements.length];
        array = elements;
    }

    /**
     * Возвращает все элементы вектора. Массив не клонируется.
     * @return Все элементы вектора.
     */
    @Override
    public double[] get() {
        return array;
    }

    /**
     * Возвращает копию вектора (такую, изменение элементов в которой не приводит к изменению элементов данного вектора).
     * @return Копия вектора.
     */
    @Override
    public ArrayVector clone() {
        double copy[] = new double[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);
        ArrayVector ret = new ArrayVectorImpl();
        ret.set(copy);
        return ret;
    }

    /**
     * @return Число элементов вектора.
     */
    @Override
    public int getSize() {
        return array.length;
    }

    /**
     * Изменяет элемент по индексу.
     * @param index В случае выхода индекса за пределы массива:<br/>
     *              а) если index<0, ничего не происходит;<br/>
     * @param value Значение, на которое надо изменить.
     */
    @Override
    public void set(int index, double value) {
        if (index >= 0 && index < array.length)
            array[index] = value;
    }

    /**
     * Возвращает элемент по индексу.
     * @param index В случае выхода индекса за пределы массива
     * @return Элемент по индексу.
     * @throws ArrayIndexOutOfBoundsException
     */
    @Override
    public double get(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= 0 && index < array.length)
            return array[index];
        else throw new ArrayIndexOutOfBoundsException("Index out of bounds!");
    }

    /**
     * Возвращает максимальный элемент вектора.
     * @return Максимальный элемент вектора
     */
    @Override
    public double getMax() {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < array.length; i++){
            if (array[i] > max)
                max = array[i];
        }
        return max;
    }

    /**
     * Возвращает минимальный элемент вектора.
     * @return Минимальный элемент вектора
     */
    @Override
    public double getMin() {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < array.length; i++){
            if (array[i] < min)
                min = array[i];
        }
        return min;
    }

    /**
     * Сортирует элементы вектора в порядке возрастания. В методе sort используется quick sort.
     */
    @Override
    public void sortAscending() {
        int low = 0;
        int high = array.length - 1;

        quickSort(array, low, high);
    }

    /**
     * Метод сортировки, использующий способ quick sort.
     * @param array Массив
     * @param low Нижняя граница.
     * @param high Верхняя граница.
     */
    public static void quickSort(double[] array, int low, int high) {
        if (array.length == 0)
            return;
        if (low >= high)
            return;
        int middle = low + (high - low) / 2;
        double opora = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }

            while (array[j] > opora) {
                j--;
            }

            if (i <= j) {
                double temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }

    /**
     * Умножает вектор на число.
     * @param factor Число, на которое надо умножать.
     */
    @Override
    public void mult(double factor) {
        for (int i = 0; i < array.length; i++){
            array[i]*= factor;
        }
    }

    /**
     * Складывает вектор с другим вектором, результат запоминает в элементах данного вектора.
     * @param anotherVector Не равен null
     * @return
     */
    @Override
    public ArrayVector sum(ArrayVector anotherVector) {
        for (int i = 0; i < anotherVector.getSize(); i++){
            array[i]+= anotherVector.get(i);
        }
        return this;
    }

    /**
     * Возвращает скалярное произведение двух векторов.
     * @param anotherVector Не равен null
     * @return Скалярное произведение двух векторов
     */
    @Override
    public double scalarMult(ArrayVector anotherVector) {
        double temp = 0;
        for (int i = 0; i < anotherVector.getSize(); i++){
            temp += array[i]*anotherVector.get(i);
        }
        return temp;
    }

    /**
     * Возвращает евклидову норму вектора (длину вектора
     * в n-мерном евклидовом пространстве
     * @return Евклидову норму вектора.
     */
    @Override
    public double getNorm() {
        return Math.sqrt(scalarMult(this));
    }
}
