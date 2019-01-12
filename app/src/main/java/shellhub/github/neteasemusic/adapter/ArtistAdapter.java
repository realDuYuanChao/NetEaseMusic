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

public class ArtistAdapter extends RecyclerView.Adapter<LocalArtistViewHolder> {
    LocalPresenter presenter = LocalActivity.mLocalPresenter;

    @NonNull
    @Override
    public LocalArtistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.artist_item, viewGroup, false);
        ButterKnife.bind(this, view);
        return new LocalArtistViewHolder(presenter, view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalArtistViewHolder holder, int i) {
        presenter.onBindArtistRowViewAtPosition(i, holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getArtistItemCount();
    }
}
