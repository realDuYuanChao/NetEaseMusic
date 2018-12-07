package shellhub.github.neteasemusic.view;

import shellhub.github.neteasemusic.response.playlist.Playlist;

public interface PlaylistDetailView extends BaseView {
    void showPlaylist(Playlist playlist);

    void showProgress();

    void hideProgress();

    void showPlaylistCover(String coverImgUrl);

}
