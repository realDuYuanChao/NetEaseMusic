package shellhub.github.neteasemusic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Data;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.response.search.SongsItem;
import shellhub.github.neteasemusic.util.TagUtils;

@Data
public class SearchSingleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG = TagUtils.getTag(this.getClass());
    private List<SongsItem> songsItems = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);

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

        @BindView(R.id.tv_single_title)
        TextView tvSingleTitle;

        @BindView(R.id.tv_single_artist)
        TextView tvSingleArtist;

        public SearchSingleViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            tvSingleArtist.setText(songsItems.get(position).getName());
            tvSingleTitle.setText(songsItems.get(position).getArtists().get(0).getName()); // just first name TODO
        }
    }
}
