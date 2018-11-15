package shellhub.github.neteasemusic.presenter.impl;

import android.graphics.Bitmap;
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
    public void getSongPic() {
        mPlayModel.getSongUrl(this);
    }

    @Override
    public void updateUI() {
        mPlayModel.getPlayType(this);
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
        mPlayView.play(null);
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
    public void onComment(int songId) {
        mPlayView.comment(songId);
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
    public void onAlbumUrl(String picUrl) {
//        mPlayView.displayPic(picUrl);
    }

    @Override
    public void onLoadedAlbum(Bitmap bitmap) {
        mPlayView.displayPic(bitmap);
    }
}
