package shellhub.github.neteasemusic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Data;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.entities.PlayActivityEvent;
import shellhub.github.neteasemusic.model.entities.Single;
import shellhub.github.neteasemusic.util.TagUtils;

@Data
public class SingleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG = TagUtils.getTag(this.getClass());
    private List<Single> singles = new ArrayList<>();

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_IS_PLAYING = 1;
    private static final int TYPE_IS_NORMAL = 2;

    private int currentPosition = 3;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_HEADER:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_item_header, viewGroup, false);
                ButterKnife.bind(this, view);
                return new SingleHeaderViewHolder(view);
            case TYPE_IS_PLAYING:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_item_playing, viewGroup, false);
                ButterKnife.bind(this, view);
                return new SinglePlayViewHolder(view);
            case TYPE_IS_NORMAL:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_item, viewGroup, false);
                ButterKnife.bind(this, view);
                return new SingleViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SingleHeaderViewHolder) {
            ((SingleHeaderViewHolder) holder).bind();
        } else if (holder instanceof SingleViewHolder) {
            ((SingleViewHolder) holder).bind(position - 1);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == currentPosition) {
            return TYPE_IS_PLAYING;
        } else {
            return TYPE_IS_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        LogUtils.d(TAG, singles.size());
        return singles.size() + 1;
    }

    public class SingleHeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_track_count)
        TextView tvTrackCount;

        public SingleHeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind() {
            String trackCountTitle = "(" + singles.size() + " " + Utils.getApp().getResources().getString(R.string.tracks) + ")";
            tvTrackCount.setText(trackCountTitle);
        }
    }

    public class SinglePlayViewHolder extends SingleViewHolder {

        public SinglePlayViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(int position) {
            super.bind(position);
            tvSingleTitle.setTextColor(Utils.getApp().getResources().getColor(R.color.red));
        }
    }

    public class SingleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_single_title)
        TextView tvSingleTitle;

        @BindView(R.id.tv_single_artist)
        TextView tvSingleArtist;


        public SingleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int index) {
            tvSingleTitle.setText(singles.get(index).getTitle());
            tvSingleArtist.setText(singles.get(index).getArtist());

            itemView.setOnClickListener((view) -> {
                if (currentPosition == index + 1) {
                    EventBus.getDefault().post(new PlayActivityEvent());
                } else {
                    currentPosition = index + 1;
                    notifyDataSetChanged();
                    EventBus.getDefault().post(singles.get(index));
                }

            });
        }
    }
}
