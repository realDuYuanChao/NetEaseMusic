package shellhub.github.neteasemusic.adapter;

import android.net.Uri;

public interface LocalAlbumRowView {
    void setAlbumCover(Uri albumCover);

    void setAlbumTitle(String title);

    void setAlbumSongCount(String albumSongCount);

    void setArtistName(String artistName);
}
