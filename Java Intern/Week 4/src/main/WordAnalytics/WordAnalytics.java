package WordAnalytics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WordAnalytics {

    private String string1;
    private String string2;

    public WordAnalytics(String string1, String string2) {
        this.string1 = string1;
        this.string2 = string2;
    }

    /**
     * Метод сортировки.
     * @param FORMAT Выбор метода.
     * @param arr Массив для сортировки.
     * @return Отсортированный массив.
     */
    private ArrayList<Character> sortResult(FORMAT FORMAT, ArrayList<Character> arr, Integer shift) {
        switch (FORMAT) {
            case REVERSE:
                arr = arr.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
                Collections.reverse(arr);
                return  arr;
            case NORMAL:
                return arr.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
            default:
                ArrayList<Integer> hase = new ArrayList<>();
                for (int i = 0; i < arr.size(); i++){
                    hase.add(arr.get(i).hashCode() << shift);
                }
                ArrayList<Character> res = new ArrayList<>();
                for (int i = 0; i < hase.size(); i++){
                    res.add((char)(hase.get(i)-48+'0'));
                }
                return res.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
        }
    }

    /**
     * @param format Метод сортировки.
     * @return Массив символов, содержащийхся в обеих строках.
     */
    public ArrayList<Character> intersection(FORMAT format, Integer shift) {
        Map<Character, Integer> letters = new HashMap<>();
        for (int i = 0; i < string1.length(); i++) {
            letters.put(
                    string1.charAt(i),
                    letters.get(string1.charAt(i)) == null ? 1 : letters.get(string1.charAt(i)) + 1);
        }
        ArrayList<Character> result = new ArrayList<>();
        for (int i = 0; i < string2.length(); i++) {
            if (letters.get(string2.charAt(i)) != null) {
                result.add(string2.charAt(i));
                letters.put(string2.charAt(i), null);
            }
        }
        return sortResult(format, result, shift);
    }

    /**
     * @param format Метод сортировки.
     * @return Массив символов, которые входят в первую, но не входят во вторую.
     */
    public ArrayList<Character> onlyFirstString(FORMAT format, Integer shift) {
        Map<Character, Integer> letters = new HashMap<>();
        for (int i = 0; i < string1.length(); i++) {
            letters.put(string1.charAt(i), 1);
        }
        for (int i = 0; i < string2.length(); i++) {
            if (letters.get(string2.charAt(i)) != null) {
                letters.put(string2.charAt(i), 2);
            }
        }
        ArrayList<Character> result = new ArrayList<>();
        for (Character key : letters.keySet()) {
            if (letters.get(key) == 1) {
                result.add(key);
            }
        }
        return sortResult(format, result, shift);
    }

    /**
     * @param format Метод сортировки.
     * @return Массив символов, которые содержаться хотя бы в одной строке.
     */
    public ArrayList<Character> unionIntersection(FORMAT format, Integer shift) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < string1.length(); i++) {
            map.put(string1.charAt(i), 1);
        }
        for (int i = 0; i < string2.length(); i++) {
            map.put(string2.charAt(i), 1);
        }
        return sortResult(format, new ArrayList<>(map.keySet()), shift);
    }
}
