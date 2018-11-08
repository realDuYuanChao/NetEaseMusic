package shellhub.github.neteasemusic.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Data;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.response.search.artist.ArtistsItem;
import shellhub.github.neteasemusic.util.TagUtils;

@Data
public class SearchArtistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String TAG = TagUtils.getTag(this.getClass());
    private List<ArtistsItem> artistsItems = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_artist_item, parent, false);
        ButterKnife.bind(this, view);
        return new SearchArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SearchArtistViewHolder) {
            ((SearchArtistViewHolder) holder).bind(position);
        }
    }

    @Override
    public int getItemCount() {
        return artistsItems.size();
    }

    public class SearchArtistViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_artist_pic)
        ImageView ivArtistPic;

        @BindView(R.id.tv_artist_name)
        TextView tvArtistName;

        @BindView(R.id.tv_artist_alias)
        TextView tvArtistAlias;

        public SearchArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            Glide.with(itemView).load(artistsItems.get(position).getPicUrl()).into(ivArtistPic);
            tvArtistName.setText(artistsItems.get(position).getName());
            if (artistsItems.get(position).getAlia() != null) {
                tvArtistAlias.setText("(" + artistsItems.get(position).getAlia().get(0) + ")");
            }else{
                tvArtistAlias.setText("");
            }
        }
    }
}
