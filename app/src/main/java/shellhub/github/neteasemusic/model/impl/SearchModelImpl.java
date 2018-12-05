package shellhub.github.neteasemusic.model.impl;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import shellhub.github.neteasemusic.model.SearchModel;
import shellhub.github.neteasemusic.model.entities.SearchHistory;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.response.search.SearchResponse;
import shellhub.github.neteasemusic.response.search.SongsItem;
import shellhub.github.neteasemusic.response.search.artist.ArtistResponse;
import shellhub.github.neteasemusic.response.search.hot.HotResponse;
import shellhub.github.neteasemusic.response.search.mp3.SongResponse;
import shellhub.github.neteasemusic.response.search.song.detail.SongDetailResponse;
import shellhub.github.neteasemusic.response.search.video.VideoResponse;
import shellhub.github.neteasemusic.util.ConstantUtils;
import shellhub.github.neteasemusic.util.MusicUtils;
import shellhub.github.neteasemusic.util.NetEaseMusicApp;
import shellhub.github.neteasemusic.util.TagUtils;
import shellhub.github.neteasemusic.vo.NetworkMusic;

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
                callback.onHotSuccess(data);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void searchByKeywords(String keyword, Callback callback) {
        saveHistory(keyword);
        mNetEaseMusicService.search(keyword, new NetEaseMusicService.Callback<SearchResponse>() {

            @Override
            public void onSuccess(SearchResponse searchResponse) {
                callback.onKeywordSuccess(searchResponse);

                //store search result
                for (SongsItem songsItem : searchResponse.getResult().getSongs()) {
                    mNetEaseMusicService.getSongUrl(songsItem.getId(), new NetEaseMusicService.Callback<SongResponse>() {

                        @Override
                        public void onSuccess(SongResponse data) {
                            data.getData().get(0).getUrl();
                            NetworkMusic networkMusic = new NetworkMusic();
                            networkMusic.setId(songsItem.getId());
                            networkMusic.setUrl(data.getData().get(0).getUrl());
                            networkMusic.setName(songsItem.getName());
                            networkMusic.setArtistAndAlbum(MusicUtils.getArtistAndAlbum(songsItem));
                            EventBus.getDefault().post(networkMusic);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void loadMore(String keyword, int offset, Callback callback) {
        mNetEaseMusicService.search(keyword, offset, new NetEaseMusicService.Callback<SearchResponse>() {
            @Override
            public void onSuccess(SearchResponse data) {
                callback.onLoadMoreSuccess(data);

                //store search re
                for (SongsItem songsItem : data.getResult().getSongs()) {
                    mNetEaseMusicService.getSongUrl(songsItem.getId(), new NetEaseMusicService.Callback<SongResponse>() {

                        @Override
                        public void onSuccess(SongResponse data) {
                            data.getData().get(0).getUrl();
                            NetworkMusic networkMusic = new NetworkMusic();
                            networkMusic.setId(songsItem.getId());
                            networkMusic.setUrl(data.getData().get(0).getUrl());
                            networkMusic.setName(songsItem.getName());
                            networkMusic.setArtistAndAlbum(MusicUtils.getArtistAndAlbum(songsItem));
                            EventBus.getDefault().post(networkMusic);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
                }
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

    @Override
    public void loadHistory(Callback callback) {
        NetEaseMusicApp.getDBInstance().searchHistoryDao().getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SearchHistory>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<SearchHistory> searchHistories) {
                        List<String> histories = new ArrayList<>();
                        for (int i = 0, size = searchHistories.size(); i < size && histories.size() <= 5; i++) {
                            if (!histories.contains(searchHistories.get(i).getKeyword())) {
                                histories.add(searchHistories.get(i).getKeyword());
                            }
                        }
                        callback.onHistory(histories);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void saveHistory(String keyword) {

        new Thread(() -> {
            List<String> keywords =  NetEaseMusicApp.getDBInstance().searchHistoryDao().getAllKeywords();

            NetEaseMusicApp.getDBInstance().searchHistoryDao().deleteAll();
//            make keyword to top
            keywords.add(0, keyword);
            keywords = removeRepeat(keywords);
            LogUtils.d(TAG, keywords);
            //save top 5 history
            for (int i = 0, size = keywords.size(); i < size && i < 5; i++) {
                NetEaseMusicApp.getDBInstance().searchHistoryDao().insertAll(new SearchHistory(i, keywords.get(i)));
            }
        }).start();
    }

    private List<String> removeRepeat(List<String> list) {
        List<String> result = new ArrayList<>();
        for (String item : list) {
            if (!result.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public void removeHistory() {
        new Thread(() -> {
            NetEaseMusicApp.getDBInstance().searchHistoryDao().deleteAll();
        }).start();
    }

    @Override
    public void saveSong(SongsItem songsItem, Callback callback) {
        MusicUtils.saveSongId(songsItem.getId());
        SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).put(ConstantUtils.SP_CURRENT_SONG_NAME_KEY, songsItem.getName());
        SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).put(ConstantUtils.SP_CURRENT_SONG_ARTIST_AND_ALBUM_KEY, MusicUtils.getArtistAndAlbum(songsItem));

        mNetEaseMusicService.getSongUrl(songsItem.getId(), new NetEaseMusicService.Callback<SongResponse>() {

            @Override
            public void onSuccess(SongResponse data) {
                LogUtils.d(TAG, data.getData().get(0).getUrl());
                callback.onSongReady(data.getData().get(0).getUrl());
            }

            @Override
            public void onError(Throwable e) {

            }
        });
        mNetEaseMusicService.getSongDetail(songsItem.getId(), new NetEaseMusicService.Callback<SongDetailResponse>() {
            @Override
            public void onSuccess(SongDetailResponse data) {
                //store pic
                LogUtils.d(TAG, data.getSongs().get(0).getAl().getPicUrl());
                SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE)
                        .put(ConstantUtils.SP_CURRENT_SONG_ALBUM_URL_KEY, data.getSongs().get(0).getAl().getPicUrl());
                Utils.getApp().sendBroadcast(new Intent(ConstantUtils.ACTION_UPDATE_NOTIFICATION));
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

}
