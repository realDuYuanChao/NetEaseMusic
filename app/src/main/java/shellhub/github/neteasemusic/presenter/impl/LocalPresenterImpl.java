package shellhub.github.neteasemusic.presenter.impl;

import android.util.Log;

import com.blankj.utilcode.util.Utils;

import java.util.ArrayList;
import java.util.List;

import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.adapter.BaseViewHolder;
import shellhub.github.neteasemusic.adapter.LocalSongHeaderViewHolder;
import shellhub.github.neteasemusic.adapter.LocalSongPlayingViewHolder;
import shellhub.github.neteasemusic.adapter.LocalSongViewHolder;
import shellhub.github.neteasemusic.model.LocalModel;
import shellhub.github.neteasemusic.model.entities.Album;
import shellhub.github.neteasemusic.model.entities.Artist;
import shellhub.github.neteasemusic.model.entities.Single;
import shellhub.github.neteasemusic.model.impl.LocalModelImpl;
import shellhub.github.neteasemusic.presenter.LocalPresenter;
import shellhub.github.neteasemusic.util.TagUtils;
import shellhub.github.neteasemusic.view.LocalView;

public class LocalPresenterImpl implements LocalPresenter, LocalModel.Callback {
    private String TAG = TagUtils.getTag(this.getClass());
    private LocalView mLocalView;
    private LocalModel mLocalModel;

    private List<Single> singles = new ArrayList<>();
    private int currentSongPosition;

    public LocalPresenterImpl(LocalView mLocalView) {
        this.mLocalView = mLocalView;
        mLocalModel = new LocalModelImpl();
    }

    @Override
    public void load() {
        mLocalView.showProgress();
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
    public int getSongCount() {
        return singles.size() + 1;
    }

    @Override
    public void onBindSongRowViewAtPosition(int position, BaseViewHolder holder) {
        String trackCountTitle = "(" + singles.size()  + " " + Utils.getApp().getResources().getString(R.string.tracks) + ")";

        if (holder instanceof LocalSongHeaderViewHolder) {
            ((LocalSongHeaderViewHolder) holder).setTrackTitle(trackCountTitle);
        } else if (holder instanceof LocalSongViewHolder) {
            ((LocalSongViewHolder) holder).setSongTitle(singles.get(position - 1).getTitle());
            ((LocalSongViewHolder) holder).setSongArtist(singles.get(position - 1).getArtist());
        } else if (holder instanceof LocalSongPlayingViewHolder) {
            Log.d(TAG, "onBindSongRowViewAtPosition: " + "playing");
            ((LocalSongPlayingViewHolder) holder).setSongTitle(singles.get(position - 1).getTitle());
            ((LocalSongPlayingViewHolder) holder).setSongArtist(singles.get(position - 1).getArtist());
            ((LocalSongPlayingViewHolder)holder).setSongTitleColor(Utils.getApp().getResources().getColor(R.color.red));
        }
    }

    @Override
    public void onSongItemClickedAtPosition(int position) {
        if (position == currentSongPosition) {
            mLocalView.navigatePlay();
        } else {
            currentSongPosition = position;
            loadSong(singles.get(position - 1));
            mLocalView.updateSongList();
        }
    }

    @Override
    public int getCurrentSongPosition() {
        return currentSongPosition;
    }


    @Override
    public void onLoadedSong(String songUrl) {
        mLocalView.play(songUrl);
        mLocalView.updateMiniController();
    }

    @Override
    public void onLoadedAllSingle(List<Single> singles) {
        mLocalView.hideProgress();
        mLocalView.updateSongList();
        this.singles = singles;
    }

    @Override
    public void onLoadedAllAlbum(List<Album> albums) {
        mLocalView.updateAlbumList(albums);
    }

    @Override
    public void onLoadAllArtist(List<Artist> artists) {
        mLocalView.updateArtistList(artists);
    }
}
