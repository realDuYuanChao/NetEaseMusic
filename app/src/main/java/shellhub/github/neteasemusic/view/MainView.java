package shellhub.github.neteasemusic.view;

import android.os.Bundle;

import shellhub.github.neteasemusic.model.NavProfile;
import shellhub.github.neteasemusic.response.detail.DetailResponse;

public interface MainView extends BaseView {

    void updateNavProfile(NavProfile navProfile);

    void updateDetail(DetailResponse detailResponse);

    void setUpNavHeader();
}
