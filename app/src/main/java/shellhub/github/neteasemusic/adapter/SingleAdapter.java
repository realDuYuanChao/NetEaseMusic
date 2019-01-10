package shellhub.github.neteasemusic.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;
import lombok.Data;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.entities.Single;
import shellhub.github.neteasemusic.presenter.LocalPresenter;
import shellhub.github.neteasemusic.ui.activities.LocalActivity;
import shellhub.github.neteasemusic.util.TagUtils;

@Data
public class SingleAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private String TAG = TagUtils.getTag(this.getClass());
    private List<Single> singles = new ArrayList<>();

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_IS_PLAYING = 1;
    private static final int TYPE_IS_NORMAL = 2;
    private LocalPresenter localPresenter = LocalActivity.mLocalPresenter;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_HEADER:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_item_header, viewGroup, false);
                ButterKnife.bind(this, view);
                return new LocalSongHeaderViewHolder(view);
            case TYPE_IS_PLAYING:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_item_playing, viewGroup, false);
                ButterKnife.bind(this, view);
                return new LocalSongPlayingViewHolder(localPresenter, view);
            case TYPE_IS_NORMAL:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_item, viewGroup, false);
                ButterKnife.bind(this, view);
                return new LocalSongViewHolder(localPresenter, view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        localPresenter.onBindSongRowViewAtPosition(position, holder);
    }

    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType: " + position);
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == localPresenter.getCurrentSongPosition()) {
            return TYPE_IS_PLAYING;
        } else {
            return TYPE_IS_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return localPresenter.getSongCount();
    }
}
