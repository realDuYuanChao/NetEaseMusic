package shellhub.github.neteasemusic.presenter.impl;

import android.view.View;

import shellhub.github.neteasemusic.model.PlayModel;
import shellhub.github.neteasemusic.model.impl.PlayModelIml;
import shellhub.github.neteasemusic.presenter.PlayPresenter;
import shellhub.github.neteasemusic.view.PlayView;

public class PlayPresenterImpl implements PlayPresenter,PlayModel.PlayCallback {
    PlayView mPlayView;
    PlayModel mPlayModel;
    public PlayPresenterImpl(PlayView mPlayView) {
        this.mPlayView = mPlayView;
        this.mPlayModel = new PlayModelIml();
    }

    @Override
    public void executeClick(View view) {
        mPlayModel.deal(view, this);
    }

    @Override
    public void onPlayType() {
        mPlayView.playMode();
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
}
