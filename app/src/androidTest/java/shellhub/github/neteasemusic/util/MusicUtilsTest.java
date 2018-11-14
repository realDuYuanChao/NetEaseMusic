package shellhub.github.neteasemusic.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class MusicUtilsTest {

    @Test
    public void getCount() {
        assertEquals(18, MusicUtils.getCount());
    }


    @Test
    public void formatDuration() {
        System.out.println(MusicUtils.formatDuration(258508));
    }

    @Test
    public void random() {
        System.out.println(MusicUtils.random(10));
    }
}