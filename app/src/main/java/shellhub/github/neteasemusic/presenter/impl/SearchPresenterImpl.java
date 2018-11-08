package shellhub.github.neteasemusic.presenter.impl;

import shellhub.github.neteasemusic.model.SearchModel;
import shellhub.github.neteasemusic.model.impl.SearchModelImpl;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.presenter.SearchPresenter;
import shellhub.github.neteasemusic.response.search.SearchResponse;
import shellhub.github.neteasemusic.response.search.hot.HotResponse;
import shellhub.github.neteasemusic.view.SearchView;

public class SearchPresenterImpl implements SearchPresenter, SearchModel.Callback {
    private SearchModel mSearchModel;
    private NetEaseMusicService mNetEaseMusicService;
    private SearchView mSearchView;

    public SearchPresenterImpl(SearchView searchView, NetEaseMusicService netEaseMusicService) {
        this.mSearchView = searchView;
        this.mNetEaseMusicService = netEaseMusicService;
        this.mSearchModel = new SearchModelImpl(this.mNetEaseMusicService);
    }

    @Override
    public void searchHot() {
        mSearchModel.searchHot(this);
    }

    @Override
    public void search(String keyword) {
        mSearchView.showProgress();
        mSearchModel.searchByKeywords(keyword, this);
    }

    @Override
    public void onHotSuccess(HotResponse response) {
        mSearchView.showHots(response);
    }

    @Override
    public void onKeywordSuccess(SearchResponse searchResponse) {
        mSearchView.hideProgress();
        mSearchView.showSearchResult(searchResponse);
    }

    @Override
    public void onHotFail() {
        //TODO
    }
}
