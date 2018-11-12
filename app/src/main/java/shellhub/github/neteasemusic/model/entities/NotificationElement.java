package shellhub.github.neteasemusic.model.entities;

import android.graphics.Bitmap;

import lombok.Data;

@Data
public class NotificationElement {
    Bitmap songAlbumBitmap;
    String songName;
    String songArtistAndTitle;
    boolean playing;
    boolean loved;
    boolean openLyric;
}
