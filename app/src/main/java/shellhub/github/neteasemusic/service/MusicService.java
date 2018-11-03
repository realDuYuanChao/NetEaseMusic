package shellhub.github.neteasemusic.service;

public interface MusicService {
    int getCurrentPosition();

    int getDuration();

    void seekTo(int position);
}
