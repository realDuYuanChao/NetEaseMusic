package shellhub.github.neteasemusic.deps;

import javax.inject.Singleton;

import dagger.Component;
import shellhub.github.neteasemusic.networking.NetworkModule;
import shellhub.github.neteasemusic.view.activities.LoginActivity;

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(LoginActivity loginActivity);
}