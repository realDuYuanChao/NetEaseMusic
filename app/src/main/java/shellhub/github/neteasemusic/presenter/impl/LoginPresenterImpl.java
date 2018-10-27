package shellhub.github.neteasemusic.presenter.impl;

import rx.subscriptions.CompositeSubscription;
import shellhub.github.neteasemusic.model.LoginModel;
import shellhub.github.neteasemusic.model.entities.User;
import shellhub.github.neteasemusic.model.impl.LoginModelImpl;
import shellhub.github.neteasemusic.response.login.LoginResponse;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.presenter.LoginPresenter;
import shellhub.github.neteasemusic.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter, LoginModel.LoginCallback {
    private LoginView mLoginView;
    private LoginModel mLoginModel;
//    private NetEaseMusicService mNetEaseMusicService;
    private CompositeSubscription subscriptions;

    private String TAG = LoginPresenterImpl.class.getSimpleName();

    public LoginPresenterImpl(NetEaseMusicService netEaseMusicService, LoginView mLoginView) {
//        this.mNetEaseMusicService = netEaseMusicService;
        this.mLoginView = mLoginView;
        this.mLoginModel = new LoginModelImpl(netEaseMusicService);
    }

    @Override
    public void validateCredentials(User user) {
        if (mLoginView != null) {
            mLoginView.showProgress();
        }
        mLoginModel.login(user, this );
    }

    @Override
    public void onUsernameError(String errorMsg) {
        mLoginView.hideProgress();
        mLoginView.usernameError(errorMsg);
    }

    @Override
    public void onPasswordError(String errorMsg) {
        mLoginView.hideProgress();
        mLoginView.passwordError(errorMsg);
    }

    @Override
    public void onSuccess(LoginResponse response) {
        mLoginView.hideProgress();
        mLoginView.loginSuccess(response);
    }

}
