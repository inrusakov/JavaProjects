package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T> Ген-код
 * @author Rusakov Ivan
 */
public class Innovator<T> extends Martian<T> {

    /**
     * Для рождения ребенка от уже существующего Инноватора.
     *
     * @param id  Ген-код нового Инноватора.
     * @param par Родитель данного Инноватора.
     */
    Innovator(T id, Innovator<T> par) {
        super.value = id;
        super.parent = par;
        if (par != null)
            par.addChild(this);
    }

    /**
     * @param id Ген-код для марсианина без родителя.
     */
    Innovator(T id) {
        super.value = id;
        super.parent = null;
    }

    /**
     * @param val Новое значение ген-кода.
     */
    public void setValue(T val) {
        super.value = val;
    }

    /**
     * Для того, чтобы добавить ребенка без родителя данному родителю вызывать этот метод.
     *
     * @param newParent Новый родитель данного марсианина.
     */
    public void changeParent(Innovator<T> newParent) {
        if (super.parent != newParent) {
            if (super.parent != null)
                deleteChild(super.parent, this);

            if (newParent != null) {
                super.parent = newParent;
                newParent.addChild(this);
            }
        }
    }

    /**
     * Не вызвать этот метод если у ребенка нет родителя.
     *
     * @param child Новый ребенок.
     * @return Получилось ли добавить нового ребенка.
     */
    public boolean addChild(Innovator<T> child) {
        try {
            if (child == this) throw new IllegalArgumentException("This Martian can't be it's own parent!");
            if (child == null) throw new IllegalArgumentException("This doesn't exist!");
            Martian<T> temp = this;
            while (temp.parent != null) {
                temp = temp.parent;
                if (temp == child) throw new IllegalArgumentException("This Martian can't be its own ancestor!");
            }
            children.add(child);
            child.changeParent(this);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * @param par   Удалить ребенка у данного родителя.
     * @param child Ребенок для удаления.
     * @return Получилось ли удалить данного ребенка у данного родителя.
     */
    public boolean deleteChild(Martian<T> par, Martian<T> child) {
        try {
            if (!par.children.contains(child))
                throw new IllegalArgumentException("This Martian doesn't has this child!");
            if (child == null) throw new IllegalArgumentException("This doesn't exist!");
            par.children.remove(child);
            child.parent = null;
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * @param changer Родитель для замены детей.
     */
    public void changeChildren(Innovator<T> changer) {
        Martian<T> tempM = this;
        while (tempM.parent != null) {
            tempM = tempM.parent;
            if (tempM == changer) throw new IllegalArgumentException("This Martian can't be its own ancestor!");
        }
        tempM = changer;
        while (tempM.parent != null) {
            tempM = tempM.parent;
            if (this == tempM) throw new IllegalArgumentException("This Martian can't be its own ancestor!");
        }
        ArrayList<Martian<T>> temp = children;
        children = changer.children;
        changer.children = temp;
    }

    /**
     * @return Getter Свойство для получения коллекции детей.
     */
    public List<Martian<T>> getChildren() {
        return children;
    }

    /**
     * @return Коллекция всей семьи данного марсианина.
     */
    public List<Martian<T>> getAllFamily() {
        List<Martian<T>> innovatorList = new ArrayList<Martian<T>>();
        if (getChildren().size() > 0) {
            for (Martian<T> child : super.children) {
                innovatorList.addAll(child.getAllFamily());
            }
        }
        innovatorList.add(this);
        return innovatorList;
    }

    /**
     * @return Коллекция всех потомков данного марсианина.
     */
    public List<Martian<T>> getAllChildren() {
        List<Martian<T>> innovatorList = new ArrayList<Martian<T>>();
        if (getChildren().size() > 0) {
            for (Martian<T> child : super.children) {
                innovatorList.addAll(child.getAllFamily());
            }
        }
        return innovatorList;
    }

    /**
     * @param value Ген-код ребенка.
     * @return Есть ли ребенок с ген-кодом.
     */
    public boolean hasChildWithValue(T value) {
        for (Martian<T> child : children) {
            if (child.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param value Ген-код потомка.
     * @return Есть ли потомок с данным ген-кодом.
     */
    public boolean hasDescadantWithValue(T value) {
        List<Martian<T>> innovatorList = getAllChildren();
        for (Martian<T> tInnovator : innovatorList) {
            if (tInnovator.getValue().equals(value))
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
