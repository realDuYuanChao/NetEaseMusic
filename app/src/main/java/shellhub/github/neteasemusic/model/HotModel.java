package shellhub.github.neteasemusic.model;

import java.util.List;

import shellhub.github.neteasemusic.response.banner.BannerResponse;
import shellhub.github.neteasemusic.response.banner.BannersItem;

public interface HotModel {

    void getBanner(Callback callback);

    interface Callback{
        void onBanner(List<BannersItem> bannersItems);
    }
}
