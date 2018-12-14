package shellhub.github.neteasemusic.presenter.impl;

import android.view.View;

import shellhub.github.neteasemusic.model.AccountModel;
import shellhub.github.neteasemusic.model.impl.AccountModelImpl;
import shellhub.github.neteasemusic.presenter.AccountPresenter;
import shellhub.github.neteasemusic.view.AccountView;

public class AccountPresenterImpl implements AccountPresenter, AccountModel.Callback {
    AccountView mAccountView;
    AccountModel mAccountModel;

    public AccountPresenterImpl(AccountView mAccountView) {
        this.mAccountView = mAccountView;
        mAccountModel = new AccountModelImpl();
    }

    @Override
    public void navigate(View view) {
        mAccountModel.navigate(view, this);
    }

    @Override
    public void onLogin() {
        mAccountView.navigateLogin();
    }

    @Override
    public void onRegister() {
        mAccountView.navigateRegister();
    }

    @Override
    public void onEmail() {

    }

    @Override
    public void onWechat() {

    }

    @Override
    public void onQQ() {

    }

    @Override
    public void onWeibo() {

    }
}
