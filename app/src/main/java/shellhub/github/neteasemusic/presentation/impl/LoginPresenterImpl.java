package shellhub.github.neteasemusic.presentation.impl;

import android.content.Intent;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;

import rx.subscriptions.CompositeSubscription;
import shellhub.github.neteasemusic.model.User;
import shellhub.github.neteasemusic.response.login.LoginSuccessResponse;
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
            mNetEaseMusicService.login(phone, password, new NetEaseMusicService.Callback<LoginSuccessResponse>() {
                @Override
                public void onSuccess(LoginSuccessResponse data) {
                    LogUtils.d(TAG, data);
                    if (data != null && data.getCode() == ConstantUtils.SUCCESS) {
                        mLoginView.hideProgress();
                        AccountUtils.store(new User(data.getProfile().getUserId() + "", phone, password));

                        Intent intent = new Intent(ActivityUtils.getTopActivity(), MainActivity.class);
                        intent.putExtra(ConstantUtils.LOGIN_RESPONSE_KEY, data);
                        ActivityUtils.startActivity(intent);
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
