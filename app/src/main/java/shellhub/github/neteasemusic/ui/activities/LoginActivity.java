package shellhub.github.neteasemusic.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shellhub.github.neteasemusic.BaseApp;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.entities.User;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.presenter.LoginPresenter;
import shellhub.github.neteasemusic.presenter.impl.LoginPresenterImpl;
import shellhub.github.neteasemusic.response.login.LoginResponse;
import shellhub.github.neteasemusic.util.ConstantUtils;
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
        loginPresenter.validateCredentials(new User(phone,password));
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
    public void usernameError(String errorMsg) {
        ToastUtils.showLong(errorMsg);
    }

    @Override
    public void passwordError(String errorMsg) {
        ToastUtils.showLong(errorMsg);
    }

    @Override
    public void loginSuccess(LoginResponse loginResponse) {
        pbLogin.setVisibility(View.GONE);
        Intent intent = new Intent(this, MainActivity.class);
        ActivityUtils.startActivity(intent.putExtra(ConstantUtils.LOGIN_RESPONSE_KEY, loginResponse));
    }
}
