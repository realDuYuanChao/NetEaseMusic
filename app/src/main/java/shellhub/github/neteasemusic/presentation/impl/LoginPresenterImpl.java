package shellhub.github.neteasemusic.presentation.impl;

import android.content.Intent;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;

import rx.subscriptions.CompositeSubscription;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.User;
import shellhub.github.neteasemusic.model.login.LoginSuccessResponse;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.presentation.LoginPresenter;
import shellhub.github.neteasemusic.util.AccountUtils;
import shellhub.github.neteasemusic.util.ConstantUtils;
import shellhub.github.neteasemusic.view.LoginView;
import shellhub.github.neteasemusic.view.activities.MainActivity;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView mLoginView;
    private NetEaseMusicService mNetEaseMusicService;
    private CompositeSubscription subscriptions;

    private String TAG = LoginPresenterImpl.class.getSimpleName();

    public LoginPresenterImpl(NetEaseMusicService netEaseMusicService, LoginView mLoginView) {
        this.mNetEaseMusicService = netEaseMusicService;
        this.mLoginView = mLoginView;
    }

    @Override
    public void loginByPhone(final String phone, final String password) {
        if (AccountUtils.checkLogin(phone, password)) {
            mLoginView.showProgress();
            mNetEaseMusicService.login(phone, password, new NetEaseMusicService.LoginCallback() {
                @Override
                public void onSuccess(LoginSuccessResponse loginSuccessResponse) {
                    if (loginSuccessResponse != null && loginSuccessResponse.getCode() == ConstantUtils.SUCCESS) {
                        mLoginView.hideProgress();
                        AccountUtils.store(new User(phone, password));
                        Intent intent = new Intent();
                        intent.putExtra("getBackgroundUrl", loginSuccessResponse.getProfile().getBackgroundUrl());
                        ActivityUtils.startActivity(MainActivity.class, R.anim.slide_in_up, R.anim.slide_out_up);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    LogUtils.dTag(TAG, e.fillInStackTrace());
                    mLoginView.hideProgress();
                    mLoginView.invalidateAccount();
                }
            });
        }
    }
}
