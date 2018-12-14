package shellhub.github.neteasemusic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;

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

    private static final int TYPE_IS_PLAYING = 0;
    private static final int TYPE_IS_NORMAL = 1;

    private int currentPosition = 3;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType) {
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
        if (holder instanceof SingleViewHolder) {
            ((SingleViewHolder) holder).bind(position);
        } else if (holder instanceof SinglePlayViewHolder) {
            ((SinglePlayViewHolder) holder).bind(position);
        } else {
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == currentPosition) {
            return TYPE_IS_PLAYING;
        } else {
            return TYPE_IS_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        LogUtils.d(TAG, singles.size());
        return singles.size();
    }

    public class SinglePlayViewHolder extends SingleViewHolder {

        public SinglePlayViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            super.bind(position);
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
                if (currentPosition == index) {
                    EventBus.getDefault().post(new PlayActivityEvent());
                } else {
                    currentPosition = index;
                    notifyDataSetChanged();
                    EventBus.getDefault().post(singles.get(index));
                }

            });
        }
    }
}
