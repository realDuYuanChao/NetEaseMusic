package shellhub.github.neteasemusic.model;

import java.util.List;

import shellhub.github.neteasemusic.model.entities.Artist;
import shellhub.github.neteasemusic.model.entities.Single;

public interface ArtistModel {
    void loadArtist(Callback callback);

    interface Callback{
        void loadArtist(List<Artist> artists);
    }
}
