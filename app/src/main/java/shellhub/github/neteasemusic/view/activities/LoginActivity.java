package shellhub.github.neteasemusic.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.SnackbarUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shellhub.github.neteasemusic.BaseApp;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.presentation.LoginPresenter;
import shellhub.github.neteasemusic.presentation.impl.LoginPresenterImpl;
import shellhub.github.neteasemusic.view.LoginView;

public class LoginActivity extends BaseApp implements LoginView {
    private LoginPresenter loginPresenter;

    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.pb_login)
    ProgressBar pbLogin;


    @Inject
    public NetEaseMusicService mNetEaseMusicService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setUpMVP();
    }

    @Override
    public void setUpMVP() {
        loginPresenter = new LoginPresenterImpl(mNetEaseMusicService, this);
    }

    @OnClick(R.id.btn_login)
    public void loginCallback() {
        String phone = etLoginPhone.getText().toString();
        String password = etLoginPassword.getText().toString();
        loginPresenter.loginByPhone(phone, password);
    }

    @Override
    public void showProgress() {
        pbLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLogin.setVisibility(View.GONE);
    }

    @Override
    public void networkError() {

    }

    @Override
    public void invalidateAccount() {
        Snackbar.make(findViewById(R.id.login_container), R.string.login_error, Snackbar.LENGTH_LONG).setAction(R.string.i_know, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }
}
