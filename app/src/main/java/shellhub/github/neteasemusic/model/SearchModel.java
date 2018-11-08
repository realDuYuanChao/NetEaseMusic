package shellhub.github.neteasemusic.model;

import shellhub.github.neteasemusic.response.search.SearchResponse;
import shellhub.github.neteasemusic.response.search.hot.HotResponse;

public interface SearchModel {
    void searchHot(Callback callback);

    void searchByKeywords(String keyword, Callback callback);

    interface Callback{
        void onHotSuccess(HotResponse response);

        void onKeywordSuccess(SearchResponse searchResponse);

        void onHotFail();
    }

}
