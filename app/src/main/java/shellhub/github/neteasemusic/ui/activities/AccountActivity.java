package shellhub.github.neteasemusic.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.presenter.AccountPresenter;
import shellhub.github.neteasemusic.presenter.impl.AccountPresenterImpl;
import shellhub.github.neteasemusic.util.ActivityUtils;
import shellhub.github.neteasemusic.view.AccountView;

public class AccountActivity extends AppCompatActivity implements AccountView {

    AccountPresenter mAccountPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActivityUtils.requestFullScreen(this, true);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);

        setUpMVP();
    }

    @OnClick({R.id.btn_mobile_login, R.id.btn_register})
    public void loginClick(View view) {
        mAccountPresenter.navigate(view);
    }

    @Override
    public void navigateLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void navigateRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void setUpMVP() {
        mAccountPresenter = new AccountPresenterImpl(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
