package shellhub.github.neteasemusic.model;

import java.util.List;

import shellhub.github.neteasemusic.response.banner.BannerResponse;
import shellhub.github.neteasemusic.response.banner.BannersItem;
import shellhub.github.neteasemusic.response.recommend.resource.RecommendSongItem;

public interface HotModel {

    void getBanner(Callback callback);

    void getRecommendSongList(Callback callback);

    interface Callback{
        void onBanner(List<BannersItem> bannersItems);

        void onRecommendSongList(List<RecommendSongItem> recommendSongItems);
    }
}
