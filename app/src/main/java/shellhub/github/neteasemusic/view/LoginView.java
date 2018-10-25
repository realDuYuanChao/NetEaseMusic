package shellhub.github.neteasemusic.view;

public interface LoginView extends BaseView {
    void showProgress();

    void hideProgress();

    void networkError();

    void invalidateAccount();
}