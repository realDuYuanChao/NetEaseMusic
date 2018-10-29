package shellhub.github.neteasemusic.model.impl;

import com.blankj.utilcode.util.Utils;

import java.util.ArrayList;

import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.MusicMenuModel;
import shellhub.github.neteasemusic.model.entities.MusicMenu;
import shellhub.github.neteasemusic.model.entities.MusicMenuIndexEvent;
import shellhub.github.neteasemusic.util.MusicUtils;

public class MusicMenuModelImpl implements MusicMenuModel {


    @Override
    public void loadMusicMenu(MusicMenuCallback callback) {
        int[] musicMenuIcons = {
                R.drawable.ic_music,
                R.drawable.ic_recent,
                R.drawable.ic_download,
                R.drawable.ic_station,
                R.drawable.ic_favs,
                R.drawable.ic_moon
        };
        final String[] musicMenuDescs = {
                Utils.getApp().getResources().getString(R.string.local_music),
                Utils.getApp().getResources().getString(R.string.recent_play),
                Utils.getApp().getResources().getString(R.string.manage_downloads),
                Utils.getApp().getResources().getString(R.string.stations),
                Utils.getApp().getResources().getString(R.string.favs),
                Utils.getApp().getResources().getString(R.string.sati)

        };

        String[] musicMenuDetails = {
                /*TODO*/
                "(" + MusicUtils.getCount() + ")",
                "(102)",
                "(0)",
                "(0)",
                "(7)",
                Utils.getApp().getResources().getString(R.string.special_mode)
        };
        ArrayList<MusicMenu> musicMenus = new ArrayList<>();
        for (int i = 0; i < musicMenuIcons.length; i++) {
            MusicMenu musicMenu = new MusicMenu();
            musicMenu.setIcon(musicMenuIcons[i]);
            musicMenu.setDesc(musicMenuDescs[i]);
            musicMenu.setDetail(musicMenuDetails[i]);

            musicMenus.add(musicMenu);
        }
        callback.loadedMusicMenu(musicMenus);
    }

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
