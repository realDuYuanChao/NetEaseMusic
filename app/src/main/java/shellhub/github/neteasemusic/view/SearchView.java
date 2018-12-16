package shellhub.github.neteasemusic.view;

import java.util.List;

import shellhub.github.neteasemusic.response.search.mp3.SongResponse;
import shellhub.github.neteasemusic.response.search.SearchResponse;
import shellhub.github.neteasemusic.response.search.artist.ArtistResponse;
import shellhub.github.neteasemusic.response.search.hot.HotResponse;
import shellhub.github.neteasemusic.response.search.video.VideoResponse;

public interface SearchView extends BaseView {

    void hideKeyboard();

    void showHots(HotResponse hotResponse);

    void showHistory(List<String> searchHistory);

    void showSearchResult(SearchResponse searchResponse);

    void showVideos(VideoResponse videoResponse);

    void showArtist(ArtistResponse artistResponse);

    void showSearchResult();

    void playSong(String songUrl);

}
