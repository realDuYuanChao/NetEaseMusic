package shellhub.github.neteasemusic.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class MusicUtilsTest {

    @Test
    public void formatDuration() {
        System.out.println(MusicUtils.formatDuration(5 * 60 * 1000));;
        System.out.println(MusicUtils.formatDuration(3 * 60 * 1000 + 40 * 1000));
        System.out.println(MusicUtils.formatDuration(3 * 60 * 1000 + 4 * 1000));
        System.out.println(MusicUtils.formatDuration(1 * 60 * 60 * 1000 + 3 * 60 * 1000 + 40 * 1000));
//        System.out.println(MusicUtils.formatDuration(258508));
    }

}