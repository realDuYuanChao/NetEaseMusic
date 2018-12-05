package shellhub.github.neteasemusic.presenter;

import android.os.Bundle;

import shellhub.github.neteasemusic.model.entities.MusicMenuIndexEvent;

public interface MainPresenter {
    void update(Bundle bundle);

    void musicMenuNavigate(MusicMenuIndexEvent musicMenuIndexEvent);

    void getBanner();
}
