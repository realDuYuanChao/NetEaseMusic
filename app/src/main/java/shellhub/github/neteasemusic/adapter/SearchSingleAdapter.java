package shellhub.github.neteasemusic.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Data;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.response.search.ArtistsItem;
import shellhub.github.neteasemusic.response.search.SongsItem;
import shellhub.github.neteasemusic.util.TagUtils;

@Data
public class SearchSingleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG = TagUtils.getTag(this.getClass());
    private List<SongsItem> songsItems = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_single_item, parent, false);

        ButterKnife.bind(this, view);
        return new SearchSingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SearchSingleViewHolder) {
            ((SearchSingleViewHolder) holder).bind(position);
        }
    }

    @Override
    public int getItemCount() {
        return songsItems.size();
    }

    public class SearchSingleViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_search_single_name)
        TextView tvSearchSingleName;

        @BindView(R.id.tv_search_single_artist_album)
        TextView tvSearchSingleArtistAlbum;

        public SearchSingleViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            tvSearchSingleName.setText(songsItems.get(position).getName());
            tvSearchSingleArtistAlbum.setText(getArtistAndAlbum(songsItems.get(position)));
        }
    }

    private String getArtistAndAlbum(SongsItem songsItem) {
        StringJoiner joiner = new StringJoiner("/");
        for (ArtistsItem artist : songsItem.getArtists()) {
            joiner.add(artist.getName());
        }

        return joiner.toString() + " - " + songsItem.getAlbum().getName();
    }
}
