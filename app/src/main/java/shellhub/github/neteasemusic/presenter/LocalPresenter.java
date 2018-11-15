package shellhub.github.neteasemusic.presenter;

import shellhub.github.neteasemusic.model.entities.Single;

public interface LocalPresenter {
    void load();

    void query(String keyword);

    void loadSong(Single single);
}
