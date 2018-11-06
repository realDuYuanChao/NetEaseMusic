package shellhub.github.neteasemusic.model.entities;

import java.util.List;

import lombok.Data;

@Data
public class AlbumEvent {
    private List<Album> albums;

    public AlbumEvent(List<Album> albums) {
        this.albums = albums;
    }
}
