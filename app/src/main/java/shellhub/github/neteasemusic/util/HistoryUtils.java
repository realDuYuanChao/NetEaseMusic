package shellhub.github.neteasemusic.util;

import java.util.ArrayList;
import java.util.List;

public class HistoryUtils {
    private List<String> histories = new ArrayList<>();

    private List<String> getHistories(String keyword) {
        return null;
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("world");
        strings.add("hello");

        if (strings.contains("hello")) {
            strings.remove("hello");
            strings.add(0, "hello");
        }

        for (String s : strings) {
            System.out.println(s);
        }

        System.out.println(strings.subList(0, 2).size());
    }
}
