package shellhub.github.neteasemusic.model.entities;

import lombok.Data;

/**
 * this object is delivered by Event BUS
 */
@Data
public class MusicMenuIndexEvent {
    private int index;

    public MusicMenuIndexEvent(int index) {
        this.index = index;
    }
}
