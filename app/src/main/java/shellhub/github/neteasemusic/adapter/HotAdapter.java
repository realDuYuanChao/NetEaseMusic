package shellhub.github.neteasemusic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lombok.Data;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.entities.PlaylistEvent;
import shellhub.github.neteasemusic.model.entities.RecommendSongItemEvent;
import shellhub.github.neteasemusic.response.banner.BannersItem;
import shellhub.github.neteasemusic.response.recommend.resource.RecommendSongItem;
import shellhub.github.neteasemusic.util.GlideImageLoader;
import shellhub.github.neteasemusic.util.TagUtils;

@Data
public class HotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG = TagUtils.getTag(this.getClass());
    private List<BannersItem> bannersItems = new ArrayList<>();
    private final int TYPE_BANNER = 0;
    private final int TYPE_TOP_RECOMMEND = 1;
    private final int TYPE_RECOMMENDED_SONG_LIST_HEADER = 2;
    private final int TYPE_RECOMMEND_SONG_LIST = 3;
    private final int TYPE_LATEST_SONG_LIST_HEADER = 4;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LogUtils.d(TAG, "ViewHolder");
        View view;
        switch (viewType) {
            case TYPE_BANNER:
                LogUtils.d(TAG, "viewType");
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_item, parent, false);
                ButterKnife.bind(this, view);
                return new BannerViewHolder(view);
            case TYPE_TOP_RECOMMEND:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_recomand, parent, false);
                ButterKnife.bind(this, view);
                return new TopRecommendViewHolder(view);
            case TYPE_RECOMMENDED_SONG_LIST_HEADER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_song_list_header, parent, false);
                ButterKnife.bind(this, view);
                return new RecommendSongListHeader(view, parent.getContext().getResources().getString(R.string.recommended_song_list));
            case TYPE_RECOMMEND_SONG_LIST:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommand_song_list, parent, false);
                ButterKnife.bind(this, view);
                return new RecommendSongListViewHolder(view);
            case TYPE_LATEST_SONG_LIST_HEADER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_song_list_header, parent, false);
                ButterKnife.bind(this, view);
                return new LatestSongListViewHeader(view, parent.getContext().getResources().getString(R.string.latest_music));
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder) {
            ((BannerViewHolder) holder).bind();
        } else if (holder instanceof TopRecommendViewHolder) {
            //not need bind
        } else if (holder instanceof RecommendSongListHeader) {
            //not need bind
            ((RecommendSongListHeader) holder).bind();
        } else if (holder instanceof RecommendSongListViewHolder) {
            //not need bind
        } else if (holder instanceof LatestSongListViewHeader) {
            ((LatestSongListViewHeader) holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner)
        Banner banner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            LogUtils.d(TAG, "bind");
        }

        public void bind() {
            List<String> images = new ArrayList<>();
            for (int i = 0, size = bannersItems.size(); i < size; i++) {
                images.add(bannersItems.get(i).getImageUrl());
            }
            banner.setImages(images).setImageLoader(new GlideImageLoader()).start();
        }
    }

    public class TopRecommendViewHolder extends RecyclerView.ViewHolder {

        public TopRecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.iv_fm, R.id.iv_daily, R.id.iv_song_list, R.id.iv_ranking})
        public void onTopRecommendClick(View view) {
            switch (view.getId()) {
                case R.id.iv_fm:
                    //todo
                    ToastUtils.showShort("FM");
                    break;
                case R.id.iv_daily:
                    ToastUtils.showShort("iv_daily");
                    break;
                case R.id.iv_song_list:
                    ToastUtils.showShort("iv_song_list");
                    break;
                case R.id.iv_ranking:
                    ToastUtils.showShort("iv_ranking");
                    break;
            }
        }
    }

    public class RecommendSongListHeader extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_playlist_header)
        TextView tvPlaylistHeader;

        private String playlistHeaderTitle;

        public RecommendSongListHeader(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public RecommendSongListHeader(@NonNull View itemView, String title) {
            super(itemView);
            playlistHeaderTitle = title;
            ButterKnife.bind(this, itemView);
        }

        @OnClick()
        public void onRecommendSongListHeaderClick(View view) {
            //todo
        }

        public void bind() {
            tvPlaylistHeader.setText(playlistHeaderTitle);
        }
    }

    public class RecommendSongListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rv_recommend_song_list)
        RecyclerView rvRecommendSongList;

        RecommendSongListAdapter adapter;

        public RecommendSongListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            EventBus.getDefault().register(this);
            init();
        }

        @Subscribe(threadMode = ThreadMode.MAIN)
        public void onRecommendSongListEvent(RecommendSongItemEvent event) {
            LogUtils.d(TAG, event.getRecommendSongItems());
            adapter.setRecommendSongItems(event.getRecommendSongItems());
            adapter.notifyDataSetChanged();
        }

        private void init() {
            rvRecommendSongList.setLayoutManager(new GridLayoutManager(itemView.getContext(), 3));
            rvRecommendSongList.setAdapter(adapter = new RecommendSongListAdapter());
        }


        @Data
        class RecommendSongListAdapter extends RecyclerView.Adapter<RecommendSongListAdapter.ItemViewHolder> {

            private List<RecommendSongItem> recommendSongItems = new ArrayList<>();

            @NonNull
            @Override
            public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ItemViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recommend_song_list_item, parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
                holder.bind(position);
            }

            @Override
            public int getItemCount() {
                return recommendSongItems.size();
            }

            public class ItemViewHolder extends RecyclerView.ViewHolder {

                @BindView(R.id.iv_recommend_cover)
                ImageView ivRecommendCover;

                @BindView(R.id.tv_recommend_song_list_name)
                TextView tvRecommendSongListName;

                public ItemViewHolder(@NonNull View itemView) {
                    super(itemView);
                    ButterKnife.bind(this, itemView);
                }

                public void bind(int position) {
                    if (position >= recommendSongItems.size()) {
                        return;
                    }
                    Glide.with(itemView).load(recommendSongItems.get(position).getPicUrl()).into(ivRecommendCover);
                    tvRecommendSongListName.setText(recommendSongItems.get(position).getName());

                    itemView.setOnClickListener((view) -> {

                        EventBus.getDefault().post(new PlaylistEvent(recommendSongItems.get(position)));
                    });
                }
            }
        }
    }

    public class LatestSongListViewHeader extends RecommendSongListHeader {

        public LatestSongListViewHeader(@NonNull View itemView) {
            super(itemView);
        }

        public LatestSongListViewHeader(@NonNull View itemView, String title) {
            super(itemView, title);
        }
    }
}
