package shellhub.github.neteasemusic.model;

import java.util.List;

import shellhub.github.neteasemusic.model.entities.Album;
import shellhub.github.neteasemusic.model.entities.Artist;
import shellhub.github.neteasemusic.model.entities.Single;

public interface LocalModel {
    void loadSong(Single single, Callback callback);

    void loadAllSingle(Callback callback);

    void loadAllAlbum(Callback callback);

    void loadAllArtist(Callback callback);

    void query(String keyword, Callback callback);


    interface Callback {
        void onLoadedSong(String songUrl);

        void onLoadedAllSingle(List<Single> singles);

        void onLoadedAllAlbum(List<Album> albums);

        void onLoadAllArtist(List<Artist> artists);
    }
}
