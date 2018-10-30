package shellhub.github.neteasemusic.model.entities;

import java.util.List;

import lombok.Data;

@Data
public class ArtistEvent {
    private List<Artist> artist;

    public ArtistEvent(List<Artist> artist) {
        this.artist = artist;
    }
}
