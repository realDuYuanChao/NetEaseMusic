package shellhub.github.neteasemusic.model.impl;

import shellhub.github.neteasemusic.model.PlaylistModel;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.response.playlist.PlaylistDetailResponse;
import shellhub.github.neteasemusic.util.NetEaseMusicApp;
import shellhub.github.neteasemusic.util.TagUtils;

public class PlaylistModelIMpl implements PlaylistModel {
    private String TAG = TagUtils.getTag(this.getClass());
    @Override
    public void getPlaylistDetail(long id, Callback callback) {
        NetEaseMusicApp.getNetEaseMusicService().getPlaylistDetail(id, new NetEaseMusicService.Callback<PlaylistDetailResponse>(){

            @Override
            public void onSuccess(PlaylistDetailResponse data) {
                callback.onPlaylistDetail(data.getPlaylist());
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }
}
