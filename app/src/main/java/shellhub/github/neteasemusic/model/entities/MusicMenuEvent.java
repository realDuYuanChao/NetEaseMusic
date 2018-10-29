package shellhub.github.neteasemusic.model.entities;

import java.util.List;

import lombok.Data;

@Data
public class MusicMenuEvent {
    private List<MusicMenu> musicMenus;

    public MusicMenuEvent(List<MusicMenu> musicMenus) {
        this.musicMenus = musicMenus;
    }
}
