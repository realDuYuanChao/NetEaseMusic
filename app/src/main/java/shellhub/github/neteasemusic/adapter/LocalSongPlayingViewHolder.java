package shellhub.github.neteasemusic.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import butterknife.BindView;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.presenter.LocalPresenter;

public class LocalSongPlayingViewHolder extends BaseViewHolder implements LocalSongPlayingRowView {

    @BindView(R.id.tv_single_title)
    TextView tvSingleTitle;

    @BindView(R.id.tv_single_artist)
    TextView tvSingleArtist;

    private LocalPresenter presenter;

    public LocalSongPlayingViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener((view)->{
            presenter.onSongItemClickedAtPosition(getAdapterPosition());
        });
    }

    public LocalSongPlayingViewHolder(LocalPresenter presenter, @NonNull View itemView) {
        this(itemView);
        this.presenter = presenter;
    }

    @Override
    public void setSongTitleColor(int color) {
        tvSingleTitle.setTextColor(color);
    }

    @Override
    public void setSongTitle(String songTitle) {
        tvSingleTitle.setText(songTitle);
    }

    @Override
    public void setSongArtist(String songArtist) {
        tvSingleArtist.setText(songArtist);
    }
}
