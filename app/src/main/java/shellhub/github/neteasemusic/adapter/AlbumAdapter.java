package shellhub.github.neteasemusic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Data;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.entities.Album;

@Data
public class AlbumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Album> albums = new ArrayList<>();
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        ButterKnife.bind(this, view);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((AlbumViewHolder)(holder)).bind(position);
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_album_cover)
        ImageView ivAlbumCover;

        @BindView(R.id.tv_album_title)
        TextView tvAlbumTitle;

        @BindView(R.id.tv_album_song_count)
        TextView tvAlbumSongCount;

        @BindView(R.id.tv_ablum_artist_name)
        TextView tvArtistName;


        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            Glide.with(itemView).load(albums.get(position).getAlbumCoverUri()).into(ivAlbumCover);
            tvAlbumTitle.setText(albums.get(position).getTitle());
            tvAlbumSongCount.setText(albums.get(position).getSongCount() + Utils.getApp().getResources().getString(R.string.songs));
            tvArtistName.setText(albums.get(position).getArtistName());
        }
    }
}
