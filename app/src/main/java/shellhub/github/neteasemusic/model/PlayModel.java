package shellhub.github.neteasemusic.model;

import android.graphics.Bitmap;
import android.view.View;

public interface PlayModel {

    void deal(View view, PlayCallback callback);

    void getSongUrl(PlayCallback callback);

    void getPicUrl(PlayCallback callback);

    void saveSongId(int id);

    void readSongId(PlayCallback callback);

    void getPlayType(PlayCallback callback);

    interface PlayCallback{
        void onPlayType(int resId);

        void onPrevious();

        void onPlay();

        void onPause();

        void onNext();

        void onPlaylist();

        void onFavorite();

        void onDownload();

        void onComment(int songId);

        void onMenu();

        void onSongUrl(String songUrl);

        void onAlbumUrl(String albumUrl);

        void onLoadedAlbum(Bitmap bitmap);
        //TODO
    }
}
