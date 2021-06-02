package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Абстрактный класс марсианина.
 *
 * @param <T> Ген-код
 */
public abstract class Martian<T> {

    public T value;
    public Martian<T> parent;
    public ArrayList<Martian<T>> children = new ArrayList<Martian<T>>();

    /**
     * @return Getter свойство для получения родителя.
     */
    public Martian<T> getParent() {
        return parent;
    }

    /**
     * @return Getter свойство для получения коллекции детей.
     */
    public List<Martian<T>> getChildren() {
        return children;
    }

    /**
     * @return Getter свойство для получения ген-кода.
     */
    public T getValue() {
        return value;
    }

    /**
     * @return Коллекция всех потомков.
     */
    public List<Martian<T>> getAllFamily() {
        List<Martian<T>> innovatorList = new ArrayList<>();
        if (getChildren().size() > 0) {
            for (Martian<T> child : children) {
                innovatorList.addAll(child.getAllFamily());
            }
        }
        innovatorList.add(this);
        return innovatorList;
    }
}

