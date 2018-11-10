package shellhub.github.neteasemusic.model;

import android.view.View;

public interface PlayModel {

    void deal(View view, PlayCallback callback);

    void getSongUrl(int id, PlayCallback callback);

    void getPicUrl(int id, PlayCallback callback);

    void saveSongId(int id);

    void readSongId(PlayCallback callback);

    interface PlayCallback{
        void onPlayType(int resId);

        void onPrevious();

        void onPlay();

        void onPause();

        void onNext();

        void onPlaylist();

        void onFavorite();

        void onDownload();

        void onComment();

        void onMenu();

        void onSongUrl(String songUrl);

        void onPicUrl(String picUrl);

        void onSongId(int id);
        //TODO
    }
}
