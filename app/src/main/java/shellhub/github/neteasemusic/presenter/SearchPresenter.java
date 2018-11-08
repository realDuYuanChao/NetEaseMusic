package shellhub.github.neteasemusic.presenter;

public interface SearchPresenter {
    void searchHot();

    void search(String keyword);

    void searchVideo(String keyword);

    void searchArtist(String keyword);
}
