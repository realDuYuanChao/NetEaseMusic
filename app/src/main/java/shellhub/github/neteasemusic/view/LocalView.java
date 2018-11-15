package shellhub.github.neteasemusic.view;

import java.util.List;

import shellhub.github.neteasemusic.model.entities.Album;
import shellhub.github.neteasemusic.model.entities.Artist;
import shellhub.github.neteasemusic.model.entities.Single;

public interface LocalView extends BaseView{
    void loadSingle(List<Single> singles);

    void loadArtist(List<Artist> artists);

    void loadAlbum(List<Album> albums);

    void navigatePlay(String data);

    void play(String songUrl);
}
