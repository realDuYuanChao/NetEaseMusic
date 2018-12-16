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
        String cookie = "__remember_me=true; " +
                "MUSIC_U=8f68d095e67f61eb13cc459fb616d94b47e5068cd50411b88eda4b883181a673b770156b3ad1280a9e8ae5c70ca8806963531a931aa80ad0; " +
                "__csrf=47281a4e13088a05288b11ca161c982c";
        NetEaseMusicApp.getNetEaseMusicService().getRecommendSongList(cookie, new NetEaseMusicService.Callback<RecommendSongListResponse>(){

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
