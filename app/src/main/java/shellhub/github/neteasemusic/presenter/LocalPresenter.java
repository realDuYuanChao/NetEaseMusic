package shellhub.github.neteasemusic.presenter;

import shellhub.github.neteasemusic.adapter.BaseViewHolder;
import shellhub.github.neteasemusic.model.entities.Single;

public interface LocalPresenter {
    void load();

    void query(String keyword);

    void loadSong(Single single);

    int getSongItemCount();

    int getArtistItemCount();

    int getAlbumItemCount();

    void onBindSongRowViewAtPosition(int position, BaseViewHolder holder);

    void onBindArtistRowViewAtPosition(int position, BaseViewHolder holder);

    void onBindAlbumRowViewAtPosition(int position, BaseViewHolder holder);

    void onSongItemClickedAtPosition(int position);

    void onArtistItemClickAtPosition(int position);

    int getCurrentSongPosition();

    int getItemViewTypeOfSingle(int position);

}
