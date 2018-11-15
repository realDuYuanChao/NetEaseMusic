package shellhub.github.neteasemusic.view;

import android.graphics.Bitmap;

public interface PlayView extends BaseView{

    void playType(int resId);

    void previous();

    void play(String audioUrl);

    void pause();

    void next();

    void playlist();

    void favorite();

    void download();

    void comment(int songId);

    void menu();

    void updateDuration();

    void initPlayTypeIcon();


    void displayPic(Bitmap bitmap);

    //TODO
}
