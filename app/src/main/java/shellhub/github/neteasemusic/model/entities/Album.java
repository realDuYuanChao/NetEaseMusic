package shellhub.github.neteasemusic.model.entities;

import android.net.Uri;

import lombok.Data;

@Data
public class Album {
    private int id;
    private Uri albumCoverUri;
    private int artistId;
    private String artistName;
    private int songCount;
    private String title;
    private int year;
}
