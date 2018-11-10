package shellhub.github.neteasemusic.model;

import java.util.List;

import shellhub.github.neteasemusic.presenter.impl.SearchPresenterImpl;
import shellhub.github.neteasemusic.response.search.SearchResponse;
import shellhub.github.neteasemusic.response.search.artist.ArtistResponse;
import shellhub.github.neteasemusic.response.search.hot.HotResponse;
import shellhub.github.neteasemusic.response.search.video.VideoResponse;

public interface SearchModel {
    void searchHot(Callback callback);

    void searchByKeywords(String keyword, Callback callback);

    void searchVideo(String keyword, Callback callback);

    void searchArtist(String keyword, Callback callback);

    void loadHistory(Callback callback);

    void saveHistory(String keyword);

    interface Callback{
        void onHotSuccess(HotResponse response);

        void onKeywordSuccess(SearchResponse searchResponse);

        void onVideoSuccess(VideoResponse videoResponse);

        void onArtistSuccess(ArtistResponse artistResponse);

        void onHistory(List<String> histories);

        void onHotFail();
    }

}
