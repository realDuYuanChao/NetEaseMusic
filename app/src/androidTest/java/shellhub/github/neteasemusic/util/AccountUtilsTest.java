package shellhub.github.neteasemusic.util;

import androidx.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import shellhub.github.neteasemusic.model.entities.User;

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
        User user = new User("353543427", "shellhub", "github");
    }
}