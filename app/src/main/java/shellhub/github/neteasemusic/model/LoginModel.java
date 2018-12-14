package shellhub.github.neteasemusic.model;

import shellhub.github.neteasemusic.model.entities.User;
import shellhub.github.neteasemusic.response.login.LoginResponse;

public interface LoginModel {
    void login(User user, LoginCallback callback);

    interface LoginCallback {
        void onUsernameError(String errorMsg);

        void onPasswordError(String errorMsg);

        void onSuccess(LoginResponse response);

    }
}
