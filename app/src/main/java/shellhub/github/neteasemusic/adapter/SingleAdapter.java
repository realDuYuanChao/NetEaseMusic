package shellhub.github.neteasemusic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.presenter.LocalPresenter;
import shellhub.github.neteasemusic.ui.activities.LocalActivity;
import shellhub.github.neteasemusic.util.ConstantUtils;

public class SingleAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private LocalPresenter localPresenter = LocalActivity.mLocalPresenter;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType) {
            case ConstantUtils.SINGLE_TYPE_HEADER:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_item_header, viewGroup, false);
                ButterKnife.bind(this, view);
                return new LocalSongHeaderViewHolder(view);
            case ConstantUtils.SINGLE_TYPE_IS_PLAYING:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_item_playing, viewGroup, false);
                ButterKnife.bind(this, view);
                return new LocalSongPlayingViewHolder(localPresenter, view);
            case ConstantUtils.SINGLE_TYPE_IS_NORMAL:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_item, viewGroup, false);
                ButterKnife.bind(this, view);
                return new LocalSongViewHolder(localPresenter, view);
            default:
                return null; //this won't be executed
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        localPresenter.onBindSongRowViewAtPosition(position, holder);
    }

    @Override
    public int getItemViewType(int position) {
        return localPresenter.getItemViewTypeOfSingle(position);
    }

    @Override
    public int getItemCount() {
        return localPresenter.getSongItemCount();
    }
}
