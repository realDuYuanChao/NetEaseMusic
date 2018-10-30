package shellhub.github.neteasemusic.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Data;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.entities.Artist;
import shellhub.github.neteasemusic.util.TagUtils;

@Data
public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.SingleViewHolder> {
    private String TAG = TagUtils.getTag(this.getClass());
    private List<Artist> artists = new ArrayList<Artist>();

    @NonNull
    @Override
    public SingleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.artist_item, viewGroup, false);
        ButterKnife.bind(this, view);
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleViewHolder singleViewHolder, int i) {
        singleViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        LogUtils.d(TAG, artists.size());
        return artists.size();
    }

    public class SingleViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_artist_profile)
        ImageView ivArtistProfile;

        @BindView(R.id.tv_artist_name)
        TextView tvArtistName;

        @BindView(R.id.tv_artist_song_count)
        TextView tvArtistSongCount;


        public SingleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int index) {
            tvArtistName.setText(artists.get(index).getName());
            String songCountTitle = artists.get(index).getSongCount() + "" +  Utils.getApp().getResources().getString(R.string.songs);
            tvArtistSongCount.setText(songCountTitle);
            Glide.with(itemView).load(artists.get(index).getProfile())
                    .apply(new RequestOptions().placeholder(R.drawable.default_artist_avatar).centerCrop().fitCenter())
                    .into(ivArtistProfile);
        }
    }
}
