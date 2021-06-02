package sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author Rusakov Ivan
 */
public class Tree<T> {

    /**
     * Семья для составления генеалогического древа.
     */
    public List<Martian<T>> family;

    /**
     * @param member Один из марсиан для получения всей его семьи и древа.
     */
    Tree(Martian<T> member) {
        family = getAllFamily(member);
        Collections.reverse(family);
    }

    /**
     * Конструктор для создания пустого древа.
     */
    Tree() {
        family = null;
    }

    /**
     * @return Получить отчет в виде генеалогического древа.
     */
    public ArrayList<String> getReport() {
        if (family == null) throw new NullPointerException("This tree doesnt have any member!");
        StringBuilder report = new StringBuilder();
        String space = "    ";

        ArrayList<String> tree = new ArrayList<>();
        List<Martian<T>> martianList = family;

        Stack<Martian<T>> parents = new Stack<>();
        parents.push(martianList.get(0));

        report.append(martianList.get(0));
        tree.add(report.toString());
        report.delete(0, report.length());

        for (int i = 1; i < martianList.size(); i++) {
            if (martianList.get(i).getParent() == parents.peek()) {
                report.append(space);
                report.append(martianList.get(i));
                parents.push(martianList.get(i));
                tree.add(report.toString());
                report.delete(0, report.length());
                space += "    ";
            } else {
                parents.pop();
                i--;
                space = space.replaceFirst("    ", "");
            }
        }
        return tree;
    }

    /**
     * @param tree Отчет в виде генеалогического древа.
     * @return Коллекция семьи с ген-кодом в виде Integer.
     */
    public List<Martian<Integer>> getIntegerTreeFromReport(ArrayList<String> tree) {
        ArrayList<Martian<Integer>> newFamily = new ArrayList<>();
        String first = tree.get(0);
        String space = "    ";
        Innovator<Integer> martian1 = new Innovator<Integer>(getValueFromString(first));
        newFamily.add(martian1);

        Stack<Innovator<Integer>> parents = new Stack<>();
        parents.push(martian1);
        for (int i = 1; i < tree.size(); i++) {
            if (tree.get(i).startsWith(space + "I") || tree.get(i).startsWith(space + "C")) {
                Innovator<Integer> tempMartian =
                        new Innovator<Integer>(getValueFromString(tree.get(i)), parents.peek());
                newFamily.add(tempMartian);
                parents.push(tempMartian);
                space += "    ";
            } else {
                i--;
                parents.pop();
                space = space.replaceFirst("    ", "");
            }
        }
        if (first.startsWith("Innovator")) {
            return newFamily;
        } else if (tree.get(0).startsWith("Conservator")) {
            Conservator<Integer> parent = new Conservator<Integer>((Innovator<Integer>) newFamily.get(0));
            return parent.getAllFamily();
        }
        return null;
    }

    /**
     * @param tree Отчет в виде генеалогического древа.
     * @return Коллекция семьи с ген-кодом в виде String.
     */
    public List<Martian<Double>> getDoubleTreeFromReport(ArrayList<String> tree) {
        ArrayList<Martian<Double>> newFamily = new ArrayList<>();
        String first = tree.get(0);
        String space = "    ";
        Innovator<Double> martian1 = new Innovator<Double>(getDoubleFromString(first));
        newFamily.add(martian1);

        Stack<Innovator<Double>> parents = new Stack<>();
        parents.push(martian1);
        for (int i = 1; i < tree.size(); i++) {
            if (tree.get(i).startsWith(space + "I") || tree.get(i).startsWith(space + "C")) {
                Innovator<Double> tempMartian =
                        new Innovator<Double>(getDoubleFromString(first), parents.peek());
                newFamily.add(tempMartian);
                parents.push(tempMartian);
                space += "    ";
            } else {
                i--;
                parents.pop();
                space = space.replaceFirst("    ", "");
            }
        }
        if (first.startsWith("Innovator")) {
            return newFamily;
        } else if (tree.get(0).startsWith("Conservator")) {
            Conservator<Double> parent = new Conservator<Double>((Innovator<Double>) newFamily.get(0));
            return parent.getAllFamily();
        }
        return null;
    }

    /**
     * @param tree Отчет в виде генеалогического древа.
     * @return Коллекция семьи с ген-кодом в виде String.
     */
    public List<Martian<String>> getStringTreeFromReport(ArrayList<String> tree) {
        ArrayList<Martian<String>> newFamily = new ArrayList<>();
        String first = tree.get(0);
        String space = "    ";
        Innovator<String> martian1 = new Innovator<String>(getStringFromString(first));
        newFamily.add(martian1);

        Stack<Innovator<String>> parents = new Stack<>();
        parents.push(martian1);
        for (int i = 1; i < tree.size(); i++) {
            if (tree.get(i).startsWith(space + "I") || tree.get(i).startsWith(space + "C")) {
                Innovator<String> tempMartian =
                        new Innovator<String>(getStringFromString(first), parents.peek());
                newFamily.add(tempMartian);
                parents.push(tempMartian);
                space += "    ";
            } else {
                i--;
                parents.pop();
                space = space.replaceFirst("    ", "");
            }
        }
        if (first.startsWith("Innovator")) {
            return newFamily;
        } else if (tree.get(0).startsWith("Conservator")) {
            Conservator<String> parent = new Conservator<String>((Innovator<String>) newFamily.get(0));
            return parent.getAllFamily();
        }
        return null;
    }

    /**
     * @param data Строка из генеалогического древа.
     * @return Значение ген-кода с виде Integer.
     */
    private int getValueFromString(String data) {
        String temp = data.substring(data.indexOf(":") + 1
                , data.indexOf(")"));
        return Integer.parseInt(temp);
    }

    /**
     * @param data Строка из генеалогического древа.
     * @return Значение ген-кода в виде Double.
     */
    private double getDoubleFromString(String data) {
        String temp = data.substring(data.indexOf(":") + 1
                , data.indexOf(")"));
        return Double.parseDouble(temp);
    }

    /**
     * @param data Строка из генеалогического древа.
     * @return Значение ген-кода с виде String.
     */
    private String getStringFromString(String data) {
        String temp = data.substring(data.indexOf(":") + 1
                , data.indexOf(")"));
        return temp;
    }

    /**
     * @param member Марсианин для получения его семьи.
     * @return Вся семья данного Марсианина.
     */
    public List<Martian<T>> getAllFamily(Martian<T> member) {
        while (member.getParent() != null)
            member = member.getParent();
        return member.getAllFamily();
    }
}
