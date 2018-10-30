package shellhub.github.neteasemusic.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Data;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.entities.Single;
import shellhub.github.neteasemusic.util.TagUtils;

@Data
public class SingleAdapter extends RecyclerView.Adapter<SingleAdapter.SingleViewHolder> {
    private String TAG = TagUtils.getTag(this.getClass());
    private List<Single> singles = new ArrayList<Single>();

    @NonNull
    @Override
    public SingleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_item, viewGroup, false);
        ButterKnife.bind(this, view);
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleViewHolder singleViewHolder, int i) {
        singleViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        LogUtils.d(TAG, singles.size());
        return singles.size();
    }

    public class SingleViewHolder extends RecyclerView.ViewHolder{
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
        }
    }
}
