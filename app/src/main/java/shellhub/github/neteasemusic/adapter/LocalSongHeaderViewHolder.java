package shellhub.github.neteasemusic.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import shellhub.github.neteasemusic.R;

public class LocalSongHeaderViewHolder extends BaseViewHolder implements LocalSongHeaderRowView {

    @BindView(R.id.tv_track_count)
    TextView tvTrackCount;

    public LocalSongHeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setTrackTitle(String title) {
        tvTrackCount.setText(title);
    }
}
