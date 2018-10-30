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

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Data;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.entities.MusicMenu;
import shellhub.github.neteasemusic.model.entities.MusicMenuIndexEvent;
import shellhub.github.neteasemusic.util.MusicUtils;
import shellhub.github.neteasemusic.util.TagUtils;

@Data
public class MusicMenuAdapter extends RecyclerView.Adapter<MusicMenuAdapter.MusicMenuViewHolder> {

    private String TAG = TagUtils.getTag(this.getClass());
    private List<MusicMenu> musicMenus = new ArrayList<>();

    private int[] musicMenuIcons = {
            R.drawable.ic_music,
            R.drawable.ic_recent,
            R.drawable.ic_download,
            R.drawable.ic_station,
            R.drawable.ic_favs,
            R.drawable.ic_moon
    };
    private String[] musicMenuDescs = {
            Utils.getApp().getResources().getString(R.string.local_music),
            Utils.getApp().getResources().getString(R.string.recent_play),
            Utils.getApp().getResources().getString(R.string.manage_downloads),
            Utils.getApp().getResources().getString(R.string.stations),
            Utils.getApp().getResources().getString(R.string.favs),
            Utils.getApp().getResources().getString(R.string.sati)

    };

    private String[] musicMenuDetails = {
            /*TODO*/
            "(" + MusicUtils.getCount() + ")",
            "(102)",
            "(0)",
            "(0)",
            "(7)",
            Utils.getApp().getResources().getString(R.string.special_mode)
    };

    public MusicMenuAdapter() {
        for (int i = 0; i < musicMenuIcons.length; i++) {
            MusicMenu musicMenu = new MusicMenu();
            musicMenu.setIcon(musicMenuIcons[i]);
            musicMenu.setDesc(musicMenuDescs[i]);
            musicMenu.setDetail(musicMenuDetails[i]);
            LogUtils.d(TAG, i);

            musicMenus.add(musicMenu);
        }
    }

    @NonNull
    @Override
    public MusicMenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.music_menu_item, viewGroup, false);
        ButterKnife.bind(this, view);
        return new MusicMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicMenuViewHolder musicMenuViewHolder, int i) {
        musicMenuViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return musicMenus.size();
    }

    class MusicMenuViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_music_menu_ic)
        ImageView ivMusicMenuIcon;
        @BindView(R.id.tv_music_menu_desc)
        TextView tvMusicMenuDesc;
        @BindView(R.id.tv_music_menu_detail)
        TextView tvMusicMenuDetail;
        @BindView(R.id.music_menu_divider)
        View musicMenuDivider;

        private MusicMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final int index) {
            Glide.with(Utils.getApp()).load(musicMenus.get(index).getIcon()).into(ivMusicMenuIcon);
            tvMusicMenuDesc.setText(musicMenus.get(index).getDesc());
            tvMusicMenuDetail.setText(musicMenus.get(index).getDetail());
            if (index == musicMenus.size() - 1) {
                //remove the latest divider
                musicMenuDivider.setVisibility(View.GONE);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new MusicMenuIndexEvent(index));
                }
            });

        }
    }
}
