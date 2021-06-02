package sample;

import java.util.*;

/**
 * @param <T>
 * @author Rusakov Ivan
 */
public class Conservator<T> extends Martian<T> {

    public final T value;
    public final Martian<T> parent;
    public final ArrayList<Martian<T>> children;

    /**
     * @param par Класс Инноватора для создания бессмертного Консерватора.
     */
    Conservator(Innovator<T> par) {
        this.value = par.value;
        if (par.parent != null) {
            if (par.parent.getClass().getSimpleName().equals("Conservator")) {
                this.parent = par.parent;
            } else {
                if (par.parent.getClass().getSimpleName().equals("Innovator")) {
                    this.parent = new Conservator<T>((Innovator<T>) par.parent);
                } else {
                    this.parent = null;
                }
            }
        } else
            this.parent = null;
        if (par.children.size() > 0) {
            this.children = getConservativeChildren(par.children, this);
        } else
            this.children = new ArrayList<>();
    }

    /**
     * @return Getter Свойство для получения колекции детей.
     */
    public List<Martian<T>> getChildren() {
        return this.children;
    }

    /**
     * @return Getter Свойство для получения родителя.
     */
    public Martian<T> getParent() {
        return this.parent;
    }

    /**
     * @return Getter Свойство для получения ген-кода.
     */
    public T getValue() {
        return this.value;
    }

    /**
     * @param childrenT Коллекция детей инноватора для переопределения в Консерваторы.
     * @param parentT   Отец детей для переопределения.
     * @return Коллекция детей Консерваторов.
     */
    public ArrayList<Martian<T>> getConservativeChildren(ArrayList<Martian<T>> childrenT, Martian<T> parentT) {
        ArrayList<Martian<T>> temp = new ArrayList<>();
        for (int i = 0; i < childrenT.size(); i++) {
            Martian<T> tempMartian = childrenT.get(i);
            tempMartian.parent = parentT;
            temp.add(new Conservator<T>((Innovator<T>) childrenT.get(i)));
        }
        return temp;
    }

    /**
     * @return Вся семья данного марсианина.
     */
    public List<Martian<T>> getAllFamily() {
        List<Martian<T>> conservatorList = new ArrayList<>();
        if (getChildren().size() > 0) {
            for (Martian<T> child : children) {
                conservatorList.addAll(child.getAllFamily());
            }
        }
        conservatorList.add(this);
        return conservatorList;
    }

    /**
     * @param value Ген-код для поиска среди детей.
     * @return Если ли ребенок с указаным ген-кодом.
     */
    public boolean hasChildWithValue(T value) {
        for (Martian<T> child : getChildren()) {
            if (child.getValue() == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param value Ген-код для поиска среди потомков.
     * @return Если ли потомок с указаным ген-кодом.
     */
    public boolean hasDescadantWithValue(T value) {
        List<Martian<T>> conservatorList = new ArrayList<>();
        conservatorList = getAllFamily();
        for (Martian<T> tConservator : conservatorList) {
            if (tConservator.getValue() == value)
                return true;
        }
        return false;
    }

    /**
     * @return Переопределенный метод toString.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "Martian (" + value.getClass().getSimpleName() + ":" + value + ")";
    }
}
