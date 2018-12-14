package shellhub.github.neteasemusic.model.impl;

import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;

import shellhub.github.neteasemusic.model.SplashModel;
import shellhub.github.neteasemusic.model.entities.User;
import shellhub.github.neteasemusic.util.AccountUtils;

public class SplashModelImpl implements SplashModel {
    @Override
    public void checkLogin(SplashCallback callback) {
        User user = AccountUtils.restore();
        if (TextUtils.isEmpty(user.getUsername()) || TextUtils.isEmpty(user.getPassword())) {
            callback.navigateAccount();
        }else{
            callback.navigateMain();
        }
    }
}
