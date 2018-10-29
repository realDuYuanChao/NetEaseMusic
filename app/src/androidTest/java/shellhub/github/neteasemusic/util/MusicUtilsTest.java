package shellhub.github.neteasemusic.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class MusicUtilsTest {

    @Test
    public void getCount() {
        assertEquals(18, MusicUtils.getCount());
    }
}