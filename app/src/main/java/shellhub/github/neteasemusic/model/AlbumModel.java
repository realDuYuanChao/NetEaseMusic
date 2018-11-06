package shellhub.github.neteasemusic.model;

import java.util.List;

import shellhub.github.neteasemusic.model.entities.Album;

public interface AlbumModel {
    void loadAlbums(Callback callback);

    interface Callback{
        void onLoaded(List<Album> albums);
    }
}
