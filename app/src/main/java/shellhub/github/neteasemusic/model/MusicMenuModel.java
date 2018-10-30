package shellhub.github.neteasemusic.model;

import java.util.ArrayList;
import java.util.List;

import shellhub.github.neteasemusic.model.entities.MusicMenu;
import shellhub.github.neteasemusic.model.entities.MusicMenuIndexEvent;

public interface MusicMenuModel {

    void navigate(MusicMenuIndexEvent event, MusicMenuCallback callback);

    interface MusicMenuCallback{

        void navigateToLocalView();

        void navigateToRecentView();

        void navigateToDownloadsView();

        void navigateToStationsView();

        void navigateToFavorites();
    }
}
