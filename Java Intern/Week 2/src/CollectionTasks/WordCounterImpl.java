package CollectionTasks;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordCounterImpl implements WordCounter{
    private String text;
    private HashMap<String, Long> words;

    public WordCounterImpl() {
        words = new HashMap<>();
    }

    public WordCounterImpl(String text) {
        this.text = text;
    }

    private void checkText() {
        if (text == null) {
            throw new IllegalStateException();
        }
    }

    private void counter() {
        Pattern pattern = Pattern.compile("\\b(?!<)(?<word>[a-zA-Zа-яА-Яё0-9]+)(?!>)\\b");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String word = matcher.group("word").toLowerCase();
            words.put(word, words.getOrDefault(word, 0L) + 1);
        }
    }

    private static int compare(Map.Entry<String, Long> first, Map.Entry<String, Long> second) {
        long difference = second.getValue() - first.getValue();
        int intDifference;
        if (difference > 0L) {
            return 1;
        } else if (difference < 0L) {
            return -1;
        } else {
            return first.getKey().charAt(0) - second.getKey().charAt(0);
        }
    }

    @Override
    public void setText(String text) {
        this.text = text;
        if (this.text != null) {
            counter();
        }
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Map<String, Long> getWordCounts() {
        checkText();
        return words;
    }

    @Override
    public List<Map.Entry<String, Long>> getWordCountsSorted() {
        checkText();
        return words.entrySet().stream().sorted(WordCounterImpl::compare).collect(Collectors.toList());
    }

    @Override
    public <K extends Comparable<K>, V extends Comparable<V>> List<Map.Entry<K, V>> sort(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        return map.entrySet().stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public <K, V> void print(List<Map.Entry<K, V>> entries, PrintStream ps) {
        for (Map.Entry<K, V> entry : entries) {
            ps.print((entry.getKey().toString() + " " + entry.getValue().toString()).toLowerCase() + "\n");
        }
    }
}
