package shellhub.github.neteasemusic.model;

import shellhub.github.neteasemusic.response.playlist.Playlist;

public interface PlaylistModel {
    void getPlaylistDetail(long id, Callback callback);

    interface Callback{
        void onPlaylistDetail(Playlist playlist);
    }
}
