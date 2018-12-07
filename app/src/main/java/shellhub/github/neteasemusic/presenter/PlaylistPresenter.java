package shellhub.github.neteasemusic.presenter;

import shellhub.github.neteasemusic.response.recommend.resource.RecommendSongItem;

public interface PlaylistPresenter {
    void getPlaylist(RecommendSongItem recommendSongItem);
}
