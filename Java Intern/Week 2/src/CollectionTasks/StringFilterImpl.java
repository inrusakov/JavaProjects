package CollectionTasks;

import java.util.*;

public class StringFilterImpl implements StringFilter {
    ArrayList<String> list = new ArrayList<String>();

    public StringFilterImpl() {
    }

    @Override
    public void add(String s) {
        if (s != null) {
            s = s.toLowerCase();
        }
        if (!list.contains(s)) {
            list.add(s);
        }
    }

    @Override
    public boolean remove(String s) {
        s = s.toLowerCase();
        if (list.contains(s)) {
            list.remove(s);
            return true;
        } else
            return false;
    }

    @Override
    public void removeAll() {
        list.clear();
    }

    @Override
    public Collection<String> getCollection() {
        return list;
    }

    @Override
    public Iterator<String> getStringsContaining(String chars) {
        return new Filter() {
            @Override
            public Iterator<String> check(List list) {
                Iterator<String> iterator = list.iterator();
                if (chars == null || chars.equals("")) {
                    return iterator;
                } else {
                    ArrayList<String> result = new ArrayList<String>();
                    while (iterator.hasNext()) {
                        String addStr = iterator.next();
                        if (addStr != null && addStr.contains(chars)) {
                            result.add(addStr);
                        }
                    }
                    return result.iterator();
                }
            }
        }.check(list);
    }

    @Override
    public Iterator<String> getStringsStartingWith(String begin) {
        return new Filter() {
            @Override
            public Iterator<String> check(List list) {
                if (begin == null || begin.isEmpty()) {
                    return list.iterator();
                }

                ArrayList<String> resultOfChecking = new ArrayList();
                Iterator<String> iterator = list.iterator();
                String beg = begin.toLowerCase();

                String temp = null;
                while (iterator.hasNext()) {
                    String elem = iterator.next();
                    if (elem.length() >= beg.length()) {
                        temp = elem.substring(0, beg.length());
                        if (temp.equals(beg)) {
                            resultOfChecking.add(elem);
                        }
                    }
                }
                return resultOfChecking.iterator();
            }
        }.check(list);
    }

    @Override
    public Iterator<String> getStringsByNumberFormat(String format) {
        return new Filter() {
            @Override
            public Iterator<String> check(List list) {
                ArrayList<String> resultOfChecking = new ArrayList();
                Iterator<String> iterator = list.iterator();
                if (format == null || format.isEmpty()) {
                    return list.iterator();
                }

                while (iterator.hasNext()) {
                    String elem = iterator.next();
                    boolean[] flag = new boolean[format.length()];
                    if (elem.length() == format.length()) {
                        for (int i = 0; i < format.length(); i++) {

                            char first = elem.charAt(i);
                            char second = format.charAt(i);

                            if ((Character.isDigit(first) & second == '#') || (first == second)) {
                                flag[i] = true;
                            }
                        }
                    }
                    int counter = 0;
                    for (int i = 0; i < format.length(); i++) {
                        if (flag[i]) {
                            counter++;
                        }
                    }
                    if (counter == format.length())
                        resultOfChecking.add(elem);
                }
                return resultOfChecking.iterator();
            }
        }.check(list);
    }

    @Override
    public Iterator getStringsByPattern(String pattern) {
        return new Filter() {
            @Override
            public Iterator<String> check(List list) {
                ArrayList<String> result = new ArrayList<String>();
                Iterator<String> iterator = list.iterator();
                if (pattern == null || pattern.isEmpty()) {
                    return list.iterator();
                }
                int border1 = pattern.indexOf("*");
                int border2 = pattern.lastIndexOf("*");

                while (iterator.hasNext()) {
                    String next = iterator.next();
                    if (border1 == border2) {
                        if (border1 == 0) {
                            if (next.regionMatches(next.length() - pattern.length() + 1,
                                    pattern, 1, pattern.length() - 1)) {
                                result.add(next);
                            }
                        }
                        if (border1 == pattern.length() - 1) {
                            if (next.regionMatches(0, pattern, 0, pattern.length() - 1)) {
                                result.add(next);
                            }
                        } else {
                            if (next.regionMatches(0, pattern, 0, border1)
                                    & next.regionMatches(next.length() - pattern.length() + border1 + 1, pattern,
                                    border1 + 1, pattern.length() - border1 - 1)) {
                                result.add(next);
                            }
                        }
                    } else {
                        if (border1 == 0 & border2 == pattern.length() - 1) {
                            if (next.contains(pattern.substring(1, pattern.length() - 1))) {
                                result.add(next);
                            }
                        }
                        String substring = next.substring(next.length() - pattern.length() + border2 + 1);
                        boolean equals1 = substring.equals(pattern.substring(border2 + 1));
                        if (border1 == 0 & border2 != pattern.length() - 1) {
                            if ((next.substring(0, next.length() - pattern.length() + border2 - 2))
                                    .contains(pattern.substring(1, border2 - 1)) & equals1)
                                result.add(next);
                        }
                        boolean equals2 = next.substring(0, border1).equals(pattern.substring(0, border1));
                        if (border1 != 0 & border2 == pattern.length() - 1) {
                            if (equals2 & (next.substring(border1)).contains(pattern.substring(border1 + 1, border2))) {
                                result.add(next);
                            }
                        } else {
                            if (!next.isEmpty()) {
                                if (equals2 & next.substring(border1, next.length() - pattern.length() + border2 + 2)
                                        .contains(pattern.substring(border1 + 1, border2)) & equals1) {
                                    result.add(next);
                                }
                            }
                        }
                    }
                }
                return result.iterator();
            }
        }.check(list);
    }

    interface Filter {
        Iterator check(List list);
    }
}
