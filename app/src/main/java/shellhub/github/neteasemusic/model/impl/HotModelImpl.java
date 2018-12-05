package shellhub.github.neteasemusic.model.impl;

import com.blankj.utilcode.util.ToastUtils;

import shellhub.github.neteasemusic.model.HotModel;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.response.banner.BannerResponse;
import shellhub.github.neteasemusic.response.recommend.resource.RecommendSongListResponse;
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

    @Override
    public void getRecommendSongList(Callback callback) {
        ToastUtils.showShort("getRecommendSongList");
        NetEaseMusicApp.getNetEaseMusicService().getRecommendSongList(new NetEaseMusicService.Callback<RecommendSongListResponse>(){

            @Override
            public void onSuccess(RecommendSongListResponse data) {
                if (data.getCode() == ConstantUtils.SUCCESS) {
                    callback.onRecommendSongList(data.getRecommend());
                }else {
                    ToastUtils.showShort("fail to resolve recommend");
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShort("error");
            }
        });
    }
}
