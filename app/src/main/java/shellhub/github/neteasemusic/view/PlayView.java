package shellhub.github.neteasemusic.view;

public interface PlayView extends BaseView{

    void playType(int resId);

    void previous();

    void play();

    void play(String audioUrl);

    void pause();

    void next();

    void playlist();

    void favorite();

    void download();

    void comment();

    void menu();

    void updateDuration();

    void initPlayTypeIcon();

    void displayPic(String picUrl);

    void reloadSongId(int id);
    //TODO
}
