package shellhub.github.neteasemusic.service;

import shellhub.github.neteasemusic.model.entities.Single;

public interface MusicService {
    int getCurrentPosition();

    int getDuration();

    void seekTo(int position);

    String next();

    String previous();

    int getBufferPercent();

}
