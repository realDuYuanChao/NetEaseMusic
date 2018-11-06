package shellhub.github.neteasemusic.presenter.impl;

import java.util.List;

import shellhub.github.neteasemusic.model.AlbumModel;
import shellhub.github.neteasemusic.model.ArtistModel;
import shellhub.github.neteasemusic.model.SingleModel;
import shellhub.github.neteasemusic.model.entities.Album;
import shellhub.github.neteasemusic.model.entities.Artist;
import shellhub.github.neteasemusic.model.entities.Single;
import shellhub.github.neteasemusic.model.impl.AlbumModelImpl;
import shellhub.github.neteasemusic.model.impl.ArtistModelImpl;
import shellhub.github.neteasemusic.model.impl.SingleModelImpl;
import shellhub.github.neteasemusic.presenter.LocalPresenter;
import shellhub.github.neteasemusic.util.TagUtils;
import shellhub.github.neteasemusic.view.LocalView;

public class LocalPresenterImpl implements LocalPresenter, ArtistModel.Callback, SingleModel.Callback, AlbumModel.Callback {
    private String TAG = TagUtils.getTag(this.getClass());
    private LocalView mLocalView;
    private SingleModel mSingleModel;
    private ArtistModel mArtistModel;
    private AlbumModel albumModel;

    public LocalPresenterImpl(LocalView mLocalView) {
        this.mLocalView = mLocalView;
        this.mSingleModel = new SingleModelImpl();
        mArtistModel = new ArtistModelImpl();
        albumModel = new AlbumModelImpl();
    }

    @Override
    public void load() {
        mSingleModel.loadSingle(this);
        mArtistModel.loadArtist(this);
        albumModel.loadAlbums(this);
    }

    @Override
    public void query(String keyword) {
        mSingleModel.query(keyword, this);
    }

    @Override
    public void loadArtist(List<Artist> artists) {
        mLocalView.loadArtist(artists);
    }

    @Override
    public void loadSingle(List<Single> singles) {
        mLocalView.loadSingle(singles);
    }

    @Override
    public void onLoaded(List<Album> albums) {
        mLocalView.loadAlbum(albums);
    }
}
