package shellhub.github.neteasemusic.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.adapter.HotAdapter;
import shellhub.github.neteasemusic.response.banner.BannersItem;
import shellhub.github.neteasemusic.response.recommend.resource.RecommendSongItem;

public class HotFragment extends Fragment {

    @BindView(R.id.rv_fragment_hot)
    RecyclerView rvHot;

    private HotAdapter hotAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        setup();
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    private void setup() {
        rvHot.setLayoutManager(new LinearLayoutManager(getContext()));
        rvHot.setAdapter(hotAdapter = new HotAdapter());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBannerEvent(List<BannersItem> bannersItems) {
        hotAdapter.setBannersItems(bannersItems);
        hotAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRecommendSongListEvent(List<RecommendSongItem> recommendSongItems) {
        hotAdapter.setRecommendSongItems(recommendSongItems);
        hotAdapter.notifyDataSetChanged();
    }
}
