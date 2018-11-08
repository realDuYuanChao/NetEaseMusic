package shellhub.github.neteasemusic.model;

import shellhub.github.neteasemusic.response.mp3.SongResponse;

public interface SongModel {
    void getSongUrl(int id, Callback callback);

    interface Callback{
        void onSongSuccess(SongResponse songResponse);

        void onFail();
    }
}
