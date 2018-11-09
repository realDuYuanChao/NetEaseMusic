package shellhub.github.neteasemusic.model;

import shellhub.github.neteasemusic.response.search.mp3.SongResponse;
import shellhub.github.neteasemusic.response.search.song.detail.SongDetailResponse;

public interface SongModel {
    void getSongUrl(int id, Callback callback);

    void getSongDetail(int ids, Callback callback);
    interface Callback{
        void onSongSuccess(SongResponse songResponse);

        void onSongDetailSuccess(SongDetailResponse songDetailResponse);

        void onFail();
    }
}
