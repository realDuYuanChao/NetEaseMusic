package shellhub.github.neteasemusic.deps;

import javax.inject.Singleton;

import dagger.Component;
import shellhub.github.neteasemusic.networking.NetworkModule;
import shellhub.github.neteasemusic.ui.activities.CommentActivity;
import shellhub.github.neteasemusic.ui.activities.LoginActivity;
import shellhub.github.neteasemusic.ui.activities.MainActivity;
import shellhub.github.neteasemusic.ui.activities.PlayActivity;
import shellhub.github.neteasemusic.ui.activities.SearchActivity;

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);

    void inject(CommentActivity commentActivity);

    void inject(SearchActivity searchActivity);

    void inject(PlayActivity playActivity);
}