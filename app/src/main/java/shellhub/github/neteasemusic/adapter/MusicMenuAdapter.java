package shellhub.github.neteasemusic.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Data;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.entities.MusicMenu;

@Data
public class MusicMenuAdapter extends RecyclerView.Adapter<MusicMenuAdapter.MusicMenuViewHolder> {

    private List<MusicMenu> musicMenus = new ArrayList<>();

    @NonNull
    @Override
    public MusicMenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.music_menu_item, viewGroup, false);
        ButterKnife.bind(this, view);
        return new MusicMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicMenuViewHolder musicMenuViewHolder, int i) {
        Glide.with(Utils.getApp()).load(musicMenus.get(i).getIcon()).into(musicMenuViewHolder.ivMusicMenuIcon);
        musicMenuViewHolder.tvMusicMenuDesc.setText(musicMenus.get(i).getDesc());
        musicMenuViewHolder.tvMusicMenuDetail.setText(musicMenus.get(i).getDetail());
        if (i == musicMenus.size() - 1) {
            //remove the latest divider
            musicMenuViewHolder.musicMenuDivider.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return musicMenus.size();
    }

    public class MusicMenuViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_music_menu_ic)
        public ImageView ivMusicMenuIcon;
        @BindView(R.id.tv_music_menu_desc)
        public TextView tvMusicMenuDesc;
        @BindView(R.id.tv_music_menu_detail)
        public TextView tvMusicMenuDetail;
        @BindView(R.id.music_menu_divider)
        public View musicMenuDivider;

        public MusicMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
