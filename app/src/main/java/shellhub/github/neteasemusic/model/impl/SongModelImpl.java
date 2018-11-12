package shellhub.github.neteasemusic.model.impl;

import android.content.Context;

import com.blankj.utilcode.util.SPUtils;

import shellhub.github.neteasemusic.model.SongModel;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.response.search.mp3.SongResponse;
import shellhub.github.neteasemusic.response.search.song.detail.SongDetailResponse;
import shellhub.github.neteasemusic.util.ConstantUtils;

public class SongModelImpl implements SongModel {
    private NetEaseMusicService mNetEaseMusicService;

    public SongModelImpl(NetEaseMusicService mNetEaseMusicService) {
        this.mNetEaseMusicService = mNetEaseMusicService;
    }

    @Override
    public void getSongUrl(int id, Callback callback) {
        mNetEaseMusicService.getSongUrl(id, new NetEaseMusicService.Callback<SongResponse>() {
            @Override
            public void onSuccess(SongResponse data) {
                //store url
                SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).put(ConstantUtils.SP_CURRENT_SONG_URL_KEY, data.getData().get(0).getUrl());
                callback.onSongSuccess(data);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void getSongDetail(int ids, Callback callback) {
        mNetEaseMusicService.getSongDetail(ids, new NetEaseMusicService.Callback<SongDetailResponse>(){

            @Override
            public void onSuccess(SongDetailResponse data) {
                callback.onSongDetailSuccess(data);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
