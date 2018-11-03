package shellhub.github.neteasemusic.model;

import android.view.View;

public interface PlayModel {

    void deal(View view, PlayCallback callback);

    interface PlayCallback{
        void onPlayType();

        void onPrevious();

        void onPlay();

        void onPause();

        void onNext();

        void onPlaylist();

        void onFavorite();

        void onDownload();

        void onComment();

        void onMenu();

        //TODO
    }
}
