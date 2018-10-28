package shellhub.github.neteasemusic.presenter.impl;

import android.os.Bundle;

import java.util.List;

import shellhub.github.neteasemusic.model.MainModel;
import shellhub.github.neteasemusic.model.MusicMenuModel;
import shellhub.github.neteasemusic.model.entities.MusicMenu;
import shellhub.github.neteasemusic.model.entities.MusicMenuEvent;
import shellhub.github.neteasemusic.model.entities.NavProfile;
import shellhub.github.neteasemusic.model.impl.MainModelImpl;
import shellhub.github.neteasemusic.model.impl.MusicMenuModelImpl;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.presenter.MainPresenter;
import shellhub.github.neteasemusic.response.detail.DetailResponse;
import shellhub.github.neteasemusic.view.MainView;

public class MainPresenterImpl implements MainPresenter, MainModel.MainCallback, MusicMenuModel.MusicMenuCallback {
    private MainView mainView;
    private MainModel mainModel;
    private MusicMenuModel musicMenuModel;

    private String TAG = MainPresenter.class.getSimpleName();

    public MainPresenterImpl(NetEaseMusicService netEaseMusicService, MainView mainView) {
        this.mainView = mainView;
        this.mainModel = new MainModelImpl(netEaseMusicService);
        musicMenuModel = new MusicMenuModelImpl();
    }

    @Override
    public void update(Bundle bundle) {
        mainModel.update(bundle, this);
    }

    @Override
    public void musicMenuNavigate(MusicMenuEvent musicMenuEvent) {
        musicMenuModel.navigate(musicMenuEvent,this);
    }

    @Override
    public void updateProfile(NavProfile navProfile) {
        mainView.updateNavProfile(navProfile);
        musicMenuModel.loadMusicMenu(this);
    }

    @Override
    public void updateDetail(DetailResponse detailResponse) {
        mainView.updateDetail(detailResponse);
    }

    @Override
    public void loadedMusicMenu(List<MusicMenu> musicMenus) {
        mainView.updateMusicMenu(musicMenus);
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
}
