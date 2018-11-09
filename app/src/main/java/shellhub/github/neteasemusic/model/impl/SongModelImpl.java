package shellhub.github.neteasemusic.model.impl;

import shellhub.github.neteasemusic.model.SongModel;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.response.search.mp3.SongResponse;
import shellhub.github.neteasemusic.response.search.song.detail.SongDetailResponse;

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
