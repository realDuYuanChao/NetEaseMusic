package shellhub.github.neteasemusic.model.impl;

import android.view.View;

import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.AccountModel;

public class AccountModelImpl implements AccountModel {
    @Override
    public void navigate(View view, Callback callback) {
        switch (view.getId()) {
            case R.id.btn_mobile_login:
                callback.onLogin();
                break;
            case R.id.btn_register:
                callback.onRegister();
                break;
        }
    }
}
