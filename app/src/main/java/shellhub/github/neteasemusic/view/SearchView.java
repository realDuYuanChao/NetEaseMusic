package shellhub.github.neteasemusic.view;

import shellhub.github.neteasemusic.model.entities.SearchHistory;
import shellhub.github.neteasemusic.response.search.SearchResponse;
import shellhub.github.neteasemusic.response.search.artist.ArtistResponse;
import shellhub.github.neteasemusic.response.search.hot.HotResponse;
import shellhub.github.neteasemusic.response.search.video.VideoResponse;

public interface SearchView extends BaseView {
    void showProgress();

    void hideProgress();

    void showHots(HotResponse hotResponse);

    void showHistory(SearchHistory searchHistory);

    void showSearchResult(SearchResponse searchResponse);

    void showVideos(VideoResponse videoResponse);

    void showArtist(ArtistResponse artistResponse);

    void showSearchResult();
}
