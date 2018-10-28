package shellhub.github.neteasemusic.model;

import java.util.List;

import shellhub.github.neteasemusic.model.entities.MusicMenu;
import shellhub.github.neteasemusic.model.entities.MusicMenuEvent;

public interface MusicMenuModel {
    void loadMusicMenu(MusicMenuCallback callback);

    void navigate(MusicMenuEvent event, MusicMenuCallback callback);

    interface MusicMenuCallback{
        void loadedMusicMenu(List<MusicMenu> musicMenus);

        void navigateToLocalView();

        void navigateToRecentView();

        void navigateToDownloadsView();

        void navigateToStationsView();

        void navigateToFavorites();
    }
}
