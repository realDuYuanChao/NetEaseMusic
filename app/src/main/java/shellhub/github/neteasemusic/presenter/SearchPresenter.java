package shellhub.github.neteasemusic.presenter;

import shellhub.github.neteasemusic.response.search.SongsItem;

public interface SearchPresenter {
    void searchHot();

    void loadHistory();

    void removeHistory();

    void search(String keyword);

    void searchVideo(String keyword);

    void searchArtist(String keyword);

    void saveSong(SongsItem songsItem);

    void loadMore(String keyword, int offset);

}
