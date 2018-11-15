package shellhub.github.neteasemusic.presenter.impl;

import java.util.List;

import shellhub.github.neteasemusic.model.LocalModel;
import shellhub.github.neteasemusic.model.entities.Album;
import shellhub.github.neteasemusic.model.entities.Artist;
import shellhub.github.neteasemusic.model.entities.Single;
import shellhub.github.neteasemusic.model.impl.LocalModelImpl;
import shellhub.github.neteasemusic.presenter.LocalPresenter;
import shellhub.github.neteasemusic.util.TagUtils;
import shellhub.github.neteasemusic.view.LocalView;

public class LocalPresenterImpl implements LocalPresenter,  LocalModel.Callback {
    private String TAG = TagUtils.getTag(this.getClass());
    private LocalView mLocalView;
    private LocalModel mLocalModel;

    public LocalPresenterImpl(LocalView mLocalView) {
        this.mLocalView = mLocalView;
        mLocalModel = new LocalModelImpl();
    }

    @Override
    public void load() {
        mLocalModel.loadAllSingle(this);
        mLocalModel.loadAllArtist(this);
        mLocalModel.loadAllAlbum(this);
    }

    @Override
    public void query(String keyword) {
        mLocalModel.query(keyword, this);
    }

    @Override
    public void loadSong(Single single) {
        mLocalModel.loadSong(single, this);
    }



    @Override
    public void onLoadedSong(String songUrl) {
        mLocalView.play(songUrl);
    }

    @Override
    public void onLoadedAllSingle(List<Single> singles) {
        mLocalView.loadSingle(singles);
    }

    @Override
    public void onLoadedAllAlbum(List<Album> albums) {
        mLocalView.loadAlbum(albums);
    }

    @Override
    public void onLoadAllArtist(List<Artist> artists) {
        mLocalView.loadArtist(artists);
    }
}
