package shellhub.github.neteasemusic.presenter.impl;

import shellhub.github.neteasemusic.model.PlaylistModel;
import shellhub.github.neteasemusic.model.impl.PlaylistModelIMpl;
import shellhub.github.neteasemusic.presenter.PlaylistPresenter;
import shellhub.github.neteasemusic.response.playlist.Playlist;
import shellhub.github.neteasemusic.response.recommend.resource.RecommendSongItem;
import shellhub.github.neteasemusic.view.PlaylistDetailView;

public class PlaylistPresenterImpl implements PlaylistPresenter, PlaylistModel.Callback {
    PlaylistDetailView mPlaylistDetailView;
    PlaylistModel mPlaylistModel;
    public PlaylistPresenterImpl(PlaylistDetailView playlistDetailView) {
        this.mPlaylistDetailView = playlistDetailView;
        this.mPlaylistModel = new PlaylistModelIMpl();
    }

    @Override
    public void getPlaylist(RecommendSongItem recommendSongItem) {
        //todo
        mPlaylistModel.getPlaylistDetail(/*recommendSongItem.getId()*/recommendSongItem.getId(), this);
    }

    @Override
    public void onPlaylistDetail(Playlist playlist) {
        mPlaylistDetailView.showPlaylist(playlist);
    }
}
