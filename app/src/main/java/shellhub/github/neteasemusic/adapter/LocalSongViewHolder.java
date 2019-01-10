package shellhub.github.neteasemusic.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import butterknife.BindView;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.presenter.LocalPresenter;

public class LocalSongViewHolder extends BaseViewHolder implements LocalSongRowView{
    @BindView(R.id.tv_single_title)
    TextView tvSingleTitle;

    @BindView(R.id.tv_single_artist)
    TextView tvSingleArtist;

    LocalPresenter presenter;

    public LocalSongViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener((view)->{
            presenter.onSongItemClickedAtPosition(getAdapterPosition());
        });
    }

    public LocalSongViewHolder(LocalPresenter presenter, @NonNull View itemView) {
        this(itemView);
        this.presenter = presenter;
    }

    @Override
    public void setSongTitle(String songTitle) {
        tvSingleTitle.setText(songTitle);
    }

    @Override
    public void setSongArtist(String songArtist) {
        tvSingleTitle.setText(songArtist);
    }
}
