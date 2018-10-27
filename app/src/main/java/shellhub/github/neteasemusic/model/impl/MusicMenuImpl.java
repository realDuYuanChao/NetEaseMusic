package shellhub.github.neteasemusic.model.impl;

import com.blankj.utilcode.util.Utils;

import java.util.ArrayList;
import java.util.List;

import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.MusicMenuModel;
import shellhub.github.neteasemusic.model.entities.MusicMenu;

public class MusicMenuImpl implements MusicMenuModel {

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
        String[] musicMenuDescs = {
                Utils.getApp().getResources().getString(R.string.local_music),
                Utils.getApp().getResources().getString(R.string.recent_play),
                Utils.getApp().getResources().getString(R.string.manage_downloads),
                Utils.getApp().getResources().getString(R.string.station),
                Utils.getApp().getResources().getString(R.string.favs),
                Utils.getApp().getResources().getString(R.string.sati)

        };

        String[] musicMenuDetails = {
                /*TODO*/
                "(15)",
                "(102)",
                "(0)",
                "(0)",
                "(7)",
                Utils.getApp().getResources().getString(R.string.special_mode)
        };

        List<MusicMenu> musicMenus = new ArrayList<>();
        for (int i = 0; i < musicMenuIcons.length; i++) {
            MusicMenu musicMenu = new MusicMenu();
            musicMenu.setIcon(musicMenuIcons[i]);
            musicMenu.setDesc(musicMenuDescs[i]);
            musicMenu.setDetail(musicMenuDetails[i]);

            musicMenus.add(musicMenu);
        }
        callback.loadedMusicMenu(musicMenus);
    }
}
