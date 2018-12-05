package shellhub.github.neteasemusic.presenter.impl;

import android.os.Bundle;

import java.util.List;

import shellhub.github.neteasemusic.model.HotModel;
import shellhub.github.neteasemusic.model.MainModel;
import shellhub.github.neteasemusic.model.MusicMenuModel;
import shellhub.github.neteasemusic.model.entities.MusicMenuIndexEvent;
import shellhub.github.neteasemusic.model.entities.NavProfile;
import shellhub.github.neteasemusic.model.impl.HotModelImpl;
import shellhub.github.neteasemusic.model.impl.MainModelImpl;
import shellhub.github.neteasemusic.model.impl.MusicMenuModelImpl;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.presenter.MainPresenter;
import shellhub.github.neteasemusic.response.banner.BannersItem;
import shellhub.github.neteasemusic.response.detail.DetailResponse;
import shellhub.github.neteasemusic.response.recommend.resource.RecommendSongItem;
import shellhub.github.neteasemusic.util.TagUtils;
import shellhub.github.neteasemusic.view.MainView;

public class MainPresenterImpl implements MainPresenter, MainModel.MainCallback, MusicMenuModel.MusicMenuCallback, HotModel.Callback {
    private String TAG = TagUtils.getTag(this.getClass());
    private MainView mainView;
    private MainModel mainModel;
    private MusicMenuModel musicMenuModel;
    private HotModel mHotModel;


    public MainPresenterImpl(NetEaseMusicService netEaseMusicService, MainView mainView) {
        this.mainView = mainView;
        this.mainModel = new MainModelImpl(netEaseMusicService);
        musicMenuModel = new MusicMenuModelImpl();
        mHotModel = new HotModelImpl();
    }

    @Override
    public void update(Bundle bundle) {
        mainModel.update(bundle, this);
    }

    @Override
    public void musicMenuNavigate(MusicMenuIndexEvent musicMenuIndexEvent) {
        musicMenuModel.navigate(musicMenuIndexEvent,this);
    }

    @Override
    public void getBanner() {
        mHotModel.getBanner(this);
    }

    @Override
    public void getRecommendSongList() {
        mHotModel.getRecommendSongList(this);
    }

    @Override
    public void updateProfile(NavProfile navProfile) {
        mainView.updateNavProfile(navProfile);
    }

    @Override
    public void updateDetail(DetailResponse detailResponse) {
        mainView.updateDetail(detailResponse);
    }

    @Override
    public void showNetworkError(String errorMsg) {
        mainView.showNetworkError(errorMsg);
    }


    @Override
    public void navigateToLocalView() {
        mainView.navigateToLocalView();
    }

    @Override
    public void navigateToRecentView() {
        mainView.navigateToRecentPlayView();
    }

    @Override
    public void navigateToDownloadsView() {
        mainView.navigateToDownloadsView();
    }

    @Override
    public void navigateToStationsView() {
        mainView.navigateToStationsView();
    }

    @Override
    public void navigateToFavorites() {
        mainView.navigateToFavorites();
    }

    @Override
    public void onBanner(List<BannersItem> bannersItems) {
        mainView.showBanner(bannersItems);
    }

    @Override
    public void onRecommendSongList(List<RecommendSongItem> recommendSongItems) {
        mainView.showRecommendSongList(recommendSongItems);
    }
}
