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

public class AlbumAdapter extends RecyclerView.Adapter<LocalAlbumViewHolder> {
    LocalPresenter presenter = LocalActivity.mLocalPresenter;

    @NonNull
    @Override
    public LocalAlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        ButterKnife.bind(this, view);
        return new LocalAlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalAlbumViewHolder holder, int position) {
        presenter.onBindAlbumRowViewAtPosition(position, holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getAlbumItemCount();
    }

}
