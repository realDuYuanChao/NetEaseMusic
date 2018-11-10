package shellhub.github.neteasemusic.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.flexbox.FlexboxLayoutManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.adapter.HotAdapter;
import shellhub.github.neteasemusic.response.search.hot.HotResponse;
import shellhub.github.neteasemusic.util.TagUtils;

public class HistoryFragment extends Fragment {

    private String TAG = TagUtils.getTag(this.getClass());
    @BindView(R.id.iv_remove_history)
    ImageView ivRemoveHistory;

    @BindView(R.id.rvHots)
    RecyclerView rvHots;

    @BindView(R.id.rvHistory)
    RecyclerView rvHistory;

    HotAdapter mHotAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        ButterKnife.bind(this, view);

        setup();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    private void setup() {
        rvHots.setAdapter(mHotAdapter = new HotAdapter());
        rvHots.setLayoutManager(new FlexboxLayoutManager(getContext()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHotsReady(HotResponse hotResponse) {
        mHotAdapter.setHots(hotResponse.getResult().getHots());
        mHotAdapter.notifyDataSetChanged();
    }
}
