package shellhub.github.neteasemusic.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class TagUtilsTest {

    @Test
    public void getTag() {
        System.out.println(TagUtils.getTag(this.getClass()));
    }

    @Test
    public void getTagWithPackage() {
        System.out.println(TagUtils.getTag(this.getClass(), true));
    }
}