package shellhub.github.neteasemusic.model.entities;

import android.graphics.Bitmap;

import lombok.Data;

@Data
public class NotificationElement {
    Bitmap songAlbumBitmap;
    String songName;
    String songArtistAndAlbum;
    boolean playing;
    boolean loved;
    boolean openLyric;
}
