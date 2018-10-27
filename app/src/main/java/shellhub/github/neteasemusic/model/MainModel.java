package shellhub.github.neteasemusic.model;

import android.os.Bundle;

import shellhub.github.neteasemusic.model.entities.NavProfile;
import shellhub.github.neteasemusic.response.detail.DetailResponse;

public interface MainModel {
    void update(Bundle bundle, MainCallback callback);

    interface MainCallback {
        void updateProfile(NavProfile navProfile);

        void updateDetail(DetailResponse detailResponse);
    }
}
