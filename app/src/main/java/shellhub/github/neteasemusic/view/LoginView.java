package shellhub.github.neteasemusic.view;

import shellhub.github.neteasemusic.response.login.LoginResponse;

public interface LoginView extends BaseView {
    void showProgress();

    void hideProgress();

    void usernameError(String errorMsg);

    void passwordError(String errorMsg);

    void loginSuccess(LoginResponse loginResponse);
}