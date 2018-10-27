package shellhub.github.neteasemusic.presenter;

import shellhub.github.neteasemusic.model.entities.User;

public interface LoginPresenter {
    void validateCredentials(User user);
}
