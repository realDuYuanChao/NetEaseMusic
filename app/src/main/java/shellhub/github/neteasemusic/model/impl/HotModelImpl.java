package shellhub.github.neteasemusic.model.impl;

import shellhub.github.neteasemusic.model.HotModel;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.response.banner.BannerResponse;
import shellhub.github.neteasemusic.util.ConstantUtils;
import shellhub.github.neteasemusic.util.NetEaseMusicApp;

public class HotModelImpl implements HotModel {
    @Override
    public void getBanner(Callback callback) {
        NetEaseMusicApp.getNetEaseMusicService().getBanner(new NetEaseMusicService.Callback<BannerResponse>(){

            @Override
            public void onSuccess(BannerResponse data) {
                if (data.getCode() == ConstantUtils.SUCCESS) {
                    callback.onBanner(data.getBanners());
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
