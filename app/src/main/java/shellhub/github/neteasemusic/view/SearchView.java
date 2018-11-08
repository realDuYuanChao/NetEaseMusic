package shellhub.github.neteasemusic.view;

import shellhub.github.neteasemusic.model.entities.SearchHistory;
import shellhub.github.neteasemusic.response.search.SearchResponse;
import shellhub.github.neteasemusic.response.search.hot.HotResponse;

public interface SearchView extends BaseView {
    void showProgress();

    void hideProgress();

    void showHots(HotResponse hotResponse);

    void showHistory(SearchHistory searchHistory);

    void showSearchResult(SearchResponse searchResponse);

}
