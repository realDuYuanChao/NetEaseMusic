package shellhub.github.neteasemusic.model;

import java.util.List;

import shellhub.github.neteasemusic.model.entities.MusicMenu;

public interface MusicMenuModel {
    void loadMusicMenu(MusicMenuCallback callback);

    interface MusicMenuCallback{
        void loadedMusicMenu(List<MusicMenu> musicMenus);
    }
}
