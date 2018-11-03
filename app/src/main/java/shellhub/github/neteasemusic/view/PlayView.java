package shellhub.github.neteasemusic.view;

public interface PlayView extends BaseView{

    void playMode();

    void previous();

    void play();

    void pause();

    void next();

    void playlist();

    void favorite();

    void download();

    void comment();

    void menu();

    void updateDuration(int duration);

    //TODO
}
