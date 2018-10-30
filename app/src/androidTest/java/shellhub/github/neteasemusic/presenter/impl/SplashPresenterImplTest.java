package shellhub.github.neteasemusic.presenter.impl;

import android.content.Context;
import androidx.test.runner.AndroidJUnit4;

import com.blankj.utilcode.util.SPUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import shellhub.github.neteasemusic.util.ConstantUtils;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SplashPresenterImplTest {

    @Test
    public void checkLogin() {
        SPUtils.getInstance(ConstantUtils.SP_LOGIN_USER, Context.MODE_PRIVATE).put(ConstantUtils.SP_LOGIN_USER_USERNAME_KEY, "1399761091");
        assertEquals("1399761091", SPUtils.getInstance().getString(SPUtils.getInstance(ConstantUtils.SP_LOGIN_USER, Context.MODE_PRIVATE).getString(ConstantUtils.SP_LOGIN_USER_USERNAME_KEY)));
    }
}