package shellhub.github.neteasemusic;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void removeKeyword() {
//        List<String> keywords = Arrays.asList("1", "2", "1", "3");
        List<String> keywords = new LinkedList<>(Arrays.asList("1", "2", "1", "3"));
        while (keywords.remove("1")) {
        }
        assertEquals(2, keywords.size());
    }

    @Test
    public void saveHistory(){
        LinkedList<String> histories = new LinkedList<>(Arrays.asList("1", "2", "3", "4", "1", "2"));
        histories.add(0, "1");
        histories.add(0, "1");
        histories.add(0, "1");
        histories.add(0, "2");
        histories.add(0, "2");
        histories.add(0, "3");
        // add elements to al, including duplicates
        Set<String> hs = new HashSet<>(histories);
        histories.clear();
        histories.addAll(hs);
        System.out.println(histories);
    }
}