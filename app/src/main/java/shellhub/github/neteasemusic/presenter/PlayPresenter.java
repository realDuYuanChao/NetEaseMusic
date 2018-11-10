package shellhub.github.neteasemusic.presenter;

import android.view.View;

public interface PlayPresenter {
    void executeClick(View view);

    void getSongUrl(int id);

    void getSongPic(int id);

    void getSongId();

    void saveSongID(int id);
}
