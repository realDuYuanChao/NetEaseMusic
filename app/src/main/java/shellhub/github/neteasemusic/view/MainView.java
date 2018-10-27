package shellhub.github.neteasemusic.view;

import java.util.List;

import shellhub.github.neteasemusic.model.entities.MusicMenu;
import shellhub.github.neteasemusic.model.entities.NavProfile;
import shellhub.github.neteasemusic.response.detail.DetailResponse;

public interface MainView extends BaseView {

    void updateNavProfile(NavProfile navProfile);

    void updateDetail(DetailResponse detailResponse);

    void updateMusicMenu(List<MusicMenu> musicMenus);

    void setUpNavHeader();
}
