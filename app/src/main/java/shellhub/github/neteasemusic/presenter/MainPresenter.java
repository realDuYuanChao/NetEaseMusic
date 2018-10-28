package shellhub.github.neteasemusic.presenter;

import android.os.Bundle;

import shellhub.github.neteasemusic.model.entities.MusicMenuEvent;

public interface MainPresenter {
    void update(Bundle bundle);

    void musicMenuNavigate(MusicMenuEvent musicMenuEvent);

}
