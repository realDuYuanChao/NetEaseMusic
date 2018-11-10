package shellhub.github.neteasemusic.presenter;

public interface SearchPresenter {
    void searchHot();

    void loadHistory();

    void saveHistory(String keyword);

    void search(String keyword);

    void searchVideo(String keyword);

    void searchArtist(String keyword);

    void getSong(int id);
}
