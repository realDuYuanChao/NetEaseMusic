package shellhub.github.neteasemusic.model.impl;

import android.text.TextUtils;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;

import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.LoginModel;
import shellhub.github.neteasemusic.model.entities.User;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.response.login.LoginResponse;
import shellhub.github.neteasemusic.util.AccountUtils;
import shellhub.github.neteasemusic.util.ConstantUtils;

public class LoginModelImpl implements LoginModel {
    private NetEaseMusicService mNetEaseMusicService;

    public LoginModelImpl(NetEaseMusicService mNetEaseMusicService) {
        this.mNetEaseMusicService = mNetEaseMusicService;
    }

    @Override
    public void login(final User user, final LoginCallback callback) {
        if (user == null || callback == null) {
            return;
        }
        if (TextUtils.isEmpty(user.getUsername())) {
            callback.onUsernameError(Utils.getApp().getResources().getString(R.string.mobile_number));
        }
        if (TextUtils.isEmpty(user.getPassword())) {
            callback.onUsernameError(Utils.getApp().getResources().getString(R.string.password));
        }

        mNetEaseMusicService.login(user.getUsername(), user.getPassword(), new NetEaseMusicService.Callback<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse response) {
                callback.onSuccess(response);

                //save user info
                user.setUid(response.getProfile().getUserId() + "");
                AccountUtils.store(user);
            }

            @Override
            public void onError(Throwable e) {
                String message = e.getMessage();
                ToastUtils.showLong(message);
                if (message.contains(ConstantUtils.INCORRECT_PASSWORD + "")) {
                    callback.onPasswordError(Utils.getApp().getResources().getString(R.string.incorrect_password));
                } else if (message.contains(ConstantUtils.TRY_PASSWORD_LIMIT + "")){
                    callback.onPasswordError(Utils.getApp().getResources().getString(R.string.try_password_limit));
                } else if (message.contains(ConstantUtils.ACCOUNT_NOT_EXISTS + "")) {
                    callback.onUsernameError(Utils.getApp().getString(R.string.account_not_exists));
                }else {
                    callback.onUsernameError("login error");
                }
            }
        });
    }
}
