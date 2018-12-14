package shellhub.github.neteasemusic.presenter.impl;

import shellhub.github.neteasemusic.model.SplashModel;
import shellhub.github.neteasemusic.model.impl.SplashModelImpl;
import shellhub.github.neteasemusic.presenter.SplashPresenter;
import shellhub.github.neteasemusic.view.SplashView;

public class SplashPresenterImpl implements SplashPresenter, SplashModel.SplashCallback {
    private SplashView mSplashView;
    private SplashModel mSplashModel;
    public SplashPresenterImpl(SplashView mSplashView) {
        this.mSplashView = mSplashView;
        mSplashModel = new SplashModelImpl();
    }

    @Override
    public void navigate() {
        mSplashModel.checkLogin(this);
    }

    @Override
    public void navigateMain() {
        mSplashView.navigateMain();
    }

    @Override
    public void navigateLogin() {
        mSplashView.navigateLogin();
    }

    @Override
    public void navigateAccount() {
        mSplashView.navigateAccount();
    }
}
