package shellhub.github.neteasemusic.util;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import shellhub.github.neteasemusic.model.User;

import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class AccountUtilsTest {

    @Test
    public void checkLogin() {
    }

    @Test
    public void restore() {
        Assert.assertEquals("1399761091", AccountUtils.restore().getUsername());
    }

    @Test
    public void store() {
        User user = new User("shellhub", "github");
    }
}