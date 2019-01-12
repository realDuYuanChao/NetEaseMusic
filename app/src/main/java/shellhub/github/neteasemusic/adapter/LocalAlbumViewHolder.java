package shellhub.github.neteasemusic.adapter;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import butterknife.BindView;
import shellhub.github.neteasemusic.R;

public class LocalAlbumViewHolder extends BaseViewHolder implements LocalAlbumRowView {

    @BindView(R.id.iv_album_cover)
    ImageView ivAlbumCover;

    @BindView(R.id.tv_album_title)
    TextView tvAlbumTitle;

    @BindView(R.id.tv_album_song_count)
    TextView tvAlbumSongCount;

    @BindView(R.id.tv_ablum_artist_name)
    TextView tvArtistName;

    public LocalAlbumViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void setAlbumCover(Uri albumCover) {
        Glide.with(itemView).load(albumCover).into(ivAlbumCover);
    }

    @Override
    public void setAlbumTitle(String title) {
        tvAlbumTitle.setText(title);
    }

    @Override
    public void setAlbumSongCount(String albumSongCount) {
        tvAlbumSongCount.setText(albumSongCount);
    }

    @Override
    public void setArtistName(String artistName) {
        tvArtistName.setText(artistName);
    }
}
