package shellhub.github.neteasemusic.presenter.impl;

import android.view.View;

import shellhub.github.neteasemusic.model.PlayModel;
import shellhub.github.neteasemusic.model.impl.PlayModelIml;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.presenter.PlayPresenter;
import shellhub.github.neteasemusic.view.PlayView;

public class PlayPresenterImpl implements PlayPresenter,PlayModel.PlayCallback {
    PlayView mPlayView;
    PlayModel mPlayModel;
    private NetEaseMusicService mNetEaseMusicService;
    public PlayPresenterImpl(PlayView mPlayView, NetEaseMusicService netEaseMusicService) {
        this.mPlayView = mPlayView;
        this.mNetEaseMusicService = netEaseMusicService;
        this.mPlayModel = new PlayModelIml(mNetEaseMusicService);
    }

    @Override
    public void executeClick(View view) {
        mPlayModel.deal(view, this);
    }

    @Override
    public void getSongUrl(int id) {
        mPlayModel.getSongUrl(id, this);
    }

    @Override
    public void getSongPic(int id) {
        mPlayModel.getPicUrl(id, this);
    }

    @Override
    public void getSongId() {
        mPlayModel.readSongId(this);
    }

    @Override
    public void saveSongID(int id) {
        mPlayModel.saveSongId(id);
    }

    @Override
    public void onPlayType(int resId) {
        mPlayView.playType(resId);
    }

    @Override
    public void onPrevious() {
        mPlayView.previous();
    }

    @Override
    public void onPlay() {
        mPlayView.play();
    }

    @Override
    public void onPause() {
        mPlayView.pause();
    }

    @Override
    public void onNext() {
        mPlayView.next();
    }

    @Override
    public void onPlaylist() {
        mPlayView.playlist();
    }

    @Override
    public void onFavorite() {
        mPlayView.favorite();
    }

    @Override
    public void onDownload() {
        mPlayView.download();
    }

    @Override
    public void onComment() {
        mPlayView.comment();
    }

    @Override
    public void onMenu() {
        mPlayView.menu();
    }

    @Override
    public void onSongUrl(String songUrl) {
        mPlayView.play(songUrl);
    }

    @Override
    public void onPicUrl(String picUrl) {
        mPlayView.displayPic(picUrl);
    }

    @Override
    public void onSongId(int id) {
        mPlayView.reloadSongId(id);
    }
}
