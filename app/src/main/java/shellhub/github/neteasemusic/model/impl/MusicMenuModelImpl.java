package shellhub.github.neteasemusic.model.impl;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

import java.util.ArrayList;

import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.MusicMenuModel;
import shellhub.github.neteasemusic.model.entities.MusicMenu;
import shellhub.github.neteasemusic.model.entities.MusicMenuIndexEvent;
import shellhub.github.neteasemusic.util.MusicUtils;
import shellhub.github.neteasemusic.util.TagUtils;

public class MusicMenuModelImpl implements MusicMenuModel {

    @Override
    public void navigate(MusicMenuIndexEvent musicMenuIndexEvent, MusicMenuCallback callback) {
        switch (musicMenuIndexEvent.getIndex()) {
            case 0:
                callback.navigateToLocalView();
                break;
            case 1:
                callback.navigateToRecentView();
                break;
            case 2:
                callback.navigateToDownloadsView();
                break;
            case 3:
                callback.navigateToStationsView();
                break;
            case 4:
                callback.navigateToFavorites();
                break;
            case 5:
                //TODO
                break;
        }
    }
}
