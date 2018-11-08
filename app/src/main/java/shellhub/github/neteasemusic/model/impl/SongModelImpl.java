package shellhub.github.neteasemusic.model.impl;

import shellhub.github.neteasemusic.model.SongModel;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.response.mp3.SongResponse;

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
}
