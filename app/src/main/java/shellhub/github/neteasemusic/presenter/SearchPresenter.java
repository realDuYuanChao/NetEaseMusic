package shellhub.github.neteasemusic.presenter;

import shellhub.github.neteasemusic.response.search.SongsItem;

public interface SearchPresenter {
    void searchHot();

    void loadHistory();

    void saveHistory(String keyword);

    void search(String keyword);

    void searchVideo(String keyword);

    void searchArtist(String keyword);

    void saveSong(SongsItem songsItem);

}
