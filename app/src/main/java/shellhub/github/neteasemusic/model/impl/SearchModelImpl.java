package shellhub.github.neteasemusic.model.impl;

import com.blankj.utilcode.util.LogUtils;

import shellhub.github.neteasemusic.model.SearchModel;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.response.search.SearchResponse;
import shellhub.github.neteasemusic.response.search.artist.ArtistResponse;
import shellhub.github.neteasemusic.response.search.hot.HotResponse;
import shellhub.github.neteasemusic.response.search.video.VideoResponse;
import shellhub.github.neteasemusic.util.TagUtils;

public class SearchModelImpl implements SearchModel {
    private String TAG = TagUtils.getTag(this.getClass());
    private NetEaseMusicService mNetEaseMusicService;

    public SearchModelImpl(NetEaseMusicService netEaseMusicService) {
        this.mNetEaseMusicService = netEaseMusicService;
    }

    @Override
    public void searchHot(Callback callback) {
        mNetEaseMusicService.searchHot(new NetEaseMusicService.Callback<HotResponse>() {
            @Override
            public void onSuccess(HotResponse data) {
                LogUtils.d(TAG, data);
                callback.onHotSuccess(data);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void searchByKeywords(String keyword, Callback callback) {
        mNetEaseMusicService.search(keyword, new NetEaseMusicService.Callback<SearchResponse>(){

            @Override
            public void onSuccess(SearchResponse searchResponse) {
                callback.onKeywordSuccess(searchResponse);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void searchVideo(String keyword, Callback callback) {
        mNetEaseMusicService.searchVideo(keyword, new NetEaseMusicService.Callback<VideoResponse>() {
            @Override
            public void onSuccess(VideoResponse data) {
                callback.onVideoSuccess(data);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void searchArtist(String keyword, Callback callback) {
        mNetEaseMusicService.searchArtist(keyword, new NetEaseMusicService.Callback<ArtistResponse>() {
            @Override
            public void onSuccess(ArtistResponse data) {
                callback.onArtistSuccess(data);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

}
