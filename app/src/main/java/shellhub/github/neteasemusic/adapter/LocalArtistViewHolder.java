package shellhub.github.neteasemusic.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.annotation.NonNull;
import butterknife.BindView;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.presenter.LocalPresenter;

public class LocalArtistViewHolder extends BaseViewHolder implements LocalArtistRowView {

    LocalPresenter presenter;

    @BindView(R.id.iv_artist_profile)
    ImageView ivArtistProfile;

    @BindView(R.id.tv_artist_name)
    TextView tvArtistName;

    @BindView(R.id.tv_artist_song_count)
    TextView tvArtistSongCount;

    public LocalArtistViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public LocalArtistViewHolder(LocalPresenter presenter, @NonNull View itemView) {
        this(itemView);
        this.presenter = presenter;
    }

    @Override
    public void setArtistName(String name) {
        tvArtistName.setText(name);
    }

    @Override
    public void setSongCountText(String songCountText) {
        tvArtistSongCount.setText(songCountText);
    }

    @Override
    public void setProfile(String profile) {
        Glide.with(itemView).load(profile)
                .apply(new RequestOptions().placeholder(R.drawable.default_artist_avatar).centerCrop().fitCenter())
                .into(ivArtistProfile);
    }
}
