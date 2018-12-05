package shellhub.github.neteasemusic.presenter.impl;

import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import shellhub.github.neteasemusic.model.SearchModel;
import shellhub.github.neteasemusic.model.impl.SearchModelImpl;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.presenter.SearchPresenter;
import shellhub.github.neteasemusic.response.search.SearchResponse;
import shellhub.github.neteasemusic.response.search.SongsItem;
import shellhub.github.neteasemusic.response.search.artist.ArtistResponse;
import shellhub.github.neteasemusic.response.search.hot.HotResponse;
import shellhub.github.neteasemusic.response.search.video.VideoResponse;
import shellhub.github.neteasemusic.util.TagUtils;
import shellhub.github.neteasemusic.view.SearchView;

public class SearchPresenterImpl implements SearchPresenter, SearchModel.Callback {
    private String TAG = TagUtils.getTag(this.getClass());
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
        mSearchView.showProgress();
        mSearchModel.searchHot(this);
    }

    @Override
    public void loadHistory() {
        mSearchModel.loadHistory(this);
    }

    @Override
    public void removeHistory() {
        mSearchView.showHistory(new ArrayList<>());
        mSearchModel.removeHistory();
    }

    @Override
    public void search(String keyword) {
        mSearchView.showProgress();
        mSearchModel.searchByKeywords(keyword, this);
    }

    @Override
    public void searchVideo(String keyword) {
        mSearchView.showProgress();
        mSearchModel.searchVideo(keyword, this);
    }

    @Override
    public void searchArtist(String keyword) {
        mSearchView.showProgress();
        mSearchModel.searchArtist(keyword, this);
    }

    @Override
    public void saveSong(SongsItem songsItem) {
        mSearchModel.saveSong(songsItem, this);
    }

    @Override
    public void loadMore(String keyword, int offset) {
        mSearchModel.loadMore(keyword, offset, this);
    }

    @Override
    public void onHotSuccess(HotResponse response) {
        mSearchView.hideProgress();
        mSearchView.showHots(response);
    }

    @Override
    public void onKeywordSuccess(SearchResponse searchResponse) {
        mSearchView.hideProgress();
        mSearchView.showSearchResult(searchResponse);
    }

    @Override
    public void onLoadMoreSuccess(SearchResponse searchResponse) {
//        showSearchResult
        mSearchView.showSearchResult(searchResponse);
    }

    @Override
    public void onVideoSuccess(VideoResponse videoResponse) {
        mSearchView.hideProgress();
        mSearchView.showVideos(videoResponse);
    }

    @Override
    public void onArtistSuccess(ArtistResponse artistResponse) {
        mSearchView.hideProgress();
        mSearchView.showArtist(artistResponse);
    }

    @Override
    public void onHistory(List<String> histories) {
        LogUtils.d(TAG, histories);
        mSearchView.showHistory(histories);
    }

    @Override
    public void onSongReady(String url) {
        mSearchView.playSong(url);
    }

    @Override
    public void onHotFail() {
        //TODO
    }

}
