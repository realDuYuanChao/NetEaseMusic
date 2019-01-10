package shellhub.github.neteasemusic.view;

import java.util.List;

import shellhub.github.neteasemusic.model.entities.Album;
import shellhub.github.neteasemusic.model.entities.Artist;

public interface LocalView extends BaseView{
    void updateSongList();

    void updateArtistList(List<Artist> artists);

    void updateAlbumList(List<Album> albums);

    void navigatePlay();

    void play(String songUrl);

    void updateMiniController();
}
